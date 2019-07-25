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
     * 通过场地状态查询所有场地
     *
     * @param status
     * @return List<LocaleDO>
     */
    List<LocaleDO> findAllByStatus(String status);

    /**
     * 查询场地通过场地id
     *
     * @param localeId
     * @return
     */
    LocaleDO findByLocaleId(String localeId);

    /**
     * 查询场地名称通过场地代号
     * @param localeCode
     * @return
     */
    LocaleDO findByLocaleCode(String localeCode);
}
