/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.asset.service;

import us.betahouse.haetae.asset.dal.model.AssetDO;
import us.betahouse.haetae.asset.request.AssetRequest;
import us.betahouse.haetae.serviceimpl.asset.request.AssetManagerRequest;
import us.betahouse.haetae.serviceimpl.common.OperateContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @author guofan.cp
 * @version : AssetService.java 2019/01/20 23:12 guofan.cp
 */
public interface AssetService {
    /** 
    * @Description: 创建物质 
    * @Param: [request, context] 
    */ 
    AssetDO create(AssetManagerRequest request, OperateContext context);
}
