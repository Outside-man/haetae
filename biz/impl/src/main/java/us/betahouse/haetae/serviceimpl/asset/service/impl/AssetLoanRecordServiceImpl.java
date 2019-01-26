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
    public AssetLoanRecordBO create(AssetLoanRecordRequest request, OperateContext context) {
        AssertUtil.assertStringNotBlank(request.getUserId(), "用户id不能为空");
        // AssetLoanRecordBO assetLoanRecordBO = assetLoanRecordManager.create(request);
        AssetBO assetBO = assetManager.findAssetByAssetID(request.getAssetId());
        if (assetBO == null) {
            AssertUtil.assertStringNotBlank("", "物资码无效");
        }
        // TODO 物资全部报损状态码
        if (assetBO.getAssetType().equals("耗尽")) {
            AssertUtil.assertStringNotBlank("", "物资耗尽");
            // TODO 返回报损记录
        }
        if (assetBO.getAssetRemain() == 0) {
            AssertUtil.assertStringNotBlank("", "物资借完");
            // TODO 返回最近的出借记录
        }

        return assetLoanRecordManager.create(request);
    }

    @Override
    public AssetLoanRecordBO update(AssetLoanRecordRequest request, OperateContext context) {
        return assetLoanRecordManager.update(request);
    }

    @Override
    public List<AssetLoanRecordBO> getAssetLoanRecordByUserId(String userId) {
        return null;
    }

    @Override
    public List<AssetLoanRecordBO> getAssetLoanRecordByAssetId(String assetId) {
        return null;
    }
}
