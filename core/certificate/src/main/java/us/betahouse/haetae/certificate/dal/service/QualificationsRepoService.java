/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.dal.service;

import us.betahouse.haetae.certificate.model.basic.CertificateBO;

import java.util.List;

/**
 * 资格证书仓储
 *
 * @author guofan.cp
 * @version : QualificationsRepoService.java 2019/04/05 12:40 guofan.cp
 */
public interface QualificationsRepoService {

    /**
     * 创建资格证书
     *
     * @param certificateBO
     * @return
     */
    CertificateBO create(CertificateBO certificateBO);

    /**
     * 删除资格证书
     *
     * @param certificateId
     * @return
     */
    void deleteByCertificateIdAndUserId(String certificateId, String userId);

    /**
     * 修改资格证书
     *
     * @param certificateBO
     * @return
     */
    CertificateBO modify(CertificateBO certificateBO);

    /**
     * 通过证书id获取证书详细信息
     *
     * @param certificateId
     * @return
     */
    CertificateBO queryByCertificateId(String certificateId);


    /**
     * 查询四六级证书
     * @param userId
     * @return
     */
    List<CertificateBO> queryCET46(String userId);

    /**
     * 查询资格证书（不包括四六级证书）
     *
     * @param userId
     * @return
     */
    List<CertificateBO> queryQualificate(String userId);
    /**
     * 查找证书 通过证书名字
     *
     * @param certificateName
     * @return
     */
    List<CertificateBO> queryByCertificateName(String certificateName);

    /**
     * 查找证书通过证书id和用户id
     *
     * @param userId
     * @param certificateId
     * @return
     */
    CertificateBO queryByUserIdAndCertificateId(String userId, String certificateId);

    /**
     * 通过证书名字与用户id获取证书
     *
     * @param certificateName
     * @param userId
     * @return
     */
    List<CertificateBO> queryByCertificateNameAndUserId(String certificateName, String userId);
}
