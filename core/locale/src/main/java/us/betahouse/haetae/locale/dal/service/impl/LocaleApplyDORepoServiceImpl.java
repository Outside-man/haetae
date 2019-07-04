/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.locale.dal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.locale.dal.model.LocaleApplyDO;
import us.betahouse.haetae.locale.dal.repo.LocaleApplyDORepo;
import us.betahouse.haetae.locale.dal.service.LocaleApplyDORepoService;
import us.betahouse.haetae.locale.idfactory.BizIdFactory;
import us.betahouse.haetae.locale.model.basic.LocaleApplyBO;

/**
 * @author NathanDai
 * @version :  2019-07-03 21:24 NathanDai
 */

@Service
public class LocaleApplyDORepoServiceImpl implements LocaleApplyDORepoService {
    /**
     * id工厂
     */
    @Autowired
    private BizIdFactory localBizFactory;

    @Autowired
    private LocaleApplyDORepo localeApplyDORepo;

    /**
     * @param localeApplyBO
     * @return
     */
    @Override
    public LocaleApplyBO createLocaleApply(LocaleApplyBO localeApplyBO) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public LocaleApplyBO queryAllLocaleApply() {
        return null;
    }


    @SuppressWarnings("unchecked")
    private LocaleApplyBO convert(LocaleApplyDO localeApplyDO) {
        if (localeApplyDO == null) {
            return null;
        }
        LocaleApplyBO localeApplyBO = new LocaleApplyBO();
        localeApplyBO.setTel(localeApplyDO.getTel());
        localeApplyBO.setUsage(localeApplyDO.getUsage());
        localeApplyBO.setRemark(localeApplyDO.getRemark());
        localeApplyBO.setDocument(localeApplyDO.getDocument());
        localeApplyBO.setStatus(localeApplyDO.getStatus());
        localeApplyBO.setTimeDate(localeApplyDO.getTimeDate());
        localeApplyBO.setTimeBucket(localeApplyDO.getTimeBucket());
        localeApplyBO.setLocaleCode(localeApplyDO.getLocaleCode());
        localeApplyBO.setLocaleApplyId(localeApplyDO.getLocaleApplyId());
        localeApplyBO.setLocaleId(localeApplyDO.getLocaleId());
        localeApplyBO.setLocaleAreaId(localeApplyDO.getLocaleAreaId());
        localeApplyBO.setUserId(localeApplyDO.getUserId());
        return localeApplyBO;
    }

    private LocaleApplyDO convert(LocaleApplyBO localeApplyBO) {
        if (localeApplyBO == null) {
            return null;
        }
        LocaleApplyDO localeApplyDO = new LocaleApplyDO();
        localeApplyDO.setTel(localeApplyBO.getTel());
        localeApplyDO.setUsage(localeApplyBO.getUsage());
        localeApplyDO.setRemark(localeApplyBO.getRemark());
        localeApplyDO.setDocument(localeApplyBO.getDocument());
        localeApplyDO.setStatus(localeApplyBO.getStatus());
        localeApplyDO.setTimeDate(localeApplyBO.getTimeDate());
        localeApplyDO.setTimeBucket(localeApplyBO.getTimeBucket());
        localeApplyDO.setLocaleCode(localeApplyBO.getLocaleCode());
        localeApplyDO.setLocaleApplyId(localeApplyBO.getLocaleApplyId());
        localeApplyDO.setLocaleId(localeApplyBO.getLocaleId());
        localeApplyDO.setLocaleAreaId(localeApplyBO.getLocaleAreaId());
        localeApplyDO.setUserId(localeApplyBO.getUserId());
        return localeApplyDO;
    }
}
