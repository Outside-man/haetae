/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.dal.service;

import us.betahouse.haetae.certificate.model.basic.QualificationsBO;

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
     * @param qualificationsBO
     * @return
     */
    QualificationsBO create(QualificationsBO qualificationsBO);

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
     * @param qualificationsBO
     * @return
     */
    QualificationsBO modify(QualificationsBO qualificationsBO);

    /**
     * 通过id获取资格证书详细信息
     *
     * @param certificateId
     * @return
     */
    QualificationsBO queryByCertificateId(String certificateId);

    /**
     * 查询证书 通过用户id
     *
     * @param userid
     * @return
     */
    List<QualificationsBO> queryByUserid(String userid);

    /**
     * 查找证书 通过证书名字
     *
     * @param certificateName
     * @return
     */
    List<QualificationsBO> queryByCertificateName(String certificateName);
}
