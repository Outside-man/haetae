package us.betahouse.haetae.asset.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.asset.dal.model.AssetDO;

import javax.annotation.Resource;
import java.util.List;

@Repository
public interface AssetDORepo  extends JpaRepository<AssetDO,Long> {
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
    AssetDO findAssetById(Long id);
}
