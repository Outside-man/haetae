/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.manager;


import us.betahouse.haetae.certificate.model.basic.CertificateBO;
import us.betahouse.haetae.certificate.request.CertificateManagerRequest;

/**
 * 证书管理器
 *
 * @author guofan.cp
 * @version : QualificationsManger.java 2019/04/05 19:54 guofan.cp
 */
public interface CertificateManager {

    /**
     * 创建资格证书
     *
     * @param request
     * @return
     */
    CertificateBO createQualifications(CertificateManagerRequest request);

    /**
     * 创建竞赛证书
     *
     * @param request
     * @return
     */
    CertificateBO createCompetition(CertificateManagerRequest request);

    /**
     * 创建技能证书
     *
     * @param request
     * @return
     */
    CertificateBO createSkill(CertificateManagerRequest request);
    /**
     * 更新资格证书
     *
     * @param request
     * @return
     */
    CertificateBO modifyQualifications(CertificateManagerRequest request);

    /**
     * 更新竞赛证书
     *
     * @param request
     * @return
     */
    CertificateBO modifyCompetition(CertificateManagerRequest request);

    /**
     * 更新技能证书
     *
     * @param request
     * @return
     */
    CertificateBO modifySkill(CertificateManagerRequest request);
}
