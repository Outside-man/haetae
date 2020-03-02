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
import us.betahouse.haetae.certificate.model.basic.CertificateBO;
import us.betahouse.haetae.certificate.request.CertificateManagerRequest;
import us.betahouse.haetae.serviceimpl.certificate.constant.CertificatePermType;
import us.betahouse.haetae.serviceimpl.certificate.request.CertificateConfirmRequest;
import us.betahouse.haetae.serviceimpl.certificate.request.CertificateRequest;
import us.betahouse.haetae.serviceimpl.certificate.service.CertificateManagerService;
import us.betahouse.haetae.serviceimpl.certificate.service.CertificateService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyPerm;
import us.betahouse.haetae.serviceimpl.user.enums.UserRoleCode;
import us.betahouse.haetae.user.dal.model.perm.UserRoleRelationDO;
import us.betahouse.haetae.user.dal.repo.perm.RoleDORepo;
import us.betahouse.haetae.user.dal.repo.perm.UserRoleRelationDORepo;
import us.betahouse.haetae.user.dal.service.RoleRepoService;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 证书管理器实现
 * 需要鉴权（管理员用户）
 *
 * @author guofan.cp
 * @version : CertificateManagerServiceImpl.java 2019/04/06 8:26 guofan.cp
 */
@Service
public class CertificateManagerServiceImpl implements CertificateManagerService {

    @Autowired
    private QualificationsRepoService qualificationsRepoService;
    @Autowired
    private CompetitionRepoService competitionRepoService;
    @Autowired
    private SkillRepoService skillRepoService;
    @Autowired
    private CertificateService certificateService;
    @Autowired
    private UserInfoRepoService userInfoRepoService;
    @Autowired
    private RoleRepoService roleRepoService;
    @Autowired
    private UserRoleRelationDORepo userRoleRelationDORepo;
    @Autowired
    private RoleDORepo roleDORepo;


    @Override
    @VerifyPerm(permType = CertificatePermType.DELETE_CERTIFICATE)
    public void delete(CertificateRequest request, OperateContext context) {
        certificateService.delete(request, context);
    }


    @Override
    @VerifyPerm(permType = CertificatePermType.GET_CERTIFICATES)
    public CertificateBO findByCertificateId(CertificateRequest request, OperateContext context) {
        return certificateService.findByCertificateId(request.getCertificateId());
    }

    @Override
    @VerifyPerm(permType = CertificatePermType.MANAGER_CONFIRM)
    public void bindConfirmUser(CertificateConfirmRequest request, OperateContext context) {
        UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByStuId(request.getConfirmStuId());
        AssertUtil.assertNotNull(userInfoBO, "用户不存在或该用户未绑定个人信息");
        //用户绑定角色
        roleRepoService.userBindRolesByCode(userInfoBO.getUserId(), UserRoleCode.CERTIFICATE_CONFIRM);
    }

    @Override
    @VerifyPerm(permType = CertificatePermType.MANAGER_CONFIRM)
    public void delteConfirmUser(CertificateConfirmRequest request, OperateContext context) {
        //用户信息验证
        UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByStuId(request.getConfirmStuId());
        AssertUtil.assertNotNull(userInfoBO, "用户不存在或该用户未绑定个人信息");
        //获取证书审核员角色id
        String roleId = roleDORepo.findByRoleCode(UserRoleCode.CERTIFICATE_CONFIRM.getCode()).getRoleId();
        AssertUtil.assertNotNull(roleId, "证书审核员权限不存在");
        //解除绑定
        List<String> userid = new ArrayList<>();
        userid.add(userInfoBO.getUserId());
        roleRepoService.usersUnbindRole(userid, roleId);
    }

    @Override
    @VerifyPerm(permType = CertificatePermType.MANAGER_CONFIRM)
    public List<UserInfoBO> getConfirmUser(CertificateConfirmRequest request, OperateContext context) {
        //获取角色号
        String roleId = roleDORepo.findByRoleCode(UserRoleCode.CERTIFICATE_CONFIRM.getCode()).getRoleId();
        //获取角色下的所有用户userid
        List<UserRoleRelationDO> userRoleRelationDOS = userRoleRelationDORepo.findAllByRoleId(roleId);
        //返回用户全部信息
        List<UserInfoBO> userInfoBOS = new ArrayList<>();
        userRoleRelationDOS.forEach(userRoleRelationDO -> {
            userInfoBOS.add(userInfoRepoService.queryUserInfoByUserId(userRoleRelationDO.getUserId()));
        });
        return userInfoBOS;
    }

