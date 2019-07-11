/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.serviceimpl.locale.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.locale.enums.LocaleAreaStatusEnum;
import us.betahouse.haetae.locale.manager.LocaleAreaManager;
import us.betahouse.haetae.locale.model.basic.LocaleAreaBO;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyPerm;
import us.betahouse.haetae.serviceimpl.locale.constant.LocalePermType;
import us.betahouse.haetae.serviceimpl.locale.request.LocaleAreaManagerRequest;
import us.betahouse.haetae.serviceimpl.locale.service.LocaleAreaService;
import us.betahouse.util.utils.AssertUtil;

import java.util.List;

/**
 * @author NathanDai
 * @version :  2019-07-05 05:33 NathanDai
 */
@Service
public class LocaleAreaServiceImpl implements LocaleAreaService {

    @Autowired
    LocaleAreaManager localeAreaManager;

    /**
     * 查询场地占用
     * 场地申请人角色
     *
     * @param request
     * @param context
     * @return
     */
    @Override
    @VerifyPerm(permType = LocalePermType.LOCALE_APPLY)
    @Transactional(rollbackFor = Exception.class)
    public List<LocaleAreaBO> findAllByLocaleIdAndTimeDateAndStatus(LocaleAreaManagerRequest request, OperateContext context) {
        List<LocaleAreaBO> localeAreaBO = localeAreaManager.findAllByLocaleIdAndTimeDateAndStatus(request.getLocaleId(), request.getTimeDate(), "CANCEL");
        return localeAreaBO;
    }

    /**
     * 创建场地占用
     * 场地申请人角色
     *
     * @param request
     * @param context
     * @return
     */
    @Override
    @VerifyPerm(permType = LocalePermType.LOCALE_APPLY)
    @Transactional(rollbackFor = Exception.class)
    public LocaleAreaBO create(LocaleAreaManagerRequest request, OperateContext context) {
        LocaleAreaStatusEnum localeAreaStatusEnum = LocaleAreaStatusEnum.getByCode(request.getStatus());
        AssertUtil.assertNotNull(localeAreaStatusEnum, "场地类型不存在");

        //再次确定场地是否被占用
        LocaleAreaBO localeAreaBOCheck = localeAreaManager.findByLocaleIdAndTimeDateAndTimeBucketAndStatus(request.getLocaleId(), request.getTimeDate(), request.getTimeBucket(), "CANCEL");
        AssertUtil.assertNull(localeAreaBOCheck, "场地已经被占用");

        LocaleAreaBO localeAreaBO = localeAreaManager.create(request);
        return localeAreaBO;
    }

    /**
     * 更新场地占用状态
     * 场地申请人角色
     *
     * @param request
     * @param context
     * @return
     */
    @Override
    @VerifyPerm(permType = LocalePermType.LOCALE_APPLY)
    @Transactional(rollbackFor = Exception.class)
    public LocaleAreaBO update(LocaleAreaManagerRequest request, OperateContext context) {
        LocaleAreaStatusEnum localeAreaStatusEnum = LocaleAreaStatusEnum.getByCode(request.getStatus());
        AssertUtil.assertNotNull(localeAreaStatusEnum, "场地状态不存在");

        LocaleAreaBO localeAreaBO = localeAreaManager.update(request);
        return localeAreaBO;
    }
}
