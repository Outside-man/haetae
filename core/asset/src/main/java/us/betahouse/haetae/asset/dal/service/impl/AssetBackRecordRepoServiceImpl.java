/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.dal.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.asset.dal.model.AssetBackRecordDO;
import us.betahouse.haetae.asset.dal.model.AssetDO;
import us.betahouse.haetae.asset.dal.model.AssetLoanRecordDO;
import us.betahouse.haetae.asset.dal.repo.AssetBackDORepo;
import us.betahouse.haetae.asset.dal.repo.AssetDORepo;
import us.betahouse.haetae.asset.dal.repo.AssetLoanDORepo;
import us.betahouse.haetae.asset.dal.service.AssetBackRecordRepoService;
import us.betahouse.haetae.asset.enums.AssetBackRecordTypeEnum;
import us.betahouse.haetae.asset.enums.AssetLoanRecordStatusEnum;
import us.betahouse.haetae.asset.enums.AssetStatusEnum;
import us.betahouse.haetae.asset.idfactory.BizIdFactory;
import us.betahouse.haetae.asset.model.basic.AssetBackRecordBO;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author yiyuk.hxy
 * @version : AssetBackRecordRepoServiceImpl.java 2019/02/11 12:28 yiyuk.hxy
 */
@Service
public class AssetBackRecordRepoServiceImpl implements AssetBackRecordRepoService {

    @Autowired
    private AssetBackDORepo assetBackDORepo;
    /**
     * id工厂
     */
    @Autowired
    private BizIdFactory assetBizFactory;

    @Autowired
    private AssetDORepo assetDORepo;

    @Autowired
    private AssetLoanDORepo assetLoanDORepo;

