/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import us.betahouse.haetae.certificate.dal.model.QualificationsDO;

import java.util.List;

/**
 * @author guofan.cp
 * @version : QualificationsDORepo.java 2019/04/03 14:44 guofan.cp
 */
public interface QualificationsDORepo extends JpaRepository<QualificationsDO, Long> {
    /**
     * 通过证书id查找证书id
     *
     * @param certificateId
     * @return
     */
    QualificationsDO findByCertificateId(String certificateId);

    /**
     * 通过学生id和类型获取证书
     *
     * @param userId
     * @param type
     * @return
     */
    List<QualificationsDO> findByUserIdAndType(String userId, String type);

    /**
     * 通过学生id获取全部证书（资格证书和四六级证书都有）
     *
     * @param userId
     * @return
     */
    List<QualificationsDO> findByUserId(String userId);
    
    int countAllByUserId(String userId);
    
    /**
     * 通过学生id和证书id查找证书
     *
     * @param certificateId
     * @param userId
     * @return
     */
    QualificationsDO findByCertificateIdAndUserId(String certificateId, String userId);
    /**
     * 通过证书名字与用户id获取证书
     *
     * @param qualificationName
     * @param userId
     * @return
     */
    List<QualificationsDO> findByCertificateNameAndUserId(String qualificationName,String userId);

    /**
     * 通过证书id删除证书
     *
     * @param certificateId
     * @return
     */
    void deleteByCertificateId(String certificateId);

    /**
     * 通过用户id和证书id删除证书
     *
     * @param certificateId
     * @param userId
     * @return
     */
    void deleteByCertificateIdAndUserId(String certificateId, String userId);

    /**
     * 通过证书名字 获取同名证书
     *
     * @param certificateName
     * @return
     */
    List<QualificationsDO> findByCertificateName(String certificateName);


}
