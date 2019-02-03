/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.asset.dal.model.AssetLoanRecordDO;

import java.util.List;

/**
 * @author yiyuk.hxy
 * @version : AssetLoanDORepo.java 2019/01/23 18:00 yiyuk.hxy
 */
@Repository
public interface AssetLoanDORepo extends JpaRepository<AssetLoanRecordDO, Long> {
    /**
     * 根据用户id获取
     *
     * @param userId
     * @return
     */
    List<AssetLoanRecordDO> findByUserId(String userId);

    /**
     * 根据借用记录id获取
     *
     * @param loanId
     * @return
     */
    AssetLoanRecordDO findByLoanRecordId(String loanId);

    /**
     * 根据物资id获取全部借用记录
     *
     * @param assetId
     * @return
     */
    List<AssetLoanRecordDO> findAllRecordByAssetId(String assetId);
}
