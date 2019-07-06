/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.locale.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.locale.dal.service.LocaleDORepoService;
import us.betahouse.haetae.locale.manager.LocaleManager;
import us.betahouse.haetae.locale.model.basic.LocaleBO;

import java.util.List;

/**
 * @author NathanDai
 * @version :  2019-07-03 21:24 NathanDai
 */

@Service
public class LocaleManagerImpl implements LocaleManager {
    @Autowired
    LocaleDORepoService localeDORepoService;

    /**
     * 查询所有场地
     *
     * @return
     */
    @Override
    public List<LocaleBO> findAll() {
        return localeDORepoService.queryAllLocales();
    }

    /**
     *
     * @param status
     * @return
     */
    @Override
    public List<LocaleBO> findAllByStatus(String status) {
        return localeDORepoService.queryAllLocalesByStatus(status);
    }
}
