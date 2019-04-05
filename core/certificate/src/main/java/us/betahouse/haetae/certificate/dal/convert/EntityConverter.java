/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.dal.convert;

import com.alibaba.fastjson.JSON;
import us.betahouse.haetae.certificate.dal.model.CompetitionDO;
import us.betahouse.haetae.certificate.dal.model.QualificationsDO;
import us.betahouse.haetae.certificate.dal.model.SkillDO;
import us.betahouse.haetae.certificate.model.basic.CompetitionBO;
import us.betahouse.haetae.certificate.model.basic.QualificationsBO;
import us.betahouse.haetae.certificate.model.basic.SkillBO;

import java.util.Map;

/**
 * 实体转换器
 *
 * @author guofan.cp
 * @version : EntityConverter.java 2019/04/05 13:08 guofan.cp
 */
final public class EntityConverter {

    /**
     * 资格证书DO2BO
     *
     * @param qualificationsDO
     * @return
     */
    @SuppressWarnings("unchecked")
    public static QualificationsBO convert(QualificationsDO qualificationsDO) {
        if (qualificationsDO == null) {
            return null;
        }
        QualificationsBO qualificationsBO = new QualificationsBO();
        qualificationsBO.setCertificateId(qualificationsDO.getCertificateId());
        qualificationsBO.setCertificateName(qualificationsDO.getCertificateName());
        qualificationsBO.setCertificateOrganization(qualificationsDO.getCertificateOrganization());
        qualificationsBO.setCertificatePublishTime(qualificationsDO.getCertificatePublishTime());
        qualificationsBO.setStatus(qualificationsDO.getStatus());
        qualificationsBO.setType(qualificationsDO.getType());
        qualificationsBO.setExtInfo(JSON.parseObject(qualificationsDO.getExtInfo(), Map.class));
        return qualificationsBO;
    }

    /**
     * 资格证书BO2DO
     *
     * @param qualificationsBO
     * @return
     */
    public static QualificationsDO convert(QualificationsBO qualificationsBO) {
        if (qualificationsBO == null) {
            return null;
        }
        QualificationsDO qualificationsDO = new QualificationsDO();
        qualificationsDO.setCertificateId(qualificationsBO.getCertificateId());
        qualificationsDO.setCertificateName(qualificationsBO.getCertificateName());
        qualificationsDO.setCertificateNumber(qualificationsBO.getCertificateNumber());
        qualificationsDO.setCertificateOrganization(qualificationsBO.getCertificateOrganization());
        qualificationsDO.setCertificatePublishTime(qualificationsBO.getCertificatePublishTime());
        qualificationsDO.setExtInfo(JSON.toJSONString(qualificationsBO.getExtInfo()));
        qualificationsDO.setType(qualificationsBO.getType());
        return qualificationsDO;
    }

//    /**
//     * 技能证书DO2BO
//     *
//     * @param skillDO
//     * @return
//     */
//    public static SkillBO convert(SkillDO skillDO) {
//
//    }
//
//    /**
//     * 技能证书BO2DO
//     *
//     * @param skillBO
//     * @return
//     */
//    public static SkillDO convert(SkillBO skillBO) {
//
//    }
//
//    /**
//     * 竞赛证书DO2B0
//     *
//     * @param competitionDO
//     * @return
//     */
//    public static CompetitionBO convert(CompetitionDO competitionDO) {
//
//    }
//
//    /**
//     * 竞赛证书B02DO
//     *
//     * @param competitionBO
//     * @return
//     */
//    public static CompetitionDO convert(CompetitionBO competitionBO) {
//
//    }
}
