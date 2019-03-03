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
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.dal.service.UserRepoService;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.utils.AssertUtil;

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

    @Autowired
    private UserRepoService userRepoService;

    @Autowired
    private UserInfoRepoService userInfoRepoService;

    @Override
    @Transactional
    public AssetLoanRecordBO create(AssetLoanRecordRequest request, OperateContext context) {
        AssetBO assetBO = assetManager.findAssetByAssetID(request.getAssetId());
        AssertUtil.assertNotNull(assetBO, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资码无效");
        //正则表达式来检验输入数量中是否有非法输入
        Boolean isNumber = Pattern.matches("[0-9]*", request.getAmount().toString());
        AssertUtil.assertTrue(isNumber, "输入物资的数量包含非法字符");
        AssertUtil.assertTrue(request.getAmount() > assetBO.getAssetRemain(), "借用数量不能大于物资剩余数量");
        AssertUtil.assertTrue(assetBO.getAssetStatus().equals(AssetStatusEnum.ASSET_LOAN), "物资不可借");
        request.setAssetType(assetBO.getAssetType());
        AssetLoanRecordBO assetLoanRecordBO = assetLoanRecordManager.create(request);
        //借用记录返回添加用户学号和用户真实姓名
        assetLoanRecordBO.setStuId(userRepoService.queryByUserId(request.getUserId()).getUserName());
        assetLoanRecordBO.setRealName(userInfoRepoService.queryUserInfoByUserId(request.getUserId()).getRealName());
        return assetLoanRecordBO;
    }


    @Override
    public List<AssetLoanRecordBO> findAllAssetLoanRecordByAssetId(AssetLoanRecordRequest request, OperateContext context) {
        AssetBO assetBO = assetManager.findAssetByAssetID(request.getAssetId());
        AssertUtil.assertNotNull(assetBO, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资ID不存在");
        List<AssetLoanRecordBO> assetLoanRecordBOS=assetLoanRecordManager.findAllAssetLoanRecordByAssetId(request.getAssetId());
        assetLoanRecordBOS.forEach(assetLoanRecordBO -> {
            assetLoanRecordBO.setStuId(userRepoService.queryByUserId(request.getUserId()).getUserName());
            assetLoanRecordBO.setRealName(userInfoRepoService.queryUserInfoByUserId(request.getUserId()).getRealName());
        });
        return assetLoanRecordBOS;
    }

    @Override
    public List<AssetLoanRecordBO> findAllAssetLoanRecordByUserId(AssetLoanRecordRequest request, OperateContext context) {
        List<AssetLoanRecordBO> assetLoanRecordBOS=assetLoanRecordManager.findAssetLoanRecordByUserId(request.getAssetId());
        assetLoanRecordBOS.forEach(assetLoanRecordBO -> {
            assetLoanRecordBO.setStuId(userRepoService.queryByUserId(request.getUserId()).getUserName());
            assetLoanRecordBO.setRealName(userInfoRepoService.queryUserInfoByUserId(request.getUserId()).getRealName());
        });
        return assetLoanRecordBOS;
    }

    @Override
    public AssetLoanRecordBO findAssetLoanRecordByLoanRecordId(AssetLoanRecordRequest request, OperateContext context) {
        AssetLoanRecordBO assetLoanRecordBO = assetLoanRecordManager.findAssetLoanRecordByLoanRecordId(request.getLoanRecordId());
        AssertUtil.assertNotNull(assetLoanRecordBO, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "借取记录不存在");
        return assetLoanRecordBO;
    }
}
