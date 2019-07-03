package us.betahouse.haetae.serviceimpl.locale.service;

import us.betahouse.haetae.locale.model.basic.LocaleBO;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.locale.request.LocaleManagerRequest;

import java.util.List;

public interface LocaleService {
    /**
     * 查询所有的场地
     *
     * @param request
     * @param context
     * @return
     */
    List<LocaleBO> findAllLocale(LocaleManagerRequest request, OperateContext context);
}
