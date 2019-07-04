/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.locale.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.locale.dal.model.LocaleAreaDO;

import java.util.List;

/**
 * @author NathanDai
 * @version :  2019-07-03 21:24 NathanDai
 */

@Repository
public interface LocaleAreaDORepo extends JpaRepository<LocaleAreaDO, Long> {
    /**
     * 查询所有被占用的场地
     *
     * @return
     */
    List<LocaleAreaDO> findAll();

    /**
     * 查询某个场地某天有没有被占用
     *
     * @return
     */
    List<LocaleAreaDO> findByLocaleIdAndTimeDateAndStatus(String LocaleId, String TimeDate, String Status);

    List<LocaleAreaDO> findByLocaleIdAndTimeDateAndStatusNot(String LocaleId, String TimeDate, String Status);

}
