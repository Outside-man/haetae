/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.certificate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.certificate.model.basic.CertificateBO;
import us.betahouse.haetae.serviceimpl.certificate.request.CertificateRequest;
import us.betahouse.haetae.serviceimpl.certificate.service.CertificateManagerService;
import us.betahouse.haetae.serviceimpl.certificate.service.CertificateService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;

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

    @Override
    public CertificateBO create(CertificateRequest request, OperateContext context) {
        return certificateManagerService.create(request,context);
    }

    @Override
    public CertificateBO update(CertificateRequest request, OperateContext context) {
        return certificateManagerService.update(request,context);
    }

    @Override
    public void delete(CertificateRequest request, OperateContext context) {

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
