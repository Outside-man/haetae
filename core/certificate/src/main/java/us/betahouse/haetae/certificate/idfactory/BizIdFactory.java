/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.idfactory;

/**
 * @author guofan.cp
 * @version : BizIdFactory.java 2019/04/02 12:34 guofan.cp
 */
public interface BizIdFactory {
    /**
     * @Description: 生成资格证书id
     * @param:
     */
    String getCertificateId();

    /**
     * @Description: 生成竞赛证书id
     * @param:
     */
    String getCompetitionId();

    /**
     * @Description: 生成技能证书id
     * @param:
     */
    String getSkillId();
}
