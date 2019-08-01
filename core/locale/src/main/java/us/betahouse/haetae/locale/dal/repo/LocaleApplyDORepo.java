/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.locale.dal.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     * 通过场地申请id查询
     *
     * @return LocaleApplyDO
     */
    LocaleApplyDO findByLocaleApplyId(String localeApplyId);

    List<LocaleApplyDO> findAllByStatus(String status);

    /**
     * 通过场地申请状态查询
     *
     * @param pageable 分页工具
     * @param status   场地申请状态
     * @return Page<LocaleApplyDO>
     */
    Page<LocaleApplyDO> findAllByStatusContainsOrderByLocaleApplyIdDesc(Pageable pageable, String status);

    /**
     * 通过场地申请状态查询
     *
     * @param pageable 分页工具
     * @param status   场地申请状态
     * @return Page<LocaleApplyDO>
     */
    Page<LocaleApplyDO> findAllByStatusContains(Pageable pageable, String status);

    /**
     * 通过用户id查询
     *
     * @param pageable 分页工具
     * @param userId   用户id
     * @return
     */
    Page<LocaleApplyDO> findAllByUserIdContains(Pageable pageable, String userId);

    /**
     * 通过用户id查询
     *
     * @param pageable 分页工具
     * @param userId   用户id
     * @return
     */
    Page<LocaleApplyDO> findAllByUserIdContainsOrderByLocaleApplyId(Pageable pageable, String userId);

}