    /**
     * 归还物资
     *
     * @param assetBackRecordBO
     * @return
     */
    @Override
    @Transactional
    public AssetBackRecordBO createAssetBackRecord(AssetBackRecordBO assetBackRecordBO) {
        if (StringUtils.isBlank(assetBackRecordBO.getBackRecoedId())) {
            assetBackRecordBO.setBackRecoedId(assetBizFactory.getAssetBackId());
        }
        AssetDO assetDO = assetDORepo.findByAssetId(assetBackRecordBO.getAssetId());
        AssetLoanRecordDO assetLoanRecordDO = assetLoanDORepo.findByLoanRecordId(assetBackRecordBO.getLoanRecoedId());
        AssetBackRecordTypeEnum assetBackRecordTypeEnum = AssetBackRecordTypeEnum.getByCode(assetBackRecordBO.getType());
        AssertUtil.assertNotNull(assetDO, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资码不存在");
        AssertUtil.assertNotNull(assetBackRecordTypeEnum, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "归还物资类型不正确");
        //当物资状态为loaded或者为destroyed时候执行
        AssertUtil.assertTrue(!(assetLoanRecordDO.getStatus().equals(AssetLoanRecordStatusEnum.LOADED.getCode()) || assetLoanRecordDO.getStatus().equals(AssetLoanRecordStatusEnum.DESTROYED.getCode())), "物资已全部归还，无法再次提交归还请求");
        switch (assetBackRecordTypeEnum) {
            case BACK:
                assetDO.setRemain(assetDO.getRemain() + assetBackRecordBO.getAmount());
                if (assetDO.getRemain() > 0) {
                    //修改物资实体类信息 变为可借
                    assetDO.setStatus(AssetStatusEnum.ASSET_LOAN.getCode());
                }
                //修改借用记录剩余需要归还的数量
                assetLoanRecordDO.setRemain(assetLoanRecordDO.getRemain() - assetBackRecordBO.getAmount());
                //如果剩余需要归还的数量==0 则表示物资归还完毕 分状态
                //情况1：归还完毕但是有损坏的 设置物资借用状态为destroyed
                //情况2：归还没有损坏的，设置物资借用状态为loaded
                if (assetLoanRecordDO.getRemain() == 0) {
                    if (assetLoanRecordDO.getDistory() == 0) {
                        assetLoanRecordDO.setStatus(AssetLoanRecordStatusEnum.LOADED.getCode());
                    } else {
                        assetLoanRecordDO.setStatus(AssetLoanRecordStatusEnum.DESTROYED.getCode());
                    }
                }
                break;
            case DESTROY:
                assetDO.setDestroy(assetDO.getDestroy() + assetBackRecordBO.getAmount());
                if (assetDO.getDestroy() >= assetDO.getAmount()) {
                    assetDO.setStatus(AssetStatusEnum.ASSET_NOT_LOAN.getCode());
                }
                assetLoanRecordDO.setDistory(assetLoanRecordDO.getDistory() + assetBackRecordBO.getAmount());
                assetLoanRecordDO.setRemain(assetLoanRecordDO.getRemain() - assetBackRecordBO.getAmount());
                if (assetLoanRecordDO.getRemain() == 0) {
                    assetLoanRecordDO.setStatus(AssetLoanRecordStatusEnum.DESTROYED.getCode());
                }
                break;
            default:
                AssertUtil.assertTrue(null, "物资归还类型不存在");
        }
        if (assetBackRecordBO.getRemark() != null) {
            assetLoanRecordDO.setRemark(assetBackRecordBO.getRemark());
        }

        try {
            assetDORepo.save(assetDO);
            assetLoanDORepo.save(assetLoanRecordDO);
            AssetBackRecordDO assetBackRecordDO = assetBackDORepo.save(convert(assetBackRecordBO));
            return convert(assetBackRecordDO);
        } catch (Exception e) {
            //抛出异常
            throw new RuntimeException("归还物资失败");
        }
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public List<AssetBackRecordBO> queryAssetBackRecordByUserId(String userId) {
        List<AssetBackRecordDO> assetBackRecordDOList = assetBackDORepo.findAllByUserId(userId);
        return CollectionUtils.toStream(assetBackRecordDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    /**
     * @param assetId
     * @return
     */
    @Override
    public List<AssetBackRecordBO> queryAssetBackRecordByAssetId(String assetId) {
        List<AssetBackRecordDO> assetBackRecordDOList = assetBackDORepo.findAllByAssetId(assetId);
        return CollectionUtils.toStream(assetBackRecordDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssetBackRecordBO> findAllAssetBackRecordByLoanRecordId(String loanRecordId) {
        List<AssetBackRecordDO> assetBackRecordDOS = assetBackDORepo.findAllByLoanRecordId(loanRecordId);
        return CollectionUtils.toStream(assetBackRecordDOS)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    /**
     * @param assetBackRecordDO
     * @return
     */
    @SuppressWarnings("unchecked")
    private AssetBackRecordBO convert(AssetBackRecordDO assetBackRecordDO) {
        if (assetBackRecordDO == null) {
            return null;
        }
        AssetBackRecordBO assetBackRecordBO = new AssetBackRecordBO();
        AssetDO assetDO = assetDORepo.findByAssetId(assetBackRecordDO.getAssetId());
        assetBackRecordBO.setAmount(assetBackRecordDO.getAmount());
        assetBackRecordBO.setAssetId(assetBackRecordDO.getAssetId());
        String assetName = assetDO.getAssetName();
        assetBackRecordBO.setAssetName(assetName);
        assetBackRecordBO.setAssetType(assetBackRecordDO.getAssetType());
        assetBackRecordBO.setBackRecoedId(assetBackRecordDO.getBackRecoedId());
        assetBackRecordBO.setExtInfo(JSON.parseObject(assetBackRecordDO.getExtInfo(), Map.class));
        assetBackRecordBO.setLoanRecoedId(assetBackRecordDO.getLoanRecordId());
        assetBackRecordBO.setRemark(assetBackRecordDO.getRemark());
        assetBackRecordBO.setType(assetBackRecordDO.getType());
        assetBackRecordBO.setUserId(assetBackRecordDO.getUserId());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        assetBackRecordBO.setBackTime(formatter.format(assetBackRecordDO.getGmtCreate()));

        return assetBackRecordBO;
    }

    /**
     * @param assetBackRecordBO
     * @return
     */
    private AssetBackRecordDO convert(AssetBackRecordBO assetBackRecordBO) {
        if (assetBackRecordBO == null) {
            return null;
        }
        AssetBackRecordDO assetBackRecordDO = new AssetBackRecordDO();
        assetBackRecordDO.setAmount(assetBackRecordBO.getAmount());
        assetBackRecordDO.setAssetId(assetBackRecordBO.getAssetId());
        assetBackRecordDO.setAssetType(assetBackRecordBO.getAssetType());
        assetBackRecordDO.setBackRecoedId(assetBackRecordBO.getBackRecoedId());
        assetBackRecordDO.setExtInfo(JSON.toJSONString(assetBackRecordBO.getExtInfo()));
        assetBackRecordDO.setLoanRecordId(assetBackRecordBO.getLoanRecoedId());
        assetBackRecordDO.setRemark(assetBackRecordBO.getRemark());
        assetBackRecordDO.setType(assetBackRecordBO.getType());
        assetBackRecordDO.setUserId(assetBackRecordBO.getUserId());

        return assetBackRecordDO;
    }
}
