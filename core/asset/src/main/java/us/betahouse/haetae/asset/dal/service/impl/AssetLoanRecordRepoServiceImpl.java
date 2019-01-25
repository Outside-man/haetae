/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.dal.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.asset.dal.model.AssetLoanRecordDO;
import us.betahouse.haetae.asset.dal.repo.AssetLoanDORepo;
import us.betahouse.haetae.asset.dal.service.AssetLoanRecordRepoService;
import us.betahouse.haetae.asset.idfactory.BizIdFactory;
import us.betahouse.haetae.asset.model.basic.AssetLoanRecordBO;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.CollectionUtils;
import us.betahouse.util.utils.LoggerUtil;

import java.util.List;
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
     *
     * @param assetLoanRecordBO
     * @return
     */
    @Override
    public AssetLoanRecordBO createAssetLoanRecord(AssetLoanRecordBO assetLoanRecordBO) {
        // TODO 验证物资是否存在/可借用

        if (StringUtils.isBlank(assetLoanRecordBO.getLoanRecordId())) {
            assetLoanRecordBO.setLoanRecordId(assetBizFactory.getAssetLoadId());
        }
        return convert(assetLoanDORepo.save(convert(assetLoanRecordBO)));
    }

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
        // TODO 还要更新归还数量

        return convert(assetLoanDORepo.save(assetLoanRecordDO));
    }

    @Override
    public List<AssetLoanRecordBO> queryAssetLoanRecordByUserId(String userId) {
        List<AssetLoanRecordDO> assetLoanRecordDOList = assetLoanDORepo.findByUserId(userId);
        return CollectionUtils.toStream(assetLoanRecordDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    public AssetLoanRecordBO findAssetLoanRecordByLoadId(String loanId) {
        return convert(assetLoanDORepo.findByLoanRecordId(loanId));
    }

    @Override
    public List<AssetLoanRecordBO> findAssetLoanRecordByAssetId(String assetId) {
        return null;
    }

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
        assetLoanRecordBO.setAssetType(assetLoanRecordDO.getAssetType());
        assetLoanRecordBO.setUserId(assetLoanRecordDO.getUserId());
        assetLoanRecordBO.setStatus(assetLoanRecordDO.getStatus());
        assetLoanRecordBO.setLoanTime(assetLoanRecordDO.getLoanTime());
        assetLoanRecordBO.setBackTime(assetLoanRecordDO.getBackTime());
        assetLoanRecordBO.setRemark(assetLoanRecordDO.getRemark());

        return assetLoanRecordBO;
    }

    private AssetLoanRecordDO convert(AssetLoanRecordBO assetLoanRecordBO) {
        if (assetLoanRecordBO == null){
            return null;
        }
        AssetLoanRecordDO assetLoanRecordDO = new AssetLoanRecordDO();
        assetLoanRecordDO.setRemark(assetLoanRecordBO.getRemark());
        assetLoanRecordDO.setAssetId(assetLoanRecordBO.getAssetId());
        assetLoanRecordDO.setAmount(assetLoanRecordBO.getAmount());
        assetLoanRecordDO.setAssetType(assetLoanRecordBO.getAssetType());
        assetLoanRecordDO.setLoanRecordId(assetLoanRecordBO.getLoanRecordId());
        assetLoanRecordDO.setLoanTime(assetLoanRecordBO.getLoanTime());
        assetLoanRecordDO.setBackTime(assetLoanRecordBO.getBackTime());
        assetLoanRecordDO.setStatus(assetLoanRecordBO.getStatus());

        return assetLoanRecordDO;
    }
}
