/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.dal.service;

import us.betahouse.haetae.certificate.dal.model.CompetitionDO;
import us.betahouse.haetae.certificate.model.basic.CertificateBO;

import java.util.List;

/**
 * 比赛证书仓储
 *
 * @author guofan.cp
 * @version : CompetitionRepoService.java 2019/04/05 12:41 guofan.cp
 */
public interface CompetitionRepoService {

    /**
     * 创建竞赛证书
     *
     * @param certificateBO
     * @return
     */
    CertificateBO create(CertificateBO certificateBO);

    /**
     * （id）删除竞赛证书
     *
     * @param certificateId
     * @return
     */
    void delete(String certificateId);

    /**
     * （teamId）删除竞赛证书
     *
     * @param teamId
     * @return
     */
    void deleteAllByTeamId(String teamId);

    /**
     * 修改竞赛证书
     *
     * @param certificateBO
     * @return
     */
    CertificateBO modify(CertificateBO certificateBO);

    /**
     * 通过id获取资格证书详细信息
     *
     * @param certificateId
     * @return
     */
    CertificateBO queryByCertificateId(String certificateId);

    /**
     * 查询证书 通过用户id
     *
     * @param userId
     * @return
     */
    List<CertificateBO> queryByUserId(String userId);

    /**
     * 查找证书 通过证书名字
     *
     * @param competitionName
     * @return
     */
    List<CertificateBO> queryByCompetitionName(String competitionName);
    /**
     * 通过团队id获取证书记录
     * @param teamId
     * @return
     */
    List<CertificateBO> queryByTeamId(String teamId);

    /**
     * 查找证书 通过证书id和学生id
     *
     * @param certificateId
     * @param userId
     * @return
     */
    CertificateBO queryByCertificateIdAndUserId(String certificateId, String userId);
}
