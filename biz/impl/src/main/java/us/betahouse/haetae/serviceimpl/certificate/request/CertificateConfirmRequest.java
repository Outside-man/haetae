/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.certificate.request;

import us.betahouse.haetae.certificate.request.CertificateRecordRequest;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyRequest;

import java.util.List;

/**
 * 证书审核请求
 *
 * @author guofan.cp
 * @version : CertificateConfirmRequest.java 2019/04/30 8:11 guofan.cp
 */
public class CertificateConfirmRequest extends CertificateRecordRequest implements VerifyRequest {

    private static final long serialVersionUID = -5634638929278264524L;

    private List<String> stuId;

    private String localURL;

    @Override
    public String getVerifyUserId() {
        return getConfirmUserId();
    }

    public List<String> getStuId() {
        return stuId;
    }

    public void setStuId(List<String> stuId) {
        this.stuId = stuId;
    }

    public String getLocalURL() {
        return localURL;
    }

    public void setLocalURL(String localURL) {
        this.localURL = localURL;
    }
}
