/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.locale.dal.service.impl;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.locale.dal.model.LocaleDO;
import us.betahouse.haetae.locale.dal.repo.LocaleDORepo;
import us.betahouse.haetae.locale.dal.service.LocaleDORepoService;
import us.betahouse.haetae.locale.idfactory.BizIdFactory;
import us.betahouse.haetae.locale.model.basic.LocaleBO;
import us.betahouse.util.utils.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author NathanDai
 * @version :  2019-07-03 21:24 NathanDai
 */

@Service
public class LocaleDORepoServiceImpl implements LocaleDORepoService {
    private final Logger LOGGER = LoggerFactory.getLogger(LocaleDORepoServiceImpl.class);
    @Autowired
    LocaleDORepo localeDORepo;
    /**
     * id工厂
     */
    @Autowired
    private BizIdFactory localBizFactory;

    /**
     * 新建一个场地
     *
     * @param localeBO
     * @return
     */
    @Override
    public LocaleBO createLocale(LocaleBO localeBO) {
        localeBO.setLocaleId(localBizFactory.getLocaleId());

        return convert(localeDORepo.save(convert(localeBO)));
    }

    /**
     * 更新场地状态
     *
     * @param localeBO
     * @return
     */
    @Override
    public LocaleBO updateLocale(LocaleBO localeBO) {
        LocaleDO localeDO = localeDORepo.findByLocaleId(localeBO.getLocaleId());
        localeDO.setStatus(localeBO.getStatus());

        return convert(localeDORepo.save(localeDO));
    }

    /**
     * 查询localeName
     *
     * @param localeCode
     * @return
     */
    @Override
    public LocaleBO findLocaleName(String localeCode) {
        LocaleDO localeDO = localeDORepo.findByLocaleCode(localeCode);

        return convert(localeDO);
    }

    /**
     * 通过 场地状态 查询所有场地
     *
     * @param Status
     * @return
     */
    @Override
    public List<LocaleBO> queryAllLocalesByStatus(String Status) {
        List<LocaleDO> localeDOList = localeDORepo.findAllByStatus(Status);

        return CollectionUtils.toStream(localeDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }


    /**
     * 场地DO2BO
     *
     * @param localeDO
     * @return
     */
    @SuppressWarnings("unchecked")
    private LocaleBO convert(LocaleDO localeDO) {
        if (localeDO == null) {
            return null;
        }
        LocaleBO localeBO = new LocaleBO();
        localeBO.setLocaleId(localeDO.getLocaleId());
        localeBO.setLocaleName(localeDO.getLocaleName());
        localeBO.setLocaleCode(localeDO.getLocaleCode());
        localeBO.setStatus(localeDO.getStatus());
        localeBO.setExtInfo(JSON.parseObject(localeDO.getExtInfo(),Map.class));
        return localeBO;
    }

    /**
     * 场地BO2DO
     *
     * @param localeBO
     * @return
     */
    private LocaleDO convert(LocaleBO localeBO) {
        if (localeBO == null) {
            return null;
        }
        LocaleDO LocaleDO = new LocaleDO();
        LocaleDO.setLocaleId(localeBO.getLocaleId());
        LocaleDO.setLocaleName(localeBO.getLocaleName());
        LocaleDO.setLocaleCode(localeBO.getLocaleCode());
        LocaleDO.setStatus(localeBO.getStatus());
        return LocaleDO;
    }
}
