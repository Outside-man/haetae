/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.dal.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.asset.builder.AssetBackRecordBOBulider;
import us.betahouse.haetae.asset.dal.model.AssetLoanRecordDO;
import us.betahouse.haetae.asset.dal.repo.AssetLoanDORepo;
import us.betahouse.haetae.asset.dal.service.AssetBackRecordRepoService;
import us.betahouse.haetae.asset.dal.service.AssetLoanRecordRepoService;
import us.betahouse.haetae.asset.enums.AssetLoanRecordStatusEnum;
import us.betahouse.haetae.asset.idfactory.BizIdFactory;
import us.betahouse.haetae.asset.model.basic.AssetLoanRecordBO;
import us.betahouse.util.utils.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author yiyuk.hxy
 * @version : AssetLoanRecordRepoServiceImpl.java 2019/01/23 20:18 yiyuk.hxy
 */
@Service
public class AssetLoanRecordRepoServiceImpl implements AssetLoanRecordRepoService {

    @Autowired
    private AssetLoanDORepo assetLoanDORepo;

    /**
     * id工厂
     */
    @Autowired
    private BizIdFactory assetBizFactory;

    /**
     * @param assetLoanRecordBO
     * @return
     */
    @Override
    public AssetLoanRecordBO createAssetLoanRecord(AssetLoanRecordBO assetLoanRecordBO) {
        if (StringUtils.isBlank(assetLoanRecordBO.getLoanRecordId())) {
            assetLoanRecordBO.setLoanRecordId(assetBizFactory.getAssetLoadId());
        }
        return convert(assetLoanDORepo.save(convert(assetLoanRecordBO)));
    }

