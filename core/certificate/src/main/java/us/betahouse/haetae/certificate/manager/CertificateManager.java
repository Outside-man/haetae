/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.manager;


import us.betahouse.haetae.certificate.model.basic.CertificateBO;
import us.betahouse.haetae.certificate.request.CertificateManagerRequest;

import java.util.List;

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

    /**
     * 通过证书id获取证书信息
     *
     * @param certificateId
     * @return
     */
    CertificateBO findByCertificateId(String certificateId);

    /**
     * 通过用户id查找证书
     *
     * @param userId
     * @return
     */
    List<CertificateBO> findByUserId(String userId);

    /**
     * 证书记录通过证书名称和用户id
     *
     * @param request
     * @return
     */
    List<CertificateBO> findByUserIdAndCertificateName(CertificateManagerRequest request);
}
