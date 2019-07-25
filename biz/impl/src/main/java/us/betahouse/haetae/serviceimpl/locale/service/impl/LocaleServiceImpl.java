/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.locale.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.locale.manager.LocaleManager;
import us.betahouse.haetae.locale.model.basic.LocaleBO;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyPerm;
import us.betahouse.haetae.serviceimpl.locale.constant.LocalePermType;
import us.betahouse.haetae.serviceimpl.locale.request.LocaleManagerRequest;
import us.betahouse.haetae.serviceimpl.locale.service.LocaleService;

import java.util.List;

/**
 * @author NathanDai
 * @version :  2019-07-03 21:24 NathanDai
 */

@Service
public class LocaleServiceImpl implements LocaleService {
    @Autowired
    LocaleManager localeManager;

    /**
     * 通过场地状态查询所有场地
     * 使用角色 申请者
     *
     * @param request
     * @param context
     * @return
     */
    @Override
    @VerifyPerm(permType = LocalePermType.LOCALE_APPLY)
    @Transactional(rollbackFor = Exception.class)
    public List<LocaleBO> findAllLocalesByStatus(LocaleManagerRequest request, OperateContext context) {
        List<LocaleBO> localeBOList = localeManager.findAllByStatus(request.getStatus());
        return localeBOList;
    }

    /**
     * 用通过场地代号查询
     * @param request
     * @param context
     * @return
     */
    @Override
    public LocaleBO findLocaleNameByLocaleCode(LocaleManagerRequest request, OperateContext context) {
        LocaleBO localeBO = localeManager.findLocaleName((request.getLocaleCode()));
        return localeBO;
    }
}
