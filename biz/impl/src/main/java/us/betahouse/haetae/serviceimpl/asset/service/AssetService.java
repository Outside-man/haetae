/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.asset.service;

import us.betahouse.haetae.asset.model.basic.AssetBO;
import us.betahouse.haetae.serviceimpl.asset.request.AssetManagerRequest;
import us.betahouse.haetae.serviceimpl.common.OperateContext;

/**
 * 物资业务
 *
 * @author yiyuk.hxy
 * @version : AssetService.java 2019/01/22 14:26 yiyuk.hxy
 */
public interface AssetService {
    /**
     * 创建物资
     *
     * @param request
     * @param context
     * @return
     */
    AssetBO create(AssetManagerRequest request, OperateContext context);
}
