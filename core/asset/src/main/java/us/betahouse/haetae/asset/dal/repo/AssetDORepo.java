package us.betahouse.haetae.asset.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import us.betahouse.haetae.asset.dal.model.AssetDO;

public interface AssetDORepo  extends JpaRepository<AssetDO,Long> {

}
