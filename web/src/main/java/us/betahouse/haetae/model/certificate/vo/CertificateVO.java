/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.model.certificate.vo;

import us.betahouse.haetae.certificate.model.basic.CertificateBO;
import us.betahouse.haetae.user.model.basic.perm.UserBO;

/**
 * @author guofan.cp
 * @version : CertificateVO.java 2019/04/05 13:39 guofan.cp
 */
public class CertificateVO {

    private static final long serialVersionUID = 8932810568264423329L;

    private CertificateBO certificate;

    private UserBO creator;

    public CertificateBO getCertificate() {
        return certificate;
    }

    public void setCertificate(CertificateBO certificate) {
        this.certificate = certificate;
    }

    public UserBO getCreator() {
        return creator;
    }

    public void setCreator(UserBO creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "CertificateVO{" +
                "certificate=" + certificate +
                ", creator=" + creator +
                '}';
    }

}
