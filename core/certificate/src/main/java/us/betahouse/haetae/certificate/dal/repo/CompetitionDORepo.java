/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import us.betahouse.haetae.certificate.dal.model.CompetitionDO;

import java.util.List;

/**
 * @author guofan.cp
 * @version : CompetitionDORepo.java 2019/04/03 14:44 guofan.cp
 */
public interface CompetitionDORepo extends JpaRepository<CompetitionDO, Long> {
    /**
     * 通过证书id查找证书id
     *
     * @param certificateId
     * @return
     */
    CompetitionDO findByCertificateId(String certificateId);

    /**
     * 通过学生id获取全部竞赛证书
     *
     * @param userId
     * @return
     */
    List<CompetitionDO> findByUserId(String userId);

    /**
     * 通过证书id获取证书
     *
     * @param certificateId
     * @param userId
     * @return
     */
    CompetitionDO findByCertificateIdAndUserId(String certificateId, String userId);

    /**
     * 通过证书id删除证书
     *
     * @param certificateId
     * @return
     */
    void deleteByCertificateId(String certificateId);

    /**
     * 通过团队id 删除所有证书
     *
     * @param teamId
     * @return
     */
    void deleteAllByTeamId(String teamId);

    /**
     * 通过竞赛名字获取该竞赛全部获奖记录
     *
     * @param competitionName
     * @return
     */
    List<CompetitionDO> findByCompetitionName(String competitionName);
    /**
     * 通过证书名字与用户id获取证书
     *
     * @param competitionName
     * @param userId
     * @return
     */
    List<CompetitionDO> findByCompetitionNameAndUserId(String competitionName, String userId);

    /**
     * 通过团队id获取证书记录
     *
     * @param teamId
     * @return
     */
    List<CompetitionDO> findByTeamId(String teamId);
}
