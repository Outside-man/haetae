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
     * 查询某个场地某天有没有被占用
     *
     * @param LocaleId
     * @param TimeDate
     * @param Status
     * @return
     */
    List<LocaleAreaDO> findAllByLocaleIdAndTimeDateAndStatusNot(String LocaleId, String TimeDate, String Status);

    /**
     * 校验一下这个有没有被占用
     *
     * @param LocaleId
     * @param TimeDate
     * @param TimeBucket
     * @param Status
     * @return
     */
    LocaleAreaDO findByLocaleIdAndTimeDateAndTimeBucketAndStatusNot(String LocaleId, String TimeDate, String TimeBucket, String Status);

    /**
     * 通过LocaleAreaId查询
     *
     * @param localeAreaId
     * @return LocaleAreaDO
     */
    LocaleAreaDO findByLocaleAreaId(String localeAreaId);

}
