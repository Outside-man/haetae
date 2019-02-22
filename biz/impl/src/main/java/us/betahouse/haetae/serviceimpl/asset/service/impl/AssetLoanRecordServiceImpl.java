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
import us.betahouse.haetae.asset.enums.AssetStatusEnum;
import us.betahouse.haetae.asset.manager.AssetLoanRecordManager;
import us.betahouse.haetae.asset.manager.AssetManager;
import us.betahouse.haetae.asset.model.basic.AssetBO;
import us.betahouse.haetae.asset.model.basic.AssetLoanRecordBO;
import us.betahouse.haetae.asset.request.AssetLoanRecordRequest;
import us.betahouse.haetae.serviceimpl.asset.service.AssetLoanRecordService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.utils.AssertUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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

    @Override
    @Transactional
    public List<AssetLoanRecordBO> create(AssetLoanRecordRequest request, OperateContext context) {
        String str = null;
        AssetBO assetBO = assetManager.findAssetByAssetID(request.getAssetId());
        AssertUtil.assertNotNull(assetBO, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资码无效");
        Boolean isNumber = Pattern.matches("[0-9]*", request.getAmount().toString());
        AssertUtil.assertNotNull(!isNumber? null: "1", RestResultCode.ILLEGAL_PARAMETERS.getCode(), "输入物资的数量包含非法字符");
        if (request.getAmount() > assetBO.getAssetRemain()) {
            AssertUtil.assertNotNull(str, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "借用数量不能大于物资剩余数量");
        }
        AssetStatusEnum assetStatusEnum = AssetStatusEnum.getByCode(assetBO.getAssetStatus());
        AssertUtil.assertNotNull(assetStatusEnum, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资状态错误");
        switch (assetStatusEnum) {
            case ASSET_ALLLOAN:
                AssertUtil.assertNotNull(str, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资全部借出");
                return assetLoanRecordManager.findAssetLoanRecordByAssetId(request.getAssetId());
            case ASSET_DISTORY:
                AssertUtil.assertNotNull(str, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资耗尽");
                return assetLoanRecordManager.findDistoryRecordByAssetId(request.getAssetId());
            default:
                break;
        }
        request.setAssetType(assetBO.getAssetType());
        List<AssetLoanRecordBO> assetLoanRecordBOS = new ArrayList<AssetLoanRecordBO>();
        AssetLoanRecordBO assetLoanRecordBO = assetLoanRecordManager.create(request);
        assetLoanRecordBOS.add(assetLoanRecordBO);
        return assetLoanRecordBOS;
    }

    @Override
    @Transactional
    public AssetLoanRecordBO update(AssetLoanRecordRequest request, OperateContext context) {
        return assetLoanRecordManager.update(request);
    }

    @Override
    @Transactional
    public List<AssetLoanRecordBO> findAllAssetLoanRecordByAssetId(AssetLoanRecordRequest request, OperateContext context) {
        return assetLoanRecordManager.findAllAssetLoanRecordByAssetId(request.getAssetId());
    }

    @Override
    @Transactional
    public List<AssetLoanRecordBO> findAllAssetLoanRecordByUserId(AssetLoanRecordRequest request, OperateContext context) {
        return assetLoanRecordManager.findAssetLoanRecordByUserId(request.getUserId());
    }

    @Override
    public AssetLoanRecordBO findAssetLoanRecordByLoanRecordId(AssetLoanRecordRequest request, OperateContext context) {
        AssetLoanRecordBO assetLoanRecordBO = assetLoanRecordManager.findAssetLoanRecordByLoanRecordId(request.getLoanRecordId());
        AssertUtil.assertNotNull(assetLoanRecordBO, "借取记录不存在");
        return assetLoanRecordBO;
    }
}
