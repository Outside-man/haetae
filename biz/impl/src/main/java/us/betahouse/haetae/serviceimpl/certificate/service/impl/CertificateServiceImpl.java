/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.certificate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.certificate.dal.service.CompetitionRepoService;
import us.betahouse.haetae.certificate.dal.service.QualificationsRepoService;
import us.betahouse.haetae.certificate.dal.service.SkillRepoService;
import us.betahouse.haetae.certificate.enums.CertificateStateEnum;
import us.betahouse.haetae.certificate.enums.CertificateTypeEnum;
import us.betahouse.haetae.certificate.manager.CertificateManager;
import us.betahouse.haetae.certificate.model.basic.CertificateBO;
import us.betahouse.haetae.serviceimpl.certificate.constant.CertificatePermType;
import us.betahouse.haetae.serviceimpl.certificate.request.CertificateRequest;
import us.betahouse.haetae.serviceimpl.certificate.service.CertificateService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyPerm;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 证书服务实现
 *
 * @author guofan.cp
 * @version : CertificateServiceImpl.java 2019/04/06 8:26 guofan.cp
 */
@Service
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    private CertificateManager certificateManager;
    @Autowired
    private QualificationsRepoService qualificationsRepoService;
    @Autowired
    private CompetitionRepoService competitionRepoService;
    @Autowired
    private SkillRepoService skillRepoService;
    @Autowired
    private UserInfoRepoService userInfoRepoService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CertificateBO create(CertificateRequest request, OperateContext context) {
        //证书类型异常抛出
        CertificateTypeEnum certificateTypeEnum = judgeCertificateType(request);
        CertificateBO certificateBO;
        //证书类型判断(四种类型，四六级与资格证书同表)
        switch (certificateTypeEnum) {
            //四六级证书
            case CET_4_6: {
                AssertUtil.assertNotNull(request.getCertificateGrade(), "成绩不能为空");
                AssertUtil.assertNotNull(request.getRank(), "四六级证书类型等级不能为空");
                Boolean CET_4_6Grade = Pattern.matches("[0-9]{3}", request.getCertificateGrade().toString());
                ;
                AssertUtil.assertTrue(CET_4_6Grade, "输入成绩需要为三位数");
                //证书范围判断
                if (Integer.valueOf(request.getCertificateGrade()) > 710 || Integer.valueOf(request.getCertificateGrade()) <= 424) {
                    CET_4_6Grade = false;
                }
                AssertUtil.assertTrue(CET_4_6Grade, "输入成绩区间不符合标准");
                request.setCertificateName("英语四六级证书");
                request.setCertificateOrganization("教育部高等教育司");
                //设置证书类型为四六级
                request.setType(CertificateTypeEnum.CET_4_6.getCode());
                certificateBO = certificateManager.createQualifications(request);
                break;
            }
            //资格证书
            case QUALIFICATIONS: {
                AssertUtil.assertNotNull(request.getCertificateName(), "发行证书名字不能为空");
                AssertUtil.assertNotNull(request.getCertificateOrganization(), "发行证书组织不能为空");
                AssertUtil.assertNotNull(request.getType(), "资格证书种类不能为空");
                certificateBO = certificateManager.createQualifications(request);
                break;
            }
            //竞赛证书
            case COMPETITION: {
                AssertUtil.assertNotNull(request.getCompetitionName(), "比赛名字不能为空");
                AssertUtil.assertNotNull(request.getRank(), "比赛级别不能为空");
                //重置  stuid转userid
                List<String> userIds = new ArrayList<>();
                for (String stuId : request.getWorkUserId()) {
                    UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByStuId(stuId);
                    AssertUtil.assertNotNull(userInfoBO, stuId + "学号不存在");
                    userIds.add(userInfoBO.getUserId());
                }
                //userid去重
                 int userIdLength =CollectionUtils.toStream(userIds)
                        .distinct()
                        .collect(Collectors.toList()).size();
                //学号重复异常抛出
                AssertUtil.assertTrue(userIdLength==userIds.size(),"重复学号添加");
                request.setWorkUserId(userIds);
                certificateBO = certificateManager.createCompetition(request);
                AssertUtil.assertNotNull(certificateBO, "证书为空创建失败");
                competitionUserIdCovert(certificateBO);
                break;
            }
            //技能证书
            case SKILL: {
                AssertUtil.assertNotNull(request.getCertificateName(), "发行证书名字不能为空");
                AssertUtil.assertNotNull(request.getRank(), "证书等级不能为空");
                certificateBO = certificateManager.createSkill(request);
                break;
            }
            //异常
            default: {
                throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getCode(), "证书类型不存在");
            }
        }
        return certificateBO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @VerifyPerm(permType = CertificatePermType.MODIFY_CERTIFICATE)
    public CertificateBO updateByTeacher(CertificateRequest request, OperateContext context) {
        CertificateBO certificateBO;
        certificateBO = Modify(request, context);
        AssertUtil.assertNotNull(certificateBO, "证书修改失败");
        return certificateBO;
    }

    @Override
    public CertificateBO update(CertificateRequest request, OperateContext context) {
        //证书类型异常抛出
        CertificateTypeEnum certificateTypeEnum = judgeCertificateType(request);
        CertificateBO certificateBO = new CertificateBO();
        if (request.getConfirmUserId() != null) {
            //这边confirmStuid更换为UserId;
            request.setConfirmUserId(userInfoRepoService.queryUserInfoByStuId(request.getConfirmUserId()).getUserId());
            //调用管理端修改
            certificateBO = updateByTeacher(request, context);
        } else {
            //调用用户修改
            certificateBO = updateByStudent(request, context);
        }
        return certificateBO;
    }

    @Override
    public CertificateBO updateByStudent(CertificateRequest request, OperateContext context) {
        //证书存在异常抛出
        CertificateBO certificateBO = judgeIsExit(request);
        //证书过审异常抛出
        judgeState(certificateBO);
        certificateBO = Modify(request, context);
        return certificateBO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByStudent(CertificateRequest request, OperateContext context) {
        //证书类型异常抛出
        CertificateTypeEnum certificateTypeEnum = judgeCertificateType(request);
        //证书存在异常抛出
        CertificateBO certificateBO = judgeIsExit(request);
        //证书过审异常抛出
        judgeState(certificateBO);
        delete(request, context);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(CertificateRequest request, OperateContext context) {
        //证书类型异常抛出
        CertificateTypeEnum certificateTypeEnum = judgeCertificateType(request);
        //证书存在异常抛出
        CertificateBO certificateBO = judgeIsExit(request);
        switch (certificateTypeEnum) {
            //四六级证书 删除
            case CET_4_6:
                //资格证书
            case QUALIFICATIONS: {
                qualificationsRepoService.deleteByCertificateIdAndUserId(request.getCertificateId(), request.getUserId());
                break;
            }
            //竞赛证书
            case COMPETITION: {
                //判断接受参数获取团队id  和获取证书记录团队id是否相同
                AssertUtil.assertTrue(certificateBO.getTeamId().equals(request.getTeamId()), "竞赛团队id不存在");
                competitionRepoService.deleteAllByTeamId(request.getTeamId());
                break;
            }
            //技能证书
            case SKILL: {
                skillRepoService.deleteByCertificateIdAndUserId(request.getCertificateId(), request.getUserId());
                break;
            }
            //异常
            default: {
                throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getCode(), "证书类型不存在");
            }
        }
    }

    @Override
    public CertificateBO findByCertificateId(String certificateId) {
        // 1.查找资格证书 2.查找技能证书 3.查找竞赛证书
        CertificateBO certificateBO = qualificationsRepoService.queryByCertificateId(certificateId);
        if (certificateBO == null) {
            certificateBO = skillRepoService.queryByCertificateId(certificateId);
        }
        if (certificateBO == null) {
            certificateBO = competitionRepoService.queryByCertificateId(certificateId);
            //竞赛证书 团队成员和审核员 UserId 转 stuId
            competitionUserIdCovert(certificateBO);
        }
        AssertUtil.assertNotNull(certificateBO, "未查找到该证书");
        //certificateType 由QUALIFICATIONS 改为CET4_6
        if (CertificateTypeEnum.CET_4_6.getCode().equals(certificateBO.getType())) {
            certificateBO.setCertificateType(CertificateTypeEnum.CET_4_6.getCode());
        }
        return certificateBO;
    }


    @Override
    public List<CertificateBO> findAllByCertificateTypeAndUserId(CertificateRequest request, OperateContext context) {
        //类型异常抛出
        CertificateTypeEnum certificateTypeEnum = judgeCertificateType(request);
        List<CertificateBO> certificateBOS;
        switch (certificateTypeEnum) {
            //四六级证书
            case CET_4_6: {
                certificateBOS = qualificationsRepoService.queryCET46(request.getUserId());
                break;
            }
            //资格证书
            case QUALIFICATIONS: {
                certificateBOS = qualificationsRepoService.queryQualificate(request.getUserId());
                break;
            }
            //竞赛证书
            case COMPETITION: {
                certificateBOS = competitionRepoService.queryByUserId(request.getUserId());
                competitionUserIdCovert(certificateBOS);
                break;
            }
            //技能证书
            case SKILL: {
                //同资格证书
                certificateBOS = skillRepoService.queryByUserId(request.getUserId());
                break;
            }
            //异常
            default: {
                throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getCode(), "证书类型不存在");
            }
        }
        return certificateBOS;
    }

    /**
     * 判断证书状态抛出异常
     *
     * @param certificateBO
     * @return
     */
    private void judgeState(CertificateBO certificateBO) {
        AssertUtil.assertTrue(certificateBO.getStatus().equals(CertificateStateEnum.UNREVIEWED.getCode()), "证书已通过审核");
    }

    /**
     * 判断证书类型抛出异常
     *
     * @param request
     * @return
     */
    private CertificateTypeEnum judgeCertificateType(CertificateRequest request) {
        String certificateType = request.getCertificateType();
        CertificateTypeEnum certificateTypeEnum = CertificateTypeEnum.getByCode(certificateType);
        AssertUtil.assertNotNull(certificateTypeEnum, "证书类型不存在");
        return certificateTypeEnum;
    }

    /**
     * 判断证书存在性抛出异常
     * 返回证书实体
     *
     * @param request
     * @return
     */
    private CertificateBO judgeIsExit(CertificateRequest request) {
        //判断证书类型抛出异常
        CertificateTypeEnum certificateTypeEnum = judgeCertificateType(request);
        CertificateBO certificateBO;
        switch (certificateTypeEnum) {
            //四六级证书
            case CET_4_6:
                //资格证书
            case QUALIFICATIONS: {
                //证书存在判断
                certificateBO = qualificationsRepoService.queryByUserIdAndCertificateId(request.getUserId(), request.getCertificateId());
                break;
            }
            //竞赛证书
            case COMPETITION: {
                certificateBO = competitionRepoService.queryByCertificateIdAndUserId(request.getCertificateId(), request.getUserId());
                break;
            }
            //技能证书
            case SKILL: {
                //同资格证书
                certificateBO = skillRepoService.queryByCertificateIdAndUserId(request.getCertificateId(), request.getUserId());
                break;
            }
            //异常
            default: {
                throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getCode(), "证书类型不存在");
            }
        }
        //证书非空判断
        AssertUtil.assertNotNull(certificateBO, "证书不存在");
        return certificateBO;
    }

    /**
     * 转换器 竞赛证书userid转 stuid
     * 审核员 userid转 stuid
     *
     * @param certificateBO
     * @return
     */
    private CertificateBO competitionUserIdCovert(CertificateBO certificateBO) {
        List<String> userIds = new ArrayList<>();
        for (String userid : certificateBO.getWorkUserId()) {
            UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByUserId(userid);
            userIds.add(userInfoBO.getStuId());
        }
        //团队伙伴id转stuId
        certificateBO.setWorkUserId(userIds);
        //审核员id转userId
        if (certificateBO.getConfirmUserId() != null) {
            certificateBO.setConfirmUserId(userInfoRepoService.queryUserInfoByUserId(certificateBO.getConfirmUserId()).getStuId());
        }
        return certificateBO;
    }

    /**
     * 转换器 竞赛证书userid 转stuid
     *
     * @param certificateBOS
     * @return
     */
    private List<CertificateBO> competitionUserIdCovert(List<CertificateBO> certificateBOS) {
        return CollectionUtils.toStream(certificateBOS)
                .filter(Objects::nonNull)
                .map(this::competitionUserIdCovert)
                .collect(Collectors.toList());
    }

    /**
     * 修改证书
     *
     * @param request
     * @param context
     * @return
     */
    private CertificateBO Modify(CertificateRequest request, OperateContext context) {
        CertificateTypeEnum certificateTypeEnum = judgeCertificateType(request);
        CertificateBO certificateBO;
        //证书类型判断
        switch (certificateTypeEnum) {
            //四六级证书
            case CET_4_6: {
                AssertUtil.assertNotNull(request.getRank(), "四六级证书类别不能为空");
                //设置证书种类
                request.setType(certificateTypeEnum.getCode());
                certificateBO = certificateManager.modifyQualifications(request);
                break;
            }
            //资格证书
            case QUALIFICATIONS: {
                AssertUtil.assertNotNull(request.getCertificateName(), "发行证书名字不能为空");
                AssertUtil.assertNotNull(request.getCertificateOrganization(), "发行证书组织不能为空");
                AssertUtil.assertNotNull(request.getType(), "资格证书种类不能为空");
                certificateBO = certificateManager.modifyQualifications(request);
                break;
            }
            //竞赛证书
            case COMPETITION: {
                AssertUtil.assertNotNull(request.getCompetitionName(), "比赛名字不能为空");
                AssertUtil.assertNotNull(request.getRank(), "比赛级别不能为空");
                AssertUtil.assertNotNull(request.getTeamId(), "团队比赛id不能为空");
                //stuid转userid
                List<String> userIds = new ArrayList<>();
                request.getWorkUserId().forEach(stuId -> {
                    UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByStuId(stuId);
                    AssertUtil.assertNotNull(userInfoBO, "队员id不存在");
                    userIds.add(userInfoBO.getUserId());
                });
                request.setWorkUserId(userIds);
                certificateBO = certificateManager.modifyCompetition(request);
                //显示 userid转stuid
                competitionUserIdCovert(certificateBO);
                break;
            }
            //技能证书
            case SKILL: {
                AssertUtil.assertNotNull(request.getCertificateName(), "发行证书名字不能为空");
                AssertUtil.assertNotNull(request.getRank(), "证书等级不能为空");
                certificateBO = certificateManager.modifySkill(request);
                break;
            }
            //异常
            default: {
                throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getCode(), "证书类型不存在");
            }
        }
        return certificateBO;
    }
}
