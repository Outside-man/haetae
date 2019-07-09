/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.locale.manager;

import us.betahouse.haetae.locale.model.basic.LocaleApplyBO;
import us.betahouse.haetae.locale.model.common.PageList;
import us.betahouse.haetae.locale.request.LocaleApplyRequest;

/**
 * @author NathanDai
 * @version :  2019-07-05 06:28 NathanDai
 */
public interface LocaleApplyManager {
    /**
     * 创建场地申请
     *
     * @param request
     * @return
     */
    LocaleApplyBO create(LocaleApplyRequest request);

    /**
     * 场地申请状态修改
     *
     * @param request
     * @return
     */
    LocaleApplyBO update(LocaleApplyRequest request);

    /**
     * 查找场地申请 通过状态
     *
     * @param request
     * @return
     */
    PageList<LocaleApplyBO> findByStatus(LocaleApplyRequest request);

    /**
     * 查找场地申请 通过用户id
     *
     * @param request
     * @return
     */
    PageList<LocaleApplyBO> findByUserId(LocaleApplyRequest request);
}
