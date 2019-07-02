package us.betahouse.haetae.locale.manager;

import us.betahouse.haetae.locale.model.basic.LocaleBO;

import java.util.List;

public interface LocaleManager {
    /**
     * 查询所有场地
     *
     * @return
     */
    List<LocaleBO> findAll();
}
