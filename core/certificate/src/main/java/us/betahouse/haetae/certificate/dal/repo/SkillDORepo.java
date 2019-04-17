/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import us.betahouse.haetae.certificate.dal.model.SkillDO;

import java.util.List;

/**
 * @author guofan.cp
 * @version : SkillDORepo.java 2019/04/03 14:44 guofan.cp
 */
public interface SkillDORepo extends JpaRepository<SkillDO, Long> {
    /**
     * 通过证书id查找证书id
     *
     * @param certificateId
     * @return
     */
    SkillDO findByCertificateId(String certificateId);

    /**
     * 通过证书id和用户id查找证书
     *
     * @param certificateId
     * @param userId
     * @return
     */
    SkillDO findByCertificateIdAndUserId(String certificateId,String userId);
    /**
     * 通过学生id获取全部技能证书
     *
     * @param userId
     * @return
     */
    List<SkillDO> findByUserId(String userId);

    /**
     * 通过证书id删除证书
     *
     * @param certificateId
     * @return
     */
    void deleteByCertificateId(String certificateId);

    void deleteByCertificateIdAndUserId(String certificateId,String userId);
    /**
     * 通过证书名字 获取同名证书
     *
     * @param certificateName
     * @return
     */
    List<SkillDO> findByCertificateName(String certificateName);
}
