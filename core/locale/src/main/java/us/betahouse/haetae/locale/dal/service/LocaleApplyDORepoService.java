/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.locale.dal.service;

import us.betahouse.haetae.locale.model.basic.LocaleApplyBO;
import us.betahouse.haetae.locale.model.common.PageList;

import java.util.List;

/**
 * @author NathanDai
 * @version :  2019-07-03 21:24 NathanDai
 */

public interface LocaleApplyDORepoService {

    /**
     * 新建一个场地申请
     *
     * @param localeApplyBO
     * @return LocaleApplyBO
     */
    LocaleApplyBO createLocaleApply(LocaleApplyBO localeApplyBO);

    /**
     * 通过场地申请id查询
     *
     * @return List<LocaleApplyBO>
     */
    LocaleApplyBO queryByLocaleApplyId(String localeApplyId);

    /**
     * 通过申请状态 查询
     * @param status
     * @return
     */
    List<LocaleApplyBO> queryLocaleApplyByStatus(String status);

    /**
     * 通过状态 分页查询 分页
     *
     * @param status 状态
     * @param page   页面
     * @param limit  每页行数
     * @return PageList<LocaleApplyBO>
     */
    PageList<LocaleApplyBO> queryLocaleApplyByStatusAndPagerDESC(String status, Integer page, Integer limit);

    /**
     * 通过状态 分页查询 分页
     *
     * @param status 状态
     * @param page   页面
     * @param limit  每页行数
     * @return PageList<LocaleApplyBO>
     */
    PageList<LocaleApplyBO> queryLocaleApplyByStatusAndPagerASC(String status, Integer page, Integer limit);

    /**
     * 通过用户id 分页查询 分页
     *
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    PageList<LocaleApplyBO> queryLocaleApplyByUserIdAndPagerASC(String userId, Integer page, Integer limit);

    /**
     * 通过用户id 分页查询 分页
     *
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    PageList<LocaleApplyBO> queryLocaleApplyByUserIdAndPagerDESC(String userId, Integer page, Integer limit);

    /**
     * 更新场地申请状态
     *
     * @param localeApplyBO
     * @return LocaleApplyBO
     */
    LocaleApplyBO updateLocaleApply(LocaleApplyBO localeApplyBO);
}
