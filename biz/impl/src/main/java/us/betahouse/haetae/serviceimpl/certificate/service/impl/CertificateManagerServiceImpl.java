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
import us.betahouse.haetae.certificate.manager.CertificateManager;
import us.betahouse.haetae.certificate.model.basic.CertificateBO;
import us.betahouse.haetae.serviceimpl.certificate.request.CertificateRequest;
import us.betahouse.haetae.serviceimpl.certificate.service.CertificateManagerService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.AssertUtil;

/**
 * 证书管理器实现
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
    private SkillRepoService skillRepoServicel;
    @Autowired
    private CertificateManager certificateManager;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CertificateBO create(CertificateRequest request, OperateContext context) {
        String certificateType = request.getCertificateType();
        CertificateTypeEnum certificateTypeEnum = CertificateTypeEnum.getByCode(certificateType);
        CertificateBO certificateBO = new CertificateBO();
        AssertUtil.assertNotNull(certificateTypeEnum, "证书类型不存在");
        //证书类型判断(三种)
        switch (certificateTypeEnum) {
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
                certificateBO = certificateManager.createCompetition(request);
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
    public CertificateBO update(CertificateRequest request, OperateContext context) {
        String certificateType = request.getCertificateType();
        CertificateTypeEnum certificateTypeEnum = CertificateTypeEnum.getByCode(certificateType);
        CertificateBO certificateBO = new CertificateBO();
        AssertUtil.assertNotNull(certificateTypeEnum, "证书类型不存在");
        //证书类型判断(三种)
        switch (certificateTypeEnum) {
            //资格证书
            case QUALIFICATIONS: {
                AssertUtil.assertNotNull(request.getCertificateOrganization(), "发行证书组织不能为空");
                AssertUtil.assertNotNull(request.getCertificateType(), "资格证书种类不能为空");
                certificateBO = certificateManager.modifyQualifications(request);
                break;
            }
            //竞赛证书
            case COMPETITION: {
                AssertUtil.assertNotNull(request.getCompetitionName(), "比赛名字不能为空");
                AssertUtil.assertNotNull(request.getType(), "比赛类型不能为空");
                AssertUtil.assertNotNull(request.getRank(), "比赛级别不能为空");
                certificateBO = certificateManager.modifyCompetition(request);
                break;
            }
            //技能证书
            case SKILL: {
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

    @Override
    public void delete(CertificateRequest request, OperateContext context) {

    }
}
