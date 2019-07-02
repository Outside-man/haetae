package us.betahouse.haetae.locale.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.locale.dal.model.LocaleAreaDO;

import java.util.List;

@Repository
public interface LocaleAreaDORepo extends JpaRepository<LocaleAreaDO, Long> {
    /**
     *
     * @return
     */
    List<LocaleAreaDO> findAll();
}
