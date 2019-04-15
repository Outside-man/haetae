/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.certificate.service;

import us.betahouse.haetae.certificate.model.basic.CertificateBO;
import us.betahouse.haetae.serviceimpl.certificate.request.CertificateRequest;
import us.betahouse.haetae.serviceimpl.common.OperateContext;

/**
 * 证书管理器
 *
 * @author guofan.cp
 * @version : CertificateManagerService.java 2019/04/06 8:26 guofan.cp
 */
public interface CertificateManagerService {

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
     * @param context
     * @return
     */
    void delete(CertificateRequest request, OperateContext context);

}
