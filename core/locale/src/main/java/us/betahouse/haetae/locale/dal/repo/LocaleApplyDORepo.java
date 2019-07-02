package us.betahouse.haetae.locale.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.locale.dal.model.LocaleApplyDO;

import java.util.List;

@Repository
public interface LocaleApplyDORepo extends JpaRepository<LocaleApplyDO, Long> {
    /**
     *
     * @return
     */
    List<LocaleApplyDO> findAll();

}
