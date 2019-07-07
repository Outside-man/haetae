/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.locale.dal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.locale.dal.model.LocaleApplyDO;
import us.betahouse.haetae.locale.dal.repo.LocaleApplyDORepo;
import us.betahouse.haetae.locale.dal.service.LocaleApplyDORepoService;
import us.betahouse.haetae.locale.idfactory.BizIdFactory;
import us.betahouse.haetae.locale.model.basic.LocaleApplyBO;
import us.betahouse.util.utils.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author NathanDai
 * @version :  2019-07-03 21:24 NathanDai
 */

@Service
public class LocaleApplyDORepoServiceImpl implements LocaleApplyDORepoService {
    /**
     * id工厂
     */
    @Autowired
    private BizIdFactory localBizFactory;

    @Autowired
    private LocaleApplyDORepo localeApplyDORepo;

    /**
     * 新建一个场地申请
     *
     * @param localeApplyBO
     * @return
     */
    @Override
    public LocaleApplyBO createLocaleApply(LocaleApplyBO localeApplyBO) {
        localeApplyBO.setLocaleApplyId(localBizFactory.getLocaleApplyId());
        System.out.println(convert(localeApplyBO) + "localeApplyDO");
        return convert(localeApplyDORepo.save(convert(localeApplyBO)));
    }

    /**
     * 查询所有场地申请
     *
     * @return
     */
    @Override
    public List<LocaleApplyBO> queryAllLocaleApply() {
        List<LocaleApplyDO> localeApplyDOList = localeApplyDORepo.findAll();
        return CollectionUtils.toStream(localeApplyDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }


    /**
     * 申请DO2BO
     *
     * @param localeApplyDO
     * @return
     */
    @SuppressWarnings("unchecked")
    private LocaleApplyBO convert(LocaleApplyDO localeApplyDO) {
        if (localeApplyDO == null) {
            return null;
        }
        LocaleApplyBO localeApplyBO = new LocaleApplyBO();
        localeApplyBO.setTel(localeApplyDO.getTel());
        localeApplyBO.setUsages(localeApplyDO.getUsages());
        localeApplyBO.setRemark(localeApplyDO.getRemark());
        localeApplyBO.setDocument(localeApplyDO.getDocument());
        localeApplyBO.setStatus(localeApplyDO.getStatus());
        localeApplyBO.setTimeDate(localeApplyDO.getTimeDate());
        localeApplyBO.setTimeBucket(localeApplyDO.getTimeBucket());
        localeApplyBO.setLocaleCode(localeApplyDO.getLocaleCode());
        localeApplyBO.setLocaleApplyId(localeApplyDO.getLocaleApplyId());
        localeApplyBO.setLocaleId(localeApplyDO.getLocaleId());
        localeApplyBO.setLocaleAreaId(localeApplyDO.getLocaleAreaId());
        localeApplyBO.setUserId(localeApplyDO.getUserId());
        return localeApplyBO;
    }

    /**
     * 申请BO2DO
     *
     * @param localeApplyBO
     * @return
     */
    private LocaleApplyDO convert(LocaleApplyBO localeApplyBO) {
        if (localeApplyBO == null) {
            return null;
        }
        LocaleApplyDO localeApplyDO = new LocaleApplyDO();
        localeApplyDO.setTel(localeApplyBO.getTel());
        localeApplyDO.setUsages(localeApplyBO.getUsages());
        localeApplyDO.setRemark(localeApplyBO.getRemark());
        localeApplyDO.setDocument(localeApplyBO.getDocument());
        localeApplyDO.setStatus(localeApplyBO.getStatus());
        localeApplyDO.setTimeDate(localeApplyBO.getTimeDate());
        localeApplyDO.setTimeBucket(localeApplyBO.getTimeBucket());
        localeApplyDO.setLocaleCode(localeApplyBO.getLocaleCode());
        localeApplyDO.setLocaleApplyId(localeApplyBO.getLocaleApplyId());
        localeApplyDO.setLocaleId(localeApplyBO.getLocaleId());
        localeApplyDO.setLocaleAreaId(localeApplyBO.getLocaleAreaId());
        localeApplyDO.setUserId(localeApplyBO.getUserId());
        return localeApplyDO;
    }
}
