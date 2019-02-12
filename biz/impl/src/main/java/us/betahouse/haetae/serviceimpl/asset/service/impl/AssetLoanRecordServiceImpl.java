/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.asset.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.asset.dal.service.AssetLoanRecordRepoService;
import us.betahouse.haetae.asset.manager.AssetLoanRecordManager;
import us.betahouse.haetae.asset.manager.AssetManager;
import us.betahouse.haetae.asset.model.basic.AssetBO;
import us.betahouse.haetae.asset.model.basic.AssetLoanRecordBO;
import us.betahouse.haetae.asset.request.AssetLoanRecordRequest;
import us.betahouse.haetae.serviceimpl.asset.service.AssetLoanRecordService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.util.utils.AssertUtil;

import java.util.List;

/**
 * @author yiyuk.hxy
 * @version : AssetLoanRecordServiceImpl.java 2019/01/25 15:55 yiyuk.hxy
 */
@Service
public class AssetLoanRecordServiceImpl implements AssetLoanRecordService {
    private final Logger LOGGER = LoggerFactory.getLogger(AssetLoanRecordServiceImpl.class);

    @Autowired
    private AssetManager assetManager;

    @Autowired
    private AssetLoanRecordManager assetLoanRecordManager;

    @Autowired
    private AssetLoanRecordRepoService assetLoanRecordRepoService;

    @Override
    @Transactional
    public List<AssetLoanRecordBO> create(AssetLoanRecordRequest request, OperateContext context) {
        AssertUtil.assertStringNotBlank(request.getUserId(), "用户id不能为空");

        AssetBO assetBO = assetManager.findAssetByAssetID(request.getAssetId());
        if (assetBO == null) {
            AssertUtil.assertStringNotBlank( "物资码无效");
            return null;
        }
        if (assetBO.canLoan() == 2) {
            AssertUtil.assertStringNotBlank(assetBO.getAssetName(), "物资全部借出");
            return assetLoanRecordManager.findAssetLoanRecordByAssetId(request.getAssetId());
        }
        if (assetBO.canLoan() == 3) {
            AssertUtil.assertStringNotBlank(assetBO.getAssetName(), "物资耗尽");
            return assetLoanRecordManager.findDistoryRecordByAssetId(request.getAssetId());
        }
        List<AssetLoanRecordBO> assetLoanRecordBOS = null;
        assetLoanRecordBOS.add(assetLoanRecordManager.create(request));
        return assetLoanRecordBOS;
    }

    @Override
    public AssetLoanRecordBO update(AssetLoanRecordRequest request, OperateContext context) {
        return assetLoanRecordManager.update(request);
    }

    @Override
    public List<AssetLoanRecordBO> findAllAssetLoanRecordByAssetId(AssetLoanRecordRequest request, OperateContext context) {
        return assetLoanRecordManager.findAllAssetLoanRecordByAssetId(request.getAssetId());
    }

    @Override
    public List<AssetLoanRecordBO> findAllAssetLoanRecordByUserId(AssetLoanRecordRequest request, OperateContext context) {
        return assetLoanRecordManager.findAssetLoanRecordByUserId(request.getUserId());
    }
}
