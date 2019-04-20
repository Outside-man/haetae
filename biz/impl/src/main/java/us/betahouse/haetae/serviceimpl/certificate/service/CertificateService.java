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
 * 无需鉴权
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
     * 通过证书id查找证书
     *
     * @param certificateId
     * @return
     */
    CertificateBO findByCertificateId(String certificateId);

    /**
     * 证书记录通过类型和证书id
     *
     * @param request
     * @param context
     * @return
     */
    CertificateBO findByCertificateTypeAndId(CertificateRequest request, OperateContext context);

    /**
     * 单种证书全部记录通过用户id和证书类型
     *
     * @param request
     * @param context
     * @return
     */
    List<CertificateBO> findAllByCertificateTypeAndUserId(CertificateRequest request, OperateContext context);
}
