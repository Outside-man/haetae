/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.dal.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.asset.dal.model.AssetDO;
import us.betahouse.haetae.asset.dal.model.AssetLoanRecordDO;
import us.betahouse.haetae.asset.dal.repo.AssetDORepo;
import us.betahouse.haetae.asset.dal.repo.AssetLoanDORepo;
import us.betahouse.haetae.asset.dal.service.AssetLoanRecordRepoService;
import us.betahouse.haetae.asset.dal.service.AssetRepoService;
import us.betahouse.haetae.asset.enums.AssetLoanRecordStatusEnum;
import us.betahouse.haetae.asset.enums.AssetStatusEnum;
import us.betahouse.haetae.asset.idfactory.BizIdFactory;
import us.betahouse.haetae.asset.model.basic.AssetLoanRecordBO;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;

import java.text.SimpleDateFormat;
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

    @Autowired
    private BizIdFactory assetBizFactory;

    @Autowired
    private AssetDORepo assetDORepo;

    @Autowired
    private AssetRepoService assetRepoService;

    /**
     * @param assetLoanRecordBO
     * @return
     */
    @Override
    public AssetLoanRecordBO createAssetLoanRecord(AssetLoanRecordBO assetLoanRecordBO) {
        if (StringUtils.isBlank(assetLoanRecordBO.getLoanRecordId())) {
            assetLoanRecordBO.setLoanRecordId(assetBizFactory.getAssetLoadId());
        }
        AssetDO assetDO = assetDORepo.findByAssetId(assetLoanRecordBO.getAssetId());
        //当前减去借用后剩余的物资数量
        int nowRemainNumber = assetDO.getRemain() - assetLoanRecordBO.getAmount();
        AssertUtil.assertTrue(nowRemainNumber >= 0, "借用物资数量不能超过物资剩余数量");
        assetDO.setRemain(nowRemainNumber);
        //物资状态为可借和不可借 如果剩余数量为0 则表示物资数量不足，改为不可借
        if (assetDO.getRemain() == 0) {
            assetDO.setStatus(AssetStatusEnum.ASSET_NOT_LOAN.getCode());
        }
        assetDORepo.save(assetDO);
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
            assetLoanRecordDO.setBackTime(assetLoanRecordDO1.getBackTime());
        }
        if (assetLoanRecordDO1.getRemark() != null) {
            assetLoanRecordDO.setRemark(assetLoanRecordDO1.getRemark());
        }
        if (assetLoanRecordDO1.getRemain() != null) {
            assetLoanRecordDO.setRemain(assetLoanRecordDO1.getRemain() - assetLoanRecordDO.getRemain() - assetLoanRecordDO1.getDistory());
        }
        if (assetLoanRecordDO1.getDistory() != null) {
            assetLoanRecordDO.setDistory(assetLoanRecordDO1.getDistory() + assetLoanRecordDO.getDistory());
        }
        if (assetLoanRecordDO1.getAssetInfo() != null) {
            assetLoanRecordDO.setAssetInfo(assetLoanRecordDO1.getAssetInfo());
        }
        if (0 == assetLoanRecordDO.getRemain()) {
            assetLoanRecordDO.setStatus("assetNotLoan");
        }
        if (assetLoanRecordDO.getAmount() == assetLoanRecordDO.getDistory()) {
            assetLoanRecordDO.setStatus("assetDistory");
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
                case LOADED:
                    assetLoanRecordDOList.remove(i);
                    break;
                case LOAN:
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
                case DESTROYED:
                    assetLoanRecordDOList.remove(i);
                    break;
                case LOADED:
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
     * @param assetId
     * @return
     */
    @Override
    public void delete(String assetId) {
        assetLoanDORepo.deleteByAssetId(assetId);
    }

    /**
     *
     * DO转BO BO添加学生部分信息
     * @param assetLoanRecordDO
     * @return
     */
    @SuppressWarnings("unchecked")
    private AssetLoanRecordBO convert(AssetLoanRecordDO assetLoanRecordDO) {
        if (assetLoanRecordDO == null) {
            return null;
        }
        AssetLoanRecordBO assetLoanRecordBO = new AssetLoanRecordBO();
        String assetName = assetRepoService.findByAssetId(assetLoanRecordDO.getAssetId()).getAssetName();
        assetLoanRecordBO.setLoanRecordId(assetLoanRecordDO.getLoanRecordId());
        assetLoanRecordBO.setAssetId(assetLoanRecordDO.getAssetId());
        assetLoanRecordBO.setAmount(assetLoanRecordDO.getAmount());
        assetLoanRecordBO.setRemain(assetLoanRecordDO.getRemain());
        assetLoanRecordBO.setDistory(assetLoanRecordDO.getDistory());
        assetLoanRecordBO.setAssetType(assetLoanRecordDO.getAssetType());
        assetLoanRecordBO.setUserId(assetLoanRecordDO.getUserId());
        assetLoanRecordBO.setStatus(assetLoanRecordDO.getStatus());
        assetLoanRecordBO.setRemark(assetLoanRecordDO.getRemark());
        assetLoanRecordBO.setAssetName(assetName);
        assetLoanRecordBO.setExtInfo(JSON.parseObject(assetLoanRecordDO.getExtInfo(), Map.class));
        assetLoanRecordBO.setAssetInfo(assetLoanRecordDO.getAssetInfo());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        assetLoanRecordBO.setCreateTime(formatter.format(assetLoanRecordDO.getGmtCreate()));
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
