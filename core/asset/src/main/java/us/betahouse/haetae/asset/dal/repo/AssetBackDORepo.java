/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.asset.dal.model.AssetBackRecordDO;

import java.util.List;

/**
 * @author yiyuk.hxy
 * @version : AssetBackDORepo.java 2019/01/23 18:01 yiyuk.hxy
 */
@Repository
public interface AssetBackDORepo extends JpaRepository<AssetBackRecordDO, Long> {
    /**
     * 根据用户id获取
     *
     * @param userId
     * @return
     */
    List<AssetBackRecordDO> findAllByUserId(String userId);

    /**
     * 根据借用记录id获取
     *
     * @param loanRecoedId
     * @return
     */
    AssetBackRecordDO findByLoanRecoedId(String loanRecoedId);

    /**
     * 根据归还记录id获取
     *
     * @param backRecoedId
     * @return
     */
    AssetBackRecordDO findByBackRecoedId(String backRecoedId);

}
