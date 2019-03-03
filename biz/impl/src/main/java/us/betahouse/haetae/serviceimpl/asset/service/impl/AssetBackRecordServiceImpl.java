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
import us.betahouse.haetae.asset.manager.AssetBackRecordManager;
import us.betahouse.haetae.asset.manager.AssetLoanRecordManager;
import us.betahouse.haetae.asset.manager.AssetManager;
import us.betahouse.haetae.asset.model.basic.AssetBO;
import us.betahouse.haetae.asset.model.basic.AssetBackRecordBO;
import us.betahouse.haetae.asset.model.basic.AssetLoanRecordBO;
import us.betahouse.haetae.asset.request.AssetBackRecordRequest;
import us.betahouse.haetae.serviceimpl.asset.service.AssetBackRecordService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.utils.AssertUtil;

import java.util.List;

/**
 * @author yiyuk.hxy
 * @version : AssetBackRecordServiceImpl.java 2019/02/12 17:43 yiyuk.hxy
 */
@Service
public class AssetBackRecordServiceImpl implements AssetBackRecordService {
    private final Logger LOGGER = LoggerFactory.getLogger(AssetBackRecordServiceImpl.class);

    @Autowired
    private AssetManager assetManager;

    @Autowired
    private AssetLoanRecordManager assetLoanRecordManager;

    @Autowired
    private AssetBackRecordManager assetBackRecordManager;

    @Override
    @Transactional
    public AssetBackRecordBO create(AssetBackRecordRequest request, OperateContext context) {
        AssetBO assetBO = assetManager.findAssetByAssetID(request.getAssetId());
        AssertUtil.assertNotNull(assetBO, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资码无效");
        AssetLoanRecordBO assetLoanRecordBO = assetLoanRecordManager.findAssetLoanRecordByLoanRecordId(request.getLoanRecoedId());
        AssertUtil.assertNotNull(assetLoanRecordBO, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "借用记录不存在");
        AssertUtil.assertNotNull(assetLoanRecordBO.getRemain() == 0 ? null : "1", RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资已全部归还");
        AssertUtil.assertNotNull(assetLoanRecordBO.getAmount() > assetLoanRecordBO.getRemain() ? null : "1", RestResultCode.ILLEGAL_PARAMETERS.getCode(), "归还数量超出剩余未归还数量");
        request.setAssetType(assetBO.getAssetType());
        AssetBackRecordBO assetBackRecordBO = assetBackRecordManager.create(request);
        if(assetBackRecordBO != null){
            assetBackRecordBO.setAssetName(assetBO.getAssetName());
        }
        return assetBackRecordBO;
    }

    @Override
    @Transactional
    public AssetBackRecordBO consume(AssetBackRecordRequest request, OperateContext context) {
        AssetBO assetBO = assetManager.findAssetByAssetID(request.getAssetId());
        AssetLoanRecordBO assetLoanRecordBO = assetLoanRecordManager.findAssetLoanRecordByLoanRecordId(request.getLoanRecoedId());
        request.setType("destroy");
        request.setAssetType(assetBO.getAssetType());
        request.setAmount(assetLoanRecordBO.getRemain());
        AssetBackRecordBO assetBackRecordBO = assetBackRecordManager.create(request);
        return assetBackRecordBO;
    }


    @Override
    public List<AssetBackRecordBO> findAllAssetBackRecordByAssetId(AssetBackRecordRequest request, OperateContext context) {
        AssetBO assetBO = assetManager.findAssetByAssetID(request.getAssetId());
        AssertUtil.assertNotNull(assetBO, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资码无效");
        List<AssetBackRecordBO> assetBackRecordBOS = assetBackRecordManager.findAllAssetBackRecordByAssetId(request.getAssetId());
        for (AssetBackRecordBO assetBackRecordBO : assetBackRecordBOS) {
            assetBackRecordBO.setAssetName(assetBO.getAssetName());
        }
        return assetBackRecordBOS;
    }

    @Override
    public List<AssetBackRecordBO> findAllAssetBackRecordByUserId(AssetBackRecordRequest request, OperateContext context) {
        List<AssetBackRecordBO> assetBackRecordBOS = assetBackRecordManager.findAllAssetBackRecordByUserId(request.getAssetId());
        if (assetBackRecordBOS != null) {
            AssetBO assetBO = assetManager.findAssetByAssetID(assetBackRecordBOS.get(0).getAssetId());
            for (AssetBackRecordBO assetBackRecordBO : assetBackRecordBOS) {
                assetBackRecordBO.setAssetName(assetBO.getAssetName());
            }
        }

        return assetBackRecordBOS;
    }

    @Override
    public List<AssetBackRecordBO> findAssetBackRecordByLoanRecordId(AssetBackRecordRequest request, OperateContext context) {
        List<AssetBackRecordBO> assetBackRecordBOS = assetBackRecordManager.findAssetBackRecordByLoanRecordId(request.getLoanRecoedId());
        AssertUtil.assertNotNull(assetBackRecordBOS, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "无对应归还记录");
        AssetBO assetBO = assetManager.findAssetByAssetID(assetBackRecordBOS.get(0).getAssetId());
        for (AssetBackRecordBO assetBackRecordBO : assetBackRecordBOS) {
            assetBackRecordBO.setAssetName(assetBO.getAssetName());
        }
        return assetBackRecordBOS;
    }
}
