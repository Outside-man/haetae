/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import us.betahouse.haetae.asset.dal.model.AssetDO;
import us.betahouse.haetae.asset.dal.model.AssetLoanRecordDO;

import java.util.List;

/**
 * @author yiyuk.hxy
 * @version : AssetLoanDORepo.java 2019/01/23 18:00 yiyuk.hxy
 */
public interface AssetLoanDORepo extends JpaRepository<AssetLoanRecordDO, Long> {
    /**
     * 根据用户id获取
     *
     * @param userId
     * @return
     */
    List<AssetLoanRecordDO> findAssetLoanRecordByUserId(String userId);

    /**
     * 根据借用记录id获取
     *
     * @param loanId
     * @return
     */
    AssetLoanRecordDO findAssetLoanRecordDOByLoanId(String loanId);

}
