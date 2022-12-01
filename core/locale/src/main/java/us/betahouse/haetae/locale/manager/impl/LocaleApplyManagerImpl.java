/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.locale.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.locale.builder.LocaleApplyBOBuilder;
import us.betahouse.haetae.locale.dal.service.LocaleApplyDORepoService;
import us.betahouse.haetae.locale.manager.LocaleApplyManager;
import us.betahouse.haetae.locale.model.basic.LocaleApplyBO;
import us.betahouse.haetae.locale.model.common.PageList;
import us.betahouse.haetae.locale.request.LocaleApplyRequest;

/**
 * @author NathanDai
 * @version :  2019-07-05 06:28 NathanDai
 */
@Service
public class LocaleApplyManagerImpl implements LocaleApplyManager {
    @Autowired
    LocaleApplyDORepoService localeApplyDORepoService;

    /**
     * 创建场地申请
     *
     * @param request
     * @return
     */
    @Override
    public LocaleApplyBO create(LocaleApplyRequest request) {
        LocaleApplyBOBuilder localeApplyBOBuilder = LocaleApplyBOBuilder.getInstance()
                .withLocaleId(request.getLocaleId())
                .withLocaleAreaId(request.getLocaleAreaId())
                .withLocaleCode(request.getLocaleCode())
                .withStatus(request.getStatus())
                .withDocument(request.getDocument())
                .withTel(request.getTel())
                .withRemark(request.getRemark())
                .withUsages(request.getUsages())
                .withTimeDate(request.getTimeDate())
                .withTimeBucket(request.getTimeBucket())
                .withUserId(request.getUserId())
                .withOrganizationName(request.getOrganizationName());
        return localeApplyDORepoService.createLocaleApply(localeApplyBOBuilder.build());
    }

    /**
     * 更新场地申请状态
     *
     * @param request
     * @return
     */
    @Override
    public LocaleApplyBO update(LocaleApplyRequest request) {
        LocaleApplyBO localeApplyBO = LocaleApplyBOBuilder.getInstance()
                .withLocaleApplyId(request.getLocaleApplyId())
                .withStatus(request.getStatus()).build();
        localeApplyBO.setFailureMessage(request.getFailureMessage());
        System.out.println("_________");
        System.out.println(localeApplyBO);
        System.out.println("_________");
        return localeApplyDORepoService.updateLocaleApply(localeApplyBO);
    }

    /**
     * 通过场地申请id查询
     *
     * @param request
     * @return
     */
    @Override
    public LocaleApplyBO findByLocaleApplyId(LocaleApplyRequest request) {
        return localeApplyDORepoService.queryByLocaleApplyId(request.getLocaleApplyId());
    }

    /**
     * 查询场地申请 status
     *
     * @param request
     * @return
     */
    @Override
    public PageList<LocaleApplyBO> findByStatus(LocaleApplyRequest request) {
        //順序
        String asc = "ASC";
        if (asc.equals(request.getOrderRule())) {
            return localeApplyDORepoService.queryLocaleApplyByStatusAndPagerASC(request.getStatus(), request.getPage(), request.getLimit());
        } else {
            return localeApplyDORepoService.queryLocaleApplyByStatusAndPagerDESC(request.getStatus(), request.getPage(), request.getLimit());
        }
    }

    /**
     * 查询场地申请 userId
     *
     * @param request
     * @return
     */
    @Override
    public PageList<LocaleApplyBO> findByUserId(LocaleApplyRequest request) {
        String asc = "ASC";
        if (asc.equals(request.getOrderRule())) {
            return localeApplyDORepoService.queryLocaleApplyByUserIdAndPagerASC(request.getUserId(), request.getPage(), request.getLimit());
        } else {
            return localeApplyDORepoService.queryLocaleApplyByUserIdAndPagerDESC(request.getUserId(), request.getPage(), request.getLimit());
        }
    }
}
