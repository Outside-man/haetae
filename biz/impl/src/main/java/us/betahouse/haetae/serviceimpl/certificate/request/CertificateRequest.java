/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.certificate.request;

import us.betahouse.haetae.certificate.request.CertificateManagerRequest;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyRequest;

/**
 * 证书请求管理
 *
 * @author guofan.cp
 * @version : CertificateRequest.java 2019/04/05 10:57 guofan.cp
 */
public class CertificateRequest extends CertificateManagerRequest implements VerifyRequest {

    private static final long serialVersionUID = 2737409488537410501L;

    @Override
    public String getVerifyUserId() {
        return getUserId();
    }
}
