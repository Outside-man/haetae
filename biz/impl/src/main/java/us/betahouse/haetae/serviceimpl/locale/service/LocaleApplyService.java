/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.serviceimpl.locale.service;

import us.betahouse.haetae.locale.model.basic.LocaleApplyBO;
import us.betahouse.haetae.locale.model.common.PageList;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.locale.request.LocaleApplyManagerRequest;

import java.util.List;

/**
 * 场地申请业务
 *
 * @author NathanDai
 * @version :  2019-07-05 05:32 NathanDai
 */
public interface LocaleApplyService {
    /**
     * 创建场地申请
     *
     * @param request
     * @param context
     * @return
     */
    LocaleApplyBO create(LocaleApplyManagerRequest request, OperateContext context);

    /**
     * 更新场地申请状态
     * 管理员 学工
     *
     * @param request
     * @param context
     * @return
     */
    LocaleApplyBO updateAdminFirst(LocaleApplyManagerRequest request, OperateContext context);

    /**
     * 更新场地申请状态
     * 管理员 团委
     *
     * @param request
     * @param context
     * @return
     */
    LocaleApplyBO updateAdminSecond(LocaleApplyManagerRequest request, OperateContext context);

    /**
     * 取消场地申请
     * 申请人
     *
     * @param request
     * @param context
     * @return
     */
    LocaleApplyBO updateUser(LocaleApplyManagerRequest request, OperateContext context);

    /**
     * 通过场地申请id查询
     *
     * @param request
     * @param context
     * @return
     */
    LocaleApplyBO findByLocaleId(LocaleApplyManagerRequest request, OperateContext context);

    /**
     * 查询场地申请
     * 学工 查询
     *
     * @param request
     * @param context
     * @return
     */
    PageList<LocaleApplyBO> findAllByStatusFirst(LocaleApplyManagerRequest request, OperateContext context);

    /**
     * 查询场地申请
     * 团委 查询
     *
     * @param request
     * @param context
     * @return
     */
    PageList<LocaleApplyBO> findAllByStatusSecond(LocaleApplyManagerRequest request, OperateContext context);

    /**
     * 查询场地申请
     * 申请者 查询
     *
     * @param request
     * @param context
     * @return
     */
    PageList<LocaleApplyBO> findAllByUserId(LocaleApplyManagerRequest request, OperateContext context);

    /**
     * 结束可以结束的场地申请
     *
     * @return
     */
    List<LocaleApplyBO> systemFinishLocaleApply();
}
