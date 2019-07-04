/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.locale.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.locale.dal.model.LocaleDO;

import java.util.List;

/**
 * @author NathanDai
 * @version :  2019-07-03 21:24 NathanDai
 */

@Repository
public interface LocaleDORepo extends JpaRepository<LocaleDO, Long> {
    /**
     * 查询所有场地(不管能不能用)
     *
     * @return
     */
    List<LocaleDO> findAll();

    /**
     * 通过场地状态查询场地 可用 不可用
     *
     * @param status
     * @return
     */
    List<LocaleDO> findAllByStatus(String status);


}
