package us.betahouse.haetae.locale.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.locale.dal.model.LocaleDO;

import java.util.List;

@Repository
public interface LocaleDORepo extends JpaRepository<LocaleDO, Long> {
    /**
     * @return
     */
    List<LocaleDO> findAll();

    /**
     * 通过场地状态查询场地
     *
     * @param status
     * @return
     */
    List<LocaleDO> findByStatus(String status);



}
