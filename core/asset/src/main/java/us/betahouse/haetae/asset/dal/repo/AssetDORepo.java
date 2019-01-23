/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.asset.dal.model.AssetDO;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yiyu.hxy
 * @version : AssetLoanRecordDO.java 2019/01/20 18:56 yiyu.hxy
 */
@Repository
public interface AssetDORepo extends JpaRepository<AssetDO, Long> {
    /**
     * 通过名称查找物资
     *
     * @param name
     * @return
     */
    List<AssetDO> findAssetByName(String name);
    /**
     * 通过id查找物资
     *
     * @param id
     * @return
     */
    AssetDO findAssetById(String id);
    /**
     * 通过id确定物资是否存在
     * @param id
     * @return
     */
    boolean existAssetById(String id);
}
