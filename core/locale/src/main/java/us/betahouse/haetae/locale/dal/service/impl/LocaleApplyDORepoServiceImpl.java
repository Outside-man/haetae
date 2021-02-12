/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.locale.dal.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.locale.dal.model.LocaleApplyDO;
import us.betahouse.haetae.locale.dal.repo.LocaleApplyDORepo;
import us.betahouse.haetae.locale.dal.service.LocaleApplyDORepoService;
import us.betahouse.haetae.locale.idfactory.BizIdFactory;
import us.betahouse.haetae.locale.model.basic.LocaleApplyBO;
import us.betahouse.haetae.locale.model.common.PageList;
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

        return convert(localeApplyDORepo.save(convert(localeApplyBO)));
    }

    /**
     * 通过场地申请id查询
     *
     * @return List<LocaleApplyBO>
     */
    @Override
    public LocaleApplyBO queryByLocaleApplyId(String localeApplyId) {
        return convert(localeApplyDORepo.findByLocaleApplyId(localeApplyId));
    }

    /**
     * 通过申请状态 查询
     *
     * @param status
     * @return
     */
    @Override
    public List<LocaleApplyBO> queryLocaleApplyByStatus(String status) {
        List<LocaleApplyDO> localeApplyDOList = localeApplyDORepo.findAllByStatus(status);

        return CollectionUtils.toStream(localeApplyDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }


    /**
     * 通过申请状态查询数据 DESC
     *
     * @param status 状态
     * @param page   页面
     * @param limit  每页行数
     * @return
     */
    @Override
    public PageList<LocaleApplyBO> queryLocaleApplyByStatusAndPagerDESC(String status, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit);

        return new PageList<>(localeApplyDORepo.findAllByStatusContainsOrderByLocaleApplyIdDesc(pageable, status), this::convert);
    }

    /**
     * 通过申请状态查询数据 ASC
     *
     * @param status 状态
     * @param page   页面
     * @param limit  每页行数
     * @return
     */
    @Override
    public PageList<LocaleApplyBO> queryLocaleApplyByStatusAndPagerASC(String status, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit);

        return new PageList<>(localeApplyDORepo.findAllByStatusContains(pageable, status), this::convert);
    }

    /**
     * 通过用户id查询数据 ASC
     *
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageList<LocaleApplyBO> queryLocaleApplyByUserIdAndPagerASC(String userId, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit);

        return new PageList<>(localeApplyDORepo.findAllByStatusContains(pageable, userId), this::convert);
    }

    /**
     * 通过用户id查询数据 DESC
     *
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageList<LocaleApplyBO> queryLocaleApplyByUserIdAndPagerDESC(String userId, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit);

        return new PageList<>(localeApplyDORepo.findAllByUserIdContainsOrderByLocaleApplyId(pageable, userId), this::convert);
    }

    /**
     * 更新场地申请状态
     *
     * @param localeApplyBO
     * @return
     */
    @Override
    public LocaleApplyBO updateLocaleApply(LocaleApplyBO localeApplyBO) {
        LocaleApplyDO localeApplyDO = localeApplyDORepo.findByLocaleApplyId(localeApplyBO.getLocaleApplyId());
        localeApplyDO.setStatus(localeApplyBO.getStatus());
        localeApplyDO.setExtInfo(JSON.toJSONString(localeApplyBO.getExtInfo()));
        return convert(localeApplyDORepo.save(localeApplyDO));
    }

    private Object convert(Object o) {
        if (o instanceof LocaleApplyDO) {
            return convert((LocaleApplyDO) o);
        } else if (o instanceof LocaleApplyBO) {
            return convert((LocaleApplyBO) o);
        } else {
            return null;
        }
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
        localeApplyBO.setCreate(localeApplyDO.getGmtCreate());
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
        localeApplyBO.setOrganizationName(localeApplyDO.getOrganizationName());
        localeApplyBO.setExtInfo(JSON.parseObject(localeApplyDO.getExtInfo(), Map.class));
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
        localeApplyDO.setGmtCreate(localeApplyBO.getCreate());
        localeApplyDO.setTel(localeApplyBO.getTel());
        localeApplyDO.setUsages(localeApplyBO.getUsages());
        localeApplyDO.setRemark(localeApplyBO.getRemark());
        localeApplyDO.setOrganizationName(localeApplyBO.getOrganizationName());
        localeApplyDO.setDocument(localeApplyBO.getDocument());
        localeApplyDO.setStatus(localeApplyBO.getStatus());
        localeApplyDO.setTimeDate(localeApplyBO.getTimeDate());
        localeApplyDO.setTimeBucket(localeApplyBO.getTimeBucket());
        localeApplyDO.setLocaleCode(localeApplyBO.getLocaleCode());
        localeApplyDO.setLocaleApplyId(localeApplyBO.getLocaleApplyId());
        localeApplyDO.setLocaleId(localeApplyBO.getLocaleId());
        localeApplyDO.setLocaleAreaId(localeApplyBO.getLocaleAreaId());
        localeApplyDO.setUserId(localeApplyBO.getUserId());
        localeApplyDO.setExtInfo(JSON.toJSONString(localeApplyBO.getExtInfo()));
        return localeApplyDO;
    }
}
