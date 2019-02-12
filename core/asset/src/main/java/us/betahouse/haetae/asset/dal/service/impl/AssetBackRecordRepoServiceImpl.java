/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.dal.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.asset.dal.model.AssetBackRecordDO;
import us.betahouse.haetae.asset.dal.repo.AssetBackDORepo;
import us.betahouse.haetae.asset.dal.service.AssetBackRecordRepoService;
import us.betahouse.haetae.asset.idfactory.BizIdFactory;
import us.betahouse.haetae.asset.model.basic.AssetBackRecordBO;
import us.betahouse.util.utils.CollectionUtils;

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

    /**
     * @param assetBackRecordBO
     * @return
     */
    @Override
    public AssetBackRecordBO createAssetBackRecord(AssetBackRecordBO assetBackRecordBO) {
        if (StringUtils.isBlank(assetBackRecordBO.getBackRecoedId())) {
            assetBackRecordBO.setBackRecoedId(assetBizFactory.getAssetBackId());
        }
        return convert(assetBackDORepo.save(convert(assetBackRecordBO)));
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public List<AssetBackRecordBO> queryAssetBackRecordByUserId(String userId) {
        List<AssetBackRecordDO> assetBcakRecordDOList = assetBackDORepo.findAllByUserId(userId);
        return CollectionUtils.toStream(assetBcakRecordDOList)
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
        List<AssetBackRecordDO> assetBcakRecordDOList = assetBackDORepo.findAllByAssetId(assetId);
        return CollectionUtils.toStream(assetBcakRecordDOList)
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
        assetBackRecordBO.setAmount(assetBackRecordDO.getAmount());
        assetBackRecordBO.setAssetId(assetBackRecordDO.getAssetId());
        assetBackRecordBO.setAssetType(assetBackRecordDO.getAssetType());
        assetBackRecordBO.setBackRecoedId(assetBackRecordDO.getBackRecoedId());
        assetBackRecordBO.setExtInfo(JSON.parseObject(assetBackRecordDO.getExtInfo(), Map.class));
        assetBackRecordBO.setLoanRecoedId(assetBackRecordDO.getLoanRecoedId());
        assetBackRecordBO.setRemark(assetBackRecordDO.getRemark());
        assetBackRecordBO.setType(assetBackRecordDO.getType());
        assetBackRecordBO.setUserId(assetBackRecordDO.getUserId());

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
        assetBackRecordDO.setLoanRecoedId(assetBackRecordBO.getLoanRecoedId());
        assetBackRecordDO.setRemark(assetBackRecordBO.getRemark());
        assetBackRecordDO.setType(assetBackRecordBO.getType());
        assetBackRecordDO.setUserId(assetBackRecordBO.getUserId());

        return assetBackRecordDO;
    }
}