    /**
     * @param assetLoanRecordBO
     * @return
     */
    @Override
    public AssetLoanRecordBO updateAssetLoanRecord(AssetLoanRecordBO assetLoanRecordBO) {
        AssetLoanRecordDO assetLoanRecordDO = assetLoanDORepo.findByLoanRecordId(assetLoanRecordBO.getLoanRecordId());
        AssetLoanRecordDO assetLoanRecordDO1 = convert(assetLoanRecordBO);
        if (assetLoanRecordDO1.getBackTime() != null) {
            assetLoanRecordDO.setLoanTime(assetLoanRecordDO1.getLoanTime());
        }
        if (assetLoanRecordDO1.getRemark() != null) {
            assetLoanRecordDO.setRemark(assetLoanRecordDO1.getRemark());
        }
        if (assetLoanRecordDO1.getRemain() != null) {
            assetLoanRecordDO.setRemain(assetLoanRecordDO1.getRemain() + assetLoanRecordDO.getRemain());
        }
        if (assetLoanRecordDO1.getDistory() != null) {
            assetLoanRecordDO.setDistory(assetLoanRecordDO1.getDistory() + assetLoanRecordDO.getDistory());
        }
        if (assetLoanRecordDO1.getAssetInfo() != null) {
            assetLoanRecordDO.setAssetInfo(assetLoanRecordDO1.getAssetInfo());
        }

        return convert(assetLoanDORepo.save(assetLoanRecordDO));
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public List<AssetLoanRecordBO> queryAssetLoanRecordByUserId(String userId) {
        List<AssetLoanRecordDO> assetLoanRecordDOList = assetLoanDORepo.findByUserId(userId);
        return CollectionUtils.toStream(assetLoanRecordDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    /**
     * @param loanId
     * @return
     */
    @Override
    public AssetLoanRecordBO findAssetLoanRecordByLoadId(String loanId) {
        return convert(assetLoanDORepo.findByLoanRecordId(loanId));
    }

    /**
     * @param assetId
     * @return
     */
    @Override
    public List<AssetLoanRecordBO> queryAllAssetLoanRecordByAssetId(String assetId) {
        List<AssetLoanRecordDO> assetLoanRecordDOList = assetLoanDORepo.findAllRecordByAssetId(assetId);
        return CollectionUtils.toStream(assetLoanRecordDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    /**
     * @param assetId
     * @return
     */
    @Override
    public List<AssetLoanRecordBO> queryDistoryRecordByAssetId(String assetId) {
        List<AssetLoanRecordDO> assetLoanRecordDOList = assetLoanDORepo.findAllRecordByAssetId(assetId);
        //筛选出报损记录
        for (int i = 0; i < assetLoanRecordDOList.size(); ++i) {
            AssetLoanRecordStatusEnum assetLoanRecordStatusEnum = AssetLoanRecordStatusEnum
                    .getByCode(assetLoanRecordDOList.get(i).getStatus());
            switch (assetLoanRecordStatusEnum) {
                case ASSET_LOAN_RECORD_LOAN:
                    assetLoanRecordDOList.remove(i);
                    break;
                case ASSET_LOAN_RECORD_NOTLOAN:
                    assetLoanRecordDOList.remove(i);
                    break;
                default:
                    break;
            }
        }
        return CollectionUtils.toStream(assetLoanRecordDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    /**
     * @param assetId
     * @return
     */
    @Override
    public List<AssetLoanRecordBO> queryAssetLoanRecordByAssetId(String assetId) {
        List<AssetLoanRecordDO> assetLoanRecordDOList = assetLoanDORepo.findAllRecordByAssetId(assetId);
        //筛选出借用记录
        for (int i = 0; i < assetLoanRecordDOList.size(); ++i) {
            AssetLoanRecordStatusEnum assetLoanRecordStatusEnum = AssetLoanRecordStatusEnum
                    .getByCode(assetLoanRecordDOList.get(i).getStatus());
            switch (assetLoanRecordStatusEnum) {
                case ASSET_LOAN_RECORD_LOAN:
                    assetLoanRecordDOList.remove(i);
                    break;
                case ASSET_LOAN_RECORD_DISTORY:
                    assetLoanRecordDOList.remove(i);
                    break;
                default:
                    break;
            }
        }
        return CollectionUtils.toStream(assetLoanRecordDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    /**
     * @param name
     * @return
     */
    @Override
    public List<AssetLoanRecordBO> queryAssetLoanRecordByName(String name) {
        return null;
    }

    /**
     * @param assetLoanRecordDO
     * @return
     */
    @SuppressWarnings("unchecked")
    private AssetLoanRecordBO convert(AssetLoanRecordDO assetLoanRecordDO) {
        if (assetLoanRecordDO == null) {
            return null;
        }
        AssetLoanRecordBO assetLoanRecordBO = new AssetLoanRecordBO();
        assetLoanRecordBO.setLoanRecordId(assetLoanRecordDO.getLoanRecordId());
        assetLoanRecordBO.setAssetId(assetLoanRecordDO.getAssetId());
        assetLoanRecordBO.setAmount(assetLoanRecordDO.getAmount());
        assetLoanRecordBO.setRemain(assetLoanRecordDO.getRemain());
        assetLoanRecordBO.setDistory(assetLoanRecordDO.getDistory());
        assetLoanRecordBO.setAssetType(assetLoanRecordDO.getAssetType());
        assetLoanRecordBO.setUserId(assetLoanRecordDO.getUserId());
        assetLoanRecordBO.setStatus(assetLoanRecordDO.getStatus());
        assetLoanRecordBO.setRemark(assetLoanRecordDO.getRemark());
        assetLoanRecordBO.setExtInfo(JSON.parseObject(assetLoanRecordDO.getExtInfo(), Map.class));
        assetLoanRecordBO.setAssetInfo(assetLoanRecordDO.getAssetInfo());

        return assetLoanRecordBO;
    }

    /**
     * @param assetLoanRecordBO
     * @return
     */
    private AssetLoanRecordDO convert(AssetLoanRecordBO assetLoanRecordBO) {
        if (assetLoanRecordBO == null) {
            return null;
        }
        AssetLoanRecordDO assetLoanRecordDO = new AssetLoanRecordDO();
        assetLoanRecordDO.setUserId(assetLoanRecordBO.getUserId());
        assetLoanRecordDO.setRemark(assetLoanRecordBO.getRemark());
        assetLoanRecordDO.setAssetId(assetLoanRecordBO.getAssetId());
        assetLoanRecordDO.setAmount(assetLoanRecordBO.getAmount());
        assetLoanRecordDO.setRemain(assetLoanRecordBO.getRemain());
        assetLoanRecordDO.setDistory(assetLoanRecordBO.getDistory());
        assetLoanRecordDO.setAssetType(assetLoanRecordBO.getAssetType());
        assetLoanRecordDO.setLoanRecordId(assetLoanRecordBO.getLoanRecordId());
        assetLoanRecordDO.setStatus(assetLoanRecordBO.getStatus());
        assetLoanRecordDO.setExtInfo(JSON.toJSONString(assetLoanRecordBO.getExtInfo()));
        assetLoanRecordDO.setAssetInfo(assetLoanRecordBO.getAssetInfo());

        return assetLoanRecordDO;
    }
}
