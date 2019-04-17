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
import us.betahouse.haetae.certificate.enums.CertificateTypeEnum;
import us.betahouse.haetae.certificate.model.basic.CertificateBO;
import us.betahouse.haetae.serviceimpl.certificate.request.CertificateRequest;
import us.betahouse.haetae.serviceimpl.certificate.service.CertificateManagerService;
import us.betahouse.haetae.serviceimpl.certificate.service.CertificateService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.AssertUtil;

import java.util.List;

/**
 * 证书服务实现
 *
 * @author guofan.cp
 * @version : CertificateServiceImpl.java 2019/04/06 8:26 guofan.cp
 */
@Service
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    private CertificateManagerService certificateManagerService;
    @Autowired
    private QualificationsRepoService qualificationsRepoService;
    @Autowired
    private CompetitionRepoService competitionRepoService;
    @Autowired
    private SkillRepoService skillRepoService;

    @Override
    public CertificateBO create(CertificateRequest request, OperateContext context) {
        return certificateManagerService.create(request, context);
    }

    @Override
    public CertificateBO update(CertificateRequest request, OperateContext context) {
        return certificateManagerService.update(request, context);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(CertificateRequest request, OperateContext context) {
        String certificateType = request.getCertificateType();
        CertificateTypeEnum certificateTypeEnum = CertificateTypeEnum.getByCode(certificateType);
        AssertUtil.assertNotNull(certificateTypeEnum, "证书类型不存在");
        switch (certificateTypeEnum) {
            //资格证书
            case QUALIFICATIONS: {
                //证书存在判断
                CertificateBO certificateBO = qualificationsRepoService.queryByUserIdAndCertificateId(request.getUserId(), request.getCertificateId());
                AssertUtil.assertNotNull(certificateBO, "证书不存在");
                //删除证书
                qualificationsRepoService.deleteByCertificateIdAndUserId(request.getCertificateId(), request.getUserId());
                break;
            }
            //竞赛证书
            case COMPETITION: {
                //团队id存在检验
                AssertUtil.assertNotNull(request.getTeamId(), "证书不存在");
                CertificateBO certificateBO = competitionRepoService.queryByCertificateIdAndUserId(request.getCertificateId(), request.getUserId());
                //证书存在判断
                AssertUtil.assertNotNull(certificateBO, "证书不存在");
                competitionRepoService.deleteAllByTeamId(request.getTeamId());
                break;
            }
            //技能证书
            case SKILL: {
                //同资格证书
                CertificateBO certificateBO = skillRepoService.queryByCertificateIdAndUserId(request.getCertificateId(), request.getUserId());
                AssertUtil.assertNotNull(certificateBO, "证书不存在");
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
    public CertificateBO findByUserIdAndCertificateId(String userId, String certificateId) {
        return null;
    }

    @Override
    public List<CertificateBO> findByUserIdAndCertificateName(CertificateRequest request, OperateContext context) {
        return null;
    }

    @Override
    public CertificateBO findByCertificateNameAndId(CertificateRequest request, OperateContext context) {
        return null;
    }
}
