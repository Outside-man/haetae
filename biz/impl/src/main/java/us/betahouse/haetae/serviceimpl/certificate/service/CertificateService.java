/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.certificate.service;

import us.betahouse.haetae.certificate.model.basic.CertificateBO;
import us.betahouse.haetae.serviceimpl.certificate.request.CertificateRequest;
import us.betahouse.haetae.serviceimpl.common.OperateContext;

import java.util.List;

/**
 * 证书服务
 *
 * @author guofan.cp
 * @version : CertificateService.java 2019/04/05 10:55 guofan.cp
 */
public interface CertificateService {

    /**
     * 创建证书记录
     *
     * @param request
     * @return
     */
    CertificateBO create(CertificateRequest request, OperateContext context);

    /**
     * 修改证书记录
     *
     * @param request
     * @return
     */
    CertificateBO update(CertificateRequest request, OperateContext context);

    /**
     * 删除证书记录
     *
     * @param request
     * @return
     */
    void delete(CertificateRequest request, OperateContext context);

    /**
     * 查找证书通过证书id
     *
     * @param certificateId
     * @return
     */
    CertificateBO findByCertificateId(String certificateId);

    /**
     * 通过用户id查找证书
     * @param userId
     * @return
     */
    List<CertificateBO> findByUserId(String userId);

    /**
     * 证书记录通过证书名称和用户id
     *
     * @param request
     * @param context
     * @return
     */
    List<CertificateBO> findByCertificateNameAndUserId(CertificateRequest request, OperateContext context);

    /**
     * 证书记录通过类型和证书id
     *
     * @param request
     * @param context
     * @return
     */
    CertificateBO findByCertificateNameAndId(CertificateRequest request, OperateContext context);
}