    @Override
    public List<String> importCertificate(String url) {
        return null;
    }

    @Override
    @VerifyPerm(permType = CertificatePermType.CERTIFICATE_MANAGER)
    public List<CertificateBO> fetchAllCertificateList(CertificateManagerRequest request, OperateContext context) {
        List<CertificateBO> certificateBOS = new ArrayList<>();
        certificateBOS.addAll(qualificationsRepoService.queryAllCET46());
        certificateBOS.addAll(qualificationsRepoService.queryAllQualificate());
        certificateBOS.addAll(competitionRepoService.queryAll());
        certificateBOS.addAll(skillRepoService.queryAll());
        return certificateBOS;
    }

    @Override
    @VerifyPerm(permType = CertificatePermType.GET_CERTIFICATES)
    public List<CertificateBO> fetchAllCertificate(CertificateConfirmRequest request, OperateContext context) {
        List<CertificateBO> certificateBOS = new ArrayList<>();
        String studId = request.getConfirmStuId();
        String stuUserId = userInfoRepoService.queryUserInfoByStuId(studId).getUserId();
        certificateBOS.addAll(qualificationsRepoService.queryCET46(stuUserId));
        certificateBOS.addAll(qualificationsRepoService.queryQualificate(stuUserId));
        certificateBOS.addAll(skillRepoService.queryByUserId(stuUserId));
        //竞赛证书查找后userid转换stuid
        certificateBOS.addAll(competitionUserIdCovert(competitionRepoService.queryByUserId(stuUserId)));
        return certificateBOS;
    }

    @Override
    @VerifyPerm(permType = CertificatePermType.GET_CERTIFICATES)
    public List<CertificateBO> fetchUnreviedCertificate(CertificateConfirmRequest request, OperateContext context) {
        List<CertificateBO> certificateBOS = fetchAllCertificate(request, context);
        return CollectionUtils.toStream(certificateBOS)
                .filter(certificateBO -> certificateBO.getStatus().equals(CertificateStateEnum.UNREVIEWED.getCode()))
                .collect(Collectors.toList());
    }

    @Override
    @VerifyPerm(permType = CertificatePermType.MODIFY_CERTIFICATE)
    @Transactional(rollbackFor = Exception.class)
    public CertificateBO confirmCertificate(CertificateConfirmRequest request, OperateContext context) {
        CertificateBO certificateBO = certificateService.findByCertificateId(request.getCertificateId());
        AssertUtil.assertNotNull(certificateBO, "证书id不存在");
        //更改证书 状态
        certificateBO.setStatus(CertificateStateEnum.APPROVED.getCode());
        //添加审核员userId
        certificateBO.setConfirmUserId(request.getUserId());
        //保存信息 更新信息
        CertificateTypeEnum certificateTypeEnum = CertificateTypeEnum.getByCode(request.getCertificateType());
        AssertUtil.assertNotNull(certificateTypeEnum, "证书类型不存在");
        //证书类型判断(四种类型，四六级与资格证书同表)
        switch (certificateTypeEnum) {
            //四六级证书
            case CET_4_6:
                //资格证书
            case QUALIFICATIONS: {
                qualificationsRepoService.modify(certificateBO);
                break;
            }
            //竞赛证书
            case COMPETITION: {
                //获取竞赛证书团队信息
                String teamid = certificateBO.getTeamId();
                List<CertificateBO> certificateBOList = competitionRepoService.queryByTeamId(teamid);
                for (CertificateBO certificateBO1 : certificateBOList) {
                    certificateBO.setCertificateId(certificateBO1.getCertificateId());
                    certificateBO.setWorkUserId(certificateBO1.getWorkUserId());
                    competitionRepoService.modify(certificateBO);
                }
                competitionUserIdCovert(certificateBO);
                break;
            }
            //技能证书
            case SKILL: {
                skillRepoService.modify(certificateBO);
                break;
            }
            //异常
            default: {
                throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getCode(), "证书类型不存在");
            }
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
     * 转换器 竞赛证书userid转 stuid
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
        certificateBO.setWorkUserId(userIds);
        return certificateBO;
    }
}

