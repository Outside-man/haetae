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
    void delete(String certificateId);

    /**
     * 修改资格证书
     *
     * @param certificateBO
     * @return
     */
    CertificateBO modify(CertificateBO certificateBO);

    /**
     * 通过id获取竞赛证书详细信息
     *
     * @param certificateId
     * @return
     */
    CertificateBO queryByCertificateId(String certificateId);

    /**
     * 查询证书 通过用户id
     *
     * @param userid
     * @return
     */
    List<CertificateBO> queryByUserid(String userid);

    /**
     * 查找证书 通过证书名字
     *
     * @param certificateName
     * @return
     */
    List<CertificateBO> queryByCertificateName(String certificateName);
}
