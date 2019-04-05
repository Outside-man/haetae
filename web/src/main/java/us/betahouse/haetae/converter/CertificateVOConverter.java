/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.converter;

import us.betahouse.haetae.certificate.model.basic.CertificateBO;
import us.betahouse.haetae.certificate.model.basic.CompetitionBO;
import us.betahouse.haetae.certificate.model.basic.QualificationsBO;
import us.betahouse.haetae.certificate.model.basic.SkillBO;
import us.betahouse.haetae.model.certificate.vo.CertificateVO;

/**
 * 证书转换VO
 *
 * @author guofan.cp
 * @version : CertificateVOConverter.java 2019/04/05 13:38 guofan.cp
 */
public class CertificateVOConverter {

    /**
     * 资格证书转换VO
     */
    public static CertificateVO convert(QualificationsBO QualificationsBO) {
        CertificateVO certificateVO=new CertificateVO();
        return certificateVO;
    }

    /**
     * 技能证书转换VO
     *
     * @param skillBO
     * @return
     */
    public static CertificateVO convert(SkillBO skillBO){
        CertificateVO certificateVO=new CertificateVO();
        return certificateVO;
    }

    /**
     * 竞赛证书转化VO
     *
     * @param competitionBO
     * @return
     */
    public static CertificateVO convert(CompetitionBO competitionBO){
        CertificateVO certificateVO=new CertificateVO();
        return certificateVO;
    }
}
