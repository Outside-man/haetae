package us.betahouse.haetae.locale.dal.service;

import us.betahouse.haetae.locale.model.basic.LocaleBO;

import java.util.List;

public interface LocaleDORepoService {

    /**
     * 查询所有场地
     *
     * @return
     */
    List<LocaleBO> queryAllLocales();

    /**
     * 通过状态查询可以用的场地
     *
     * @param status
     * @return
     */
    List<LocaleBO> queryLocalesByStatus(String status);

}
