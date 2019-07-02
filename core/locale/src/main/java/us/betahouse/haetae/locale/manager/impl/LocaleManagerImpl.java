package us.betahouse.haetae.locale.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.locale.dal.service.LocaleDORepoService;
import us.betahouse.haetae.locale.manager.LocaleManager;
import us.betahouse.haetae.locale.model.basic.LocaleBO;

import java.util.List;

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
}
