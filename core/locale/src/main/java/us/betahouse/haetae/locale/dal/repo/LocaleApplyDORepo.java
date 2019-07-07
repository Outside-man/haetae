/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.locale.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.locale.dal.model.LocaleApplyDO;

import java.util.List;

/**
 * @author NathanDai
 * @version :  2019-07-03 21:24 NathanDai
 */

@Repository
public interface LocaleApplyDORepo extends JpaRepository<LocaleApplyDO, Long> {
    /**
     * 查询所有场地申请
     *
     * @return
     */
    List<LocaleApplyDO> findAll();

    /**
     * 通过场地申请id查询
     *
     * @return
     */
    LocaleApplyDO findByLocaleApplyId(String localeApplyId);


}
