/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.locale.dal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.locale.dal.model.LocaleAreaDO;
import us.betahouse.haetae.locale.dal.repo.LocaleAreaDORepo;
import us.betahouse.haetae.locale.dal.service.LocaleAreaDORepoService;
import us.betahouse.haetae.locale.idfactory.BizIdFactory;
import us.betahouse.haetae.locale.model.basic.LocaleAreaBO;
import us.betahouse.util.utils.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author NathanDai
 * @version :  2019-07-03 21:24 NathanDai
 */

@Service
public class LocaleAreaDORepoServiceImpl implements LocaleAreaDORepoService {
    /**
     * id工厂
     */
    @Autowired
    private BizIdFactory localBizFactory;

    @Autowired
    private LocaleAreaDORepo localeAreaDORepo;

    /**
     * 新建一个场地占用
     *
     * @param localeAreaBO
     * @return
     */
    @Override
    public LocaleAreaBO createLocaleArea(LocaleAreaBO localeAreaBO) {
        localeAreaBO.setLocaleAreaId(localBizFactory.getLocaleAreaId());
        return convert(localeAreaDORepo.save(convert(localeAreaBO)));
    }

    /**
     * 更新场地占用的状态
     *
     * @param localeAreaBO
     * @return
     */
    @Override
    public LocaleAreaBO updateLocaleArea(LocaleAreaBO localeAreaBO) {
        LocaleAreaDO localeAreaDO = localeAreaDORepo.findByLocaleAreaId(localeAreaBO.getLocaleAreaId());
        localeAreaDO.setStatus(localeAreaBO.getStatus());
        return convert(localeAreaDORepo.save(localeAreaDO));
    }

    /**
     * 通过 场地id 日期 状态!=CANCEL 查询场地占用
     *
     * @param LocaleId
     * @param TimeDate
     * @param Status
     * @return
     */
    @Override
    public List<LocaleAreaBO> queryLocaleAreasByLocaleIdAndTimeDateAndStatusNot(String LocaleId, String TimeDate, String Status) {
        List<LocaleAreaDO> localeAreaDOList = localeAreaDORepo.findAllByLocaleIdAndTimeDateAndStatusNot(LocaleId, TimeDate, Status);

        return CollectionUtils.toStream(localeAreaDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    /**
     * 校验场地是否被占用
     *
     * @param LocaleId
     * @param TimeDate
     * @param TimeBucket
     * @param Status
     * @return
     */
    @Override
    public LocaleAreaBO queryLocaleAreasByLocaleIdAndTimeDateAndTimeBucketAndStatusNot(String LocaleId, String TimeDate, String TimeBucket, String Status) {
        LocaleAreaDO localeAreaDO = localeAreaDORepo.findByLocaleIdAndTimeDateAndTimeBucketAndStatusNot(LocaleId, TimeDate, TimeBucket, Status);

        return convert(localeAreaDORepo.save(localeAreaDO));
    }

    /**
     * 场地占用DO转BO
     *
     * @param localeAreaDO
     * @return
     */
    @SuppressWarnings("unchecked")
    private LocaleAreaBO convert(LocaleAreaDO localeAreaDO) {
        if (localeAreaDO == null) {
            return null;
        }
        LocaleAreaBO localeAreaBO = new LocaleAreaBO();
        localeAreaBO.setLocaleAreaId(localeAreaDO.getLocaleAreaId());
        localeAreaBO.setLocaleId(localeAreaDO.getLocaleId());
        localeAreaBO.setUserId(localeAreaDO.getUserId());
        localeAreaBO.setTimeDate(localeAreaDO.getTimeDate());
        localeAreaBO.setTimeBucket(localeAreaDO.getTimeBucket());
        localeAreaBO.setStatus(localeAreaDO.getStatus());
        return localeAreaBO;
    }

    /**
     * @param localeAreaBO
     * @return
     */
    private LocaleAreaDO convert(LocaleAreaBO localeAreaBO) {
        if (localeAreaBO == null) {
            return null;
        }
        LocaleAreaDO localeAreaDO = new LocaleAreaDO();
        localeAreaDO.setLocaleAreaId(localeAreaBO.getLocaleAreaId());
        localeAreaDO.setLocaleId(localeAreaBO.getLocaleId());
        localeAreaDO.setUserId(localeAreaBO.getUserId());
        localeAreaDO.setTimeDate(localeAreaBO.getTimeDate());
        localeAreaDO.setTimeBucket(localeAreaBO.getTimeBucket());
        localeAreaDO.setStatus(localeAreaBO.getStatus());
        return localeAreaDO;
    }
}
