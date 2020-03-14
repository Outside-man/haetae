/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.certificate.dal.service.CompetitionRepoService;
import us.betahouse.haetae.certificate.dal.service.QualificationsRepoService;
import us.betahouse.haetae.certificate.dal.service.SkillRepoService;
import us.betahouse.haetae.certificate.enums.CertificateExtInfoKey;
import us.betahouse.haetae.certificate.enums.CertificateStateEnum;
import us.betahouse.haetae.certificate.enums.CertificateTypeEnum;
import us.betahouse.haetae.certificate.manager.CertificateManager;
import us.betahouse.haetae.certificate.model.basic.CertificateBO;
import us.betahouse.haetae.certificate.request.CertificateManagerRequest;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.AssertUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 资格证书管理器实现
 *
 * @author guofan.cp
 * @version : QualificationsManagerImpl.java 2019/04/05 19:54 guofan.cp
 */
@Service
public class CertificateManagerImpl implements CertificateManager {

    @Autowired
    private QualificationsRepoService qualificationsRepoService;
    @Autowired
    private CompetitionRepoService competitionRepoService;
    @Autowired
    private SkillRepoService skillRepoService;

    @Override
    public CertificateBO createQualifications(CertificateManagerRequest request) {
        //资格证书类型判断
        String type = request.getType();
        CertificateTypeEnum typeEnum = CertificateTypeEnum.getByCode(type);
        AssertUtil.assertNotNull(typeEnum, "资格证书类型不存在");
        CertificateBO certificateBO = new CertificateBO();
        //资格证书 必须属性
        certificateBO.setCertificateType(CertificateTypeEnum.QUALIFICATIONS.getCode());
        certificateBO.setCertificateName(request.getCertificateName());
        certificateBO.setType(request.getType());
        certificateBO.setUserId(request.getUserId());
        certificateBO.setRank(request.getRank());
        certificateBO.setCertificateOrganization(request.getCertificateOrganization());
        certificateBO.setCertificatePublishTime(new Date(request.getCertificatePublishTime()));
        certificateBO.setCertificateNumber(request.getCertificateNumber());
        //设置证书状态  待审核
        certificateBO.setStatus(CertificateStateEnum.UNREVIEWED.getCode());
        certificateBO = setExtInfos(certificateBO, request);
        //设置证书图片路径
        certificateBO.setPictureUrl(request.getPictureUrl());
        switch (typeEnum) {
            //国际资格证书  参数同普通证书相同
            case INTERNATIONAL_QUALIFICATIONS:
                //普通资格证书
            case NORMAL_QUALIFICATIONS: {
                certificateBO = qualificationsRepoService.create(certificateBO);
                return certificateBO;
            }
            //教师资格证书
            case TEACHER_QUALIFICATIONS: {
                AssertUtil.assertNotNull(request.getExtInfo().get(CertificateExtInfoKey.TEACHER_LEVEL.getCode()), "教师资格证,学科不能为空");
                AssertUtil.assertNotNull(request.getExtInfo().get(CertificateExtInfoKey.TEACHER_SUBJECT.getCode()), "教师资格证,教师等级不能为空");
                //教师资格证书额外属性添加教师资格等级/学科
                certificateBO.putExtInfo(CertificateExtInfoKey.TEACHER_LEVEL.getCode(), request.getExtInfo().get(CertificateExtInfoKey.TEACHER_LEVEL.getCode()));
                certificateBO.putExtInfo(CertificateExtInfoKey.TEACHER_SUBJECT.getCode(), request.getExtInfo().get(CertificateExtInfoKey.TEACHER_SUBJECT.getCode()));
                certificateBO = qualificationsRepoService.create(certificateBO);
                return certificateBO;
            }
            //四六级证书
            case CET_4_6: {
                certificateBO.setCertificateGrade(request.getCertificateGrade());
                certificateBO = qualificationsRepoService.create(certificateBO);
                return certificateBO;
            }
            //异常
            default: {
                throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS, "资格证书归属类别不存在");
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CertificateBO createCompetition(CertificateManagerRequest request) {
        CertificateBO certificateBO = new CertificateBO();
        if (request.getTeacher() != null) {
            certificateBO.setTeacher(request.getTeacher());
        }
        certificateBO.setCertificateType(CertificateTypeEnum.COMPETITION.getCode());
        certificateBO.setCompetitionName(request.getCompetitionName());
        certificateBO.setTeamName(request.getTeamName());
        certificateBO.setUserId(request.getUserId());
        certificateBO.setCertificateOrganization(request.getCertificateOrganization());
        certificateBO.setCertificatePublishTime(new Date(request.getCertificatePublishTime()));
        certificateBO.setWorkUserId(request.getWorkUserId());
        certificateBO.setRank(request.getRank());
        //证书图片路径
        certificateBO.setPictureUrl(request.getPictureUrl());
        //设置证书状态 待审核
        certificateBO.setStatus(CertificateStateEnum.UNREVIEWED.getCode());
        //放入拓展信息
        certificateBO = setExtInfos(certificateBO, request);
        //当前用户是否是队员
        boolean includeUserId = false;
        AssertUtil.assertNotNull(request.getWorkUserId(), "队友学号不能为空");
        //目前团队成员人数限制不能超过五个人,把自己的学号也要填进去 方便多条记录队友属性统一字段
        AssertUtil.assertTrue(request.getWorkUserId().size() <= 5, "队友人数不能超过五个人");
        CertificateBO thisCertificate = new CertificateBO();
        //返回单个BO  创建成员等多条记录(每个记录中)
        for (String userid : request.getWorkUserId()) {
            //更改每条记录中学生id 传参引用证书id会被修改
            certificateBO.setUserId(userid);
            certificateBO.setCertificateId(null);
            competitionRepoService.create(certificateBO);
            //判断当前用户id与创建记录的学生id是否相同  返回当前对象记录
            if (certificateBO.getUserId().equals(request.getUserId())) {
                thisCertificate = certificateBO;
                includeUserId = true;
            }
        }
        AssertUtil.assertTrue(includeUserId, "只能由队员创建记录");
        return thisCertificate;
    }

    @Override
    public CertificateBO createSkill(CertificateManagerRequest request) {
        CertificateBO certificateBO = new CertificateBO();
        certificateBO.setCertificateType(CertificateTypeEnum.SKILL.getCode());
        certificateBO.setUserId(request.getUserId());
        certificateBO.setCertificateName(request.getCertificateName());
        certificateBO.setCertificatePublishTime(new Date(request.getCertificatePublishTime()));
        certificateBO.setRank(request.getRank());
        certificateBO.setCertificateNumber(request.getCertificateNumber());
        certificateBO.setPictureUrl(request.getPictureUrl());
        //设置状态 待审核
        certificateBO.setStatus(CertificateStateEnum.UNREVIEWED.getCode());
        //额外信息插入extinfo
        certificateBO = setExtInfos(certificateBO, request);
        //证书有效时间
        if (request.getExpirationTime() != null) {
            certificateBO.setExpirationTime(new Date(request.getExpirationTime()));
        }
        certificateBO = skillRepoService.create(certificateBO);
        return certificateBO;
    }

    @Override
    public CertificateBO modifyQualifications(CertificateManagerRequest request) {
        //类型再判断一次吧 然后拓展信息再判断一次
        CertificateTypeEnum certificateTypeEnum = CertificateTypeEnum.getByCode(request.getType());
        AssertUtil.assertNotNull(certificateTypeEnum, "证书类型不存在");
        CertificateBO certificateBO = new CertificateBO();
        certificateBO.setCertificateId(request.getCertificateId());
        certificateBO.setType(request.getType());
        certificateBO.setCertificateName(request.getCertificateName());
        certificateBO.setCertificatePublishTime(new Date(request.getCertificatePublishTime()));
        certificateBO.setCertificateOrganization(request.getCertificateOrganization());
        certificateBO.setRank(request.getRank());
        certificateBO.setConfirmUserId(request.getConfirmUserId());
        certificateBO.setCertificateNumber(request.getCertificateNumber());
        certificateBO.setPictureUrl(request.getPictureUrl());
        certificateBO = setExtInfos(certificateBO, request);
        switch (certificateTypeEnum) {
            //国际资格证书  参数同普通证书相同
            case INTERNATIONAL_QUALIFICATIONS:
                //普通资格证书
            case NORMAL_QUALIFICATIONS: {
                certificateBO = qualificationsRepoService.modify(certificateBO);
                return certificateBO;
            }
            //教师资格证书
            case TEACHER_QUALIFICATIONS: {
                AssertUtil.assertNotNull(request.getExtInfo().get(CertificateExtInfoKey.TEACHER_LEVEL.getCode()), "教师资格证,学科不能为空");
                AssertUtil.assertNotNull(request.getExtInfo().get(CertificateExtInfoKey.TEACHER_SUBJECT.getCode()), "教师资格证,教师等级不能为空");
                //教师资格证书额外属性添加教师资格等级/学科
                certificateBO.putExtInfo(CertificateExtInfoKey.TEACHER_LEVEL.getCode(), request.getExtInfo().get(CertificateExtInfoKey.TEACHER_LEVEL.getCode()));
                certificateBO.putExtInfo(CertificateExtInfoKey.TEACHER_SUBJECT.getCode(), request.getExtInfo().get(CertificateExtInfoKey.TEACHER_SUBJECT.getCode()));
                certificateBO = qualificationsRepoService.modify(certificateBO);
                return certificateBO;
            }
            case CET_4_6: {
                AssertUtil.assertNotNull(request.getCertificateGrade(), "成绩不能为空");
                certificateBO.setCertificateGrade(request.getCertificateGrade());
                certificateBO = qualificationsRepoService.modify(certificateBO);
                return certificateBO;
            }
            //异常
            default: {
                throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS, "资格证书归属类别不存在");
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CertificateBO modifyCompetition(CertificateManagerRequest request) {
        AssertUtil.assertNotNull(request.getWorkUserId(), "学号字段不能为空");
        //目前团队成员人数限制不能超过五个人,把自己的学号也要填进去 方便多条记录队友属性统一字段
        AssertUtil.assertTrue(request.getWorkUserId().size() <= 5, "队友人数不能超过五个人");
        //修改证书信息 传入参数 全部修改 然后队友如果改变 新队友复制一份 旧队友删除
        CertificateBO certificateBO = new CertificateBO();
        certificateBO.setCompetitionName(request.getCompetitionName());
        certificateBO.setRank(request.getRank());
        certificateBO.setTeamName(request.getTeamName());
        certificateBO.setCertificateOrganization(request.getCertificateOrganization());
        certificateBO.setCertificatePublishTime(new Date(request.getCertificatePublishTime()));
        certificateBO.setWorkUserId(request.getWorkUserId());
        certificateBO.setTeamId(request.getTeamId());
        certificateBO.setConfirmUserId(request.getConfirmUserId());
        certificateBO.setPictureUrl(request.getPictureUrl());
        //放入拓展信息
        certificateBO = setExtInfos(certificateBO, request);
        //放入指导老师信息
        if (request.getTeacher() != null) {
            certificateBO.setTeacher(request.getTeacher());
        }
        //获取原来竞赛团队的证书信息
        List<CertificateBO> certificateBOS = competitionRepoService.queryByTeamId(request.getTeamId());
        //旧团队成员id信息
        List<String> oldUserId = new ArrayList<>();
        certificateBOS.forEach(certificateBO1 -> {
            oldUserId.add(certificateBO1.getUserId());
        });
        List<String> remainUserId = new ArrayList<>(oldUserId);
        //新团队成员id信息
        List<String> newUserId = new ArrayList<>(request.getWorkUserId());
        List<String> newUserId1 = new ArrayList<>(request.getWorkUserId());
        //新成员id信息（添加） newUserdId 中保存的是创建的新成员userid
        newUserId.removeAll(oldUserId);
        //旧成员id信息 （删除）
        oldUserId.removeAll(newUserId1);
        //旧成员id信息 （更改）
        remainUserId.removeAll(oldUserId);
        //获取旧版用户证书BO
        CertificateBO oldCertificateBO = competitionRepoService.queryByCertificateId(request.getCertificateId());
        //获取旧版用户证书状态
        String status = oldCertificateBO.getStatus();
        //更新成员
        for (int i = 0; i < remainUserId.size(); i++) {
            //获取更新证书id
            String certificateId = competitionRepoService.queryByUserIdAndTeamId(remainUserId.get(i), request.getTeamId()).getCertificateId();
            certificateBO.setCertificateId(certificateId);
            competitionRepoService.modify(certificateBO);
        }
        //删除成员
        for (int i = 0; i < oldUserId.size(); i++) {
            String certificateId = competitionRepoService.queryByUserIdAndTeamId(oldUserId.get(i), request.getTeamId()).getCertificateId();
            competitionRepoService.delete(certificateId);
        }
        //添加成员
        for (int i = 0; i < newUserId.size(); i++) {
            certificateBO.setUserId(newUserId.get(i));
            certificateBO.setCertificateId(null);
            certificateBO.setStatus(status);
            competitionRepoService.create(certificateBO);
        }
        certificateBO.setUserId(request.getUserId());
        return certificateBO;
    }

    @Override
    public CertificateBO modifySkill(CertificateManagerRequest request) {
        CertificateBO certificateBO = new CertificateBO();
        certificateBO.setCertificateId(request.getCertificateId());
        certificateBO.setCertificateNumber(request.getCertificateNumber());
        certificateBO.setCertificateName(request.getCertificateName());
        certificateBO.setCertificatePublishTime(new Date(request.getCertificatePublishTime()));
        certificateBO.setRank(request.getRank());
        certificateBO.setConfirmUserId(request.getConfirmUserId());
        certificateBO.setPictureUrl(request.getPictureUrl());
        //设置拓展信息
        certificateBO = setExtInfos(certificateBO, request);
        //技能证书有效期
        if (request.getExpirationTime() != null) {
            certificateBO.setExpirationTime(new Date(request.getExpirationTime()));
        }
        certificateBO = skillRepoService.modify(certificateBO);
        return certificateBO;
    }

    @Override
    public CertificateBO findByCertificateId(String certificateId) {
        CertificateBO certificateBO = qualificationsRepoService.queryByCertificateId(certificateId);
        if (certificateBO == null) {
            certificateBO = competitionRepoService.queryByCertificateId(certificateId);
        }
        if (certificateBO == null) {
            certificateBO = skillRepoService.queryByCertificateId(certificateId);
        }
        return certificateBO;
    }

    @Override
    public List<CertificateBO> findByUserId(CertificateManagerRequest request) {
        return null;
    }

//    @Override
//    public List<CertificateBO> findByUserId(CertificateManagerRequest request) {
//        List<CertificateBO> certificateBOS = qualificationsRepoService.queryByUserIdAndType(request.getUserId(), request.getType());
//        List<CertificateBO> certificateBOS1 = competitionRepoService.queryByUserId(request.getUserId());
//        List<CertificateBO> certificateBOS2 = skillRepoService.queryByUserId(request.getUserId());
//        for (CertificateBO certificateBO : certificateBOS1) {
//            certificateBOS.add(certificateBO);
//        }
//        for (CertificateBO certificateBO : certificateBOS2) {
//            certificateBOS.add(certificateBO);
//        }
//        return certificateBOS;
//    }

    @Override
    public List<CertificateBO> findByUserIdAndCertificateName(CertificateManagerRequest request) {
        List<CertificateBO> certificateBOS = qualificationsRepoService.queryByCertificateNameAndUserId(request.getCertificateName(), request.getUserId());
        if (certificateBOS == null) {
            certificateBOS = competitionRepoService.queryByCertificateNameAndUserId(request.getCertificateName(), request.getUserId());
        }
        if (certificateBOS == null) {
            certificateBOS = skillRepoService.queryByCertificateNameAndUserId(request.getCertificateName(), request.getUserId());
        }
        return certificateBOS;
    }

    static CertificateBO setExtInfos(CertificateBO certificateBO, CertificateManagerRequest request) {
        //放入描述信息
        certificateBO.putExtInfo(CertificateExtInfoKey.DESCRIPTION.getCode(), request.getExtInfo().get(CertificateExtInfoKey.DESCRIPTION.getCode()));
        //放入教师资格
        if (request.getExtInfo().get(CertificateExtInfoKey.TEACHER_LEVEL.getCode()) != null) {
            certificateBO.putExtInfo(CertificateExtInfoKey.TEACHER_LEVEL.getCode(), request.getExtInfo().get(CertificateExtInfoKey.TEACHER_LEVEL.getCode()));
        }
        //放入教师学科
        if (request.getExtInfo().get(CertificateExtInfoKey.TEACHER_SUBJECT.getCode()) != null) {
            certificateBO.putExtInfo(CertificateExtInfoKey.TEACHER_SUBJECT.getCode(), request.getExtInfo().get(CertificateExtInfoKey.TEACHER_SUBJECT.getCode()));
        }
        return certificateBO;
    }
}
