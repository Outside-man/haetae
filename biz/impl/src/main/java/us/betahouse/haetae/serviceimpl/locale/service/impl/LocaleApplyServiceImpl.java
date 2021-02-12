/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.serviceimpl.locale.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.locale.dal.service.LocaleApplyDORepoService;
import us.betahouse.haetae.locale.enums.LocaleApplyStatusEnum;
import us.betahouse.haetae.locale.enums.LocaleAreaStatusEnum;
import us.betahouse.haetae.locale.manager.LocaleApplyManager;
import us.betahouse.haetae.locale.manager.LocaleAreaManager;
import us.betahouse.haetae.locale.manager.LocaleManager;
import us.betahouse.haetae.locale.model.basic.LocaleApplyBO;
import us.betahouse.haetae.locale.model.common.PageList;
import us.betahouse.haetae.locale.request.LocaleApplyRequest;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyPerm;
import us.betahouse.haetae.serviceimpl.locale.constant.LocalePermType;
import us.betahouse.haetae.serviceimpl.locale.request.LocaleApplyManagerRequest;
import us.betahouse.haetae.serviceimpl.locale.request.LocaleAreaManagerRequest;
import us.betahouse.haetae.serviceimpl.locale.request.builder.LocaleAreaManagerRequestBuilder;
import us.betahouse.haetae.serviceimpl.locale.service.LocaleApplyService;
import us.betahouse.haetae.serviceimpl.locale.service.LocaleAreaService;
import us.betahouse.haetae.user.user.service.UserBasicService;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author NathanDai
 * @version :  2019-07-05 05:33 NathanDai
 */

@Service
public class LocaleApplyServiceImpl implements LocaleApplyService {

    /**
     * 系统结束标志
     */
    private final static String SYSTEM_FINISH_SIGN = "systemFinish";

    @Autowired
    private LocaleApplyManager localeApplyManager;

    @Autowired
    private LocaleAreaManager localeAreaManager;

    @Autowired
    private LocaleAreaService localeAreaService;

    @Autowired
    private LocaleApplyDORepoService localeApplyDORepoService;

    @Autowired
    private LocaleManager localeManager;

    @Autowired
    private UserBasicService userBasicService;

    /**
     * 创建场地申请
     *
     * @param request
     * @param context
     * @return
     */
    @Override
    @VerifyPerm(permType = LocalePermType.LOCALE_APPLY)
    @Transactional(rollbackFor = Exception.class)
    public LocaleApplyBO create(LocaleApplyManagerRequest request, OperateContext context) {
        LocaleApplyStatusEnum localeApplyStatusEnum = LocaleApplyStatusEnum.getByCode(request.getStatus());
        AssertUtil.assertNotNull(localeApplyStatusEnum, "申请场地状态不存在");

        //更新场地占用状态 SUBMITING->APPLYING
        LocaleAreaManagerRequest localeAreaManagerRequest = LocaleAreaManagerRequestBuilder.getInstance()
                //填充场地占用id
                .withLocaleAreaId(request.getLocaleAreaId())
                //填充场地占用状态
                .withStatus(LocaleAreaStatusEnum.APPLYING.getCode())
                //鉴权的时候要用
                .withUserId(request.getUserId())
                .build();
        localeAreaService.update(localeAreaManagerRequest, context);

        return localeApplyManager.create(request);
    }

    /**
     * 更新场地申请状态
     * 管理员 学工
     *
     * @param request
     * @param context
     * @return
     */
    @Override
    @VerifyPerm(permType = LocalePermType.APPLY_FIRST_CHECK)
    @Transactional(rollbackFor = Exception.class)
    public LocaleApplyBO updateAdminFirst(LocaleApplyManagerRequest request, OperateContext context) {
        if(StringUtils.isNotBlank(request.getFailureMessage())){
            request.setFailureMessage("第一轮审批未过:"+request.getFailureMessage());
        }else{
            request.setFailureMessage("第一轮审批未过");
        }
        return updateStatus(request);
    }

    /**
     * 更新场地申请状态
     * 管理员 团委
     *
     * @param request
     * @param context
     * @return
     */
    @Override
    @VerifyPerm(permType = LocalePermType.APPLY_CHECK)
    @Transactional(rollbackFor = Exception.class)
    public LocaleApplyBO updateAdminSecond(LocaleApplyManagerRequest request, OperateContext context) {
        if(StringUtils.isNotBlank(request.getFailureMessage())){
            request.setFailureMessage("第二轮审批未过:"+request.getFailureMessage());
        }else{
            request.setFailureMessage("第二轮审批未过");
        }
        return updateStatus(request);
    }

    /**
     * 更新场地申请状态
     * 申请人
     *
     * @param request
     * @param context
     * @return
     */
    @Override
    @VerifyPerm(permType = LocalePermType.LOCALE_APPLY)
    @Transactional(rollbackFor = Exception.class)
    public LocaleApplyBO updateStatusByUser(LocaleApplyManagerRequest request, OperateContext context) {
        if(StringUtils.isNotBlank(request.getFailureMessage())){
            request.setFailureMessage("自己取消:"+request.getFailureMessage());
        }else{
            request.setFailureMessage("自己取消");
        }
         return updateStatus(request);
    }

    /**
     * 通过场地申请id查询
     *
     * @param request
     * @param context
     * @return
     */
    @Override
    public LocaleApplyBO findByLocaleId(LocaleApplyManagerRequest request, OperateContext context) {
        LocaleApplyBO localeApplyBO = localeApplyManager.findByLocaleApplyId(request);
        return localeApplyBO;
    }

    /**
     * 查询场地申请
     * 学工 查询
     *
     * @param request
     * @param context
     * @return
     */
    @Override
    @VerifyPerm(permType = LocalePermType.APPLY_FIRST_CHECK)
    @Transactional(rollbackFor = Exception.class)
    public PageList<LocaleApplyBO> findAllByStatusFirst(LocaleApplyManagerRequest request, OperateContext context) {
        LocaleApplyStatusEnum localeApplyStatusEnum = LocaleApplyStatusEnum.getByCode(request.getStatus());
        AssertUtil.assertNotNull(localeApplyStatusEnum, "申请场地状态不存在");

        //默认值 状态不限 第0页 每页十条 逆序
        String status = "";
        Integer page = 0;
        Integer limit = 10;
        String orderRule = "DESC";

        if (StringUtils.isNotBlank(request.getStatus())) {
            status = request.getStatus();
        }
        if (NumberUtils.isNotBlank(request.getPage())) {
            page = request.getPage();
        }
        if (NumberUtils.isNotBlank(request.getLimit())) {
            limit = request.getLimit();
        }
        if (StringUtils.isNotBlank(request.getOrderRule())) {
            //顺序
            String asc = "ASC";
            if (asc.equals(request.getOrderRule())) {
                orderRule = asc;
            }
        }
        LocaleApplyRequest localeApplyRequest = new LocaleApplyRequest();
        localeApplyRequest.setStatus(status);
        localeApplyRequest.setPage(page);
        localeApplyRequest.setLimit(limit);
        localeApplyRequest.setOrderRule(orderRule);

        PageList<LocaleApplyBO> localeApplyBOPageList = localeApplyManager.findByStatus(localeApplyRequest);
        List<LocaleApplyBO> localeApplyBOList = localeApplyBOPageList.getContent();
        for (LocaleApplyBO localeApplyBO : localeApplyBOList) {
            localeApplyBO.setLocaleName(localeManager.findLocaleName(localeApplyBO.getLocaleCode()).getLocaleName());
            localeApplyBO.setUserName(userBasicService.getByUserId(localeApplyBO.getUserId()).getUserInfo().getRealName());
        }
        localeApplyBOPageList.setContent(localeApplyBOList);

        return localeApplyBOPageList;
    }

    /**
     * 查询场地申请
     * 团委 查询
     *
     * @param request
     * @param context
     * @return
     */
    @Override
    @VerifyPerm(permType = LocalePermType.APPLY_CHECK)
    @Transactional(rollbackFor = Exception.class)
    public PageList<LocaleApplyBO> findAllByStatusSecond(LocaleApplyManagerRequest request, OperateContext context) {
        LocaleApplyStatusEnum localeApplyStatusEnum = LocaleApplyStatusEnum.getByCode(request.getStatus());
        AssertUtil.assertNotNull(localeApplyStatusEnum, "申请场地状态不存在");

        //默认值 状态不限 第0页 每页十条 逆序
        String status = "";
        Integer page = 0;
        Integer limit = 10;
        String orderRule = "DESC";

        if (StringUtils.isNotBlank(request.getStatus())) {
            status = request.getStatus();
        }
        if (NumberUtils.isNotBlank(request.getPage())) {
            page = request.getPage();
        }
        if (NumberUtils.isNotBlank(request.getLimit())) {
            limit = request.getLimit();
        }
        if (StringUtils.isNotBlank(request.getOrderRule())) {
            //顺序
            String asc = "ASC";
            if (asc.equals(request.getOrderRule())) {
                orderRule = asc;
            }
        }
        LocaleApplyRequest localeApplyRequest = new LocaleApplyRequest();
        localeApplyRequest.setStatus(status);
        localeApplyRequest.setPage(page);
        localeApplyRequest.setLimit(limit);
        localeApplyRequest.setOrderRule(orderRule);

        PageList<LocaleApplyBO> localeApplyBOPageList = localeApplyManager.findByStatus(localeApplyRequest);
        List<LocaleApplyBO> localeApplyBOList = localeApplyBOPageList.getContent();
        for (LocaleApplyBO localeApplyBO : localeApplyBOList) {
            localeApplyBO.setLocaleName(localeManager.findLocaleName(localeApplyBO.getLocaleCode()).getLocaleName());
            localeApplyBO.setUserName(userBasicService.getByUserId(localeApplyBO.getUserId()).getUserInfo().getRealName());
        }
        localeApplyBOPageList.setContent(localeApplyBOList);

        return localeApplyBOPageList;
    }

    /**
     * 查询场地申请
     * 申请人 查询
     *
     * @param request
     * @param context
     * @return
     */
    @Override
    @VerifyPerm(permType = LocalePermType.LOCALE_APPLY)
    @Transactional(rollbackFor = Exception.class)
    public PageList<LocaleApplyBO> findAllByUserId(LocaleApplyManagerRequest request, OperateContext context) {

        //默认值 用户id不限 第0页 每页十条 逆序
        String userId = "";
        Integer page = 0;
        Integer limit = 10;
        String orderRule = "DESC";

        if (StringUtils.isNotBlank(request.getUserId())) {
            userId = request.getUserId();
        }
        if (NumberUtils.isNotBlank(request.getPage())) {
            page = request.getPage();
        }
        if (NumberUtils.isNotBlank(request.getLimit())) {
            limit = request.getLimit();
        }
        if (StringUtils.isNotBlank(request.getOrderRule())) {
            //顺序
            String asc = "ASC";
            if (asc.equals(request.getOrderRule())) {
                orderRule = asc;
            }
        }
        LocaleApplyRequest localeApplyRequest = new LocaleApplyRequest();
        localeApplyRequest.setUserId(userId);
        localeApplyRequest.setPage(page);
        localeApplyRequest.setLimit(limit);
        localeApplyRequest.setOrderRule(orderRule);

        PageList<LocaleApplyBO> localeApplyBOPageList = localeApplyManager.findByUserId(localeApplyRequest);
        List<LocaleApplyBO> localeApplyBOList = localeApplyBOPageList.getContent();
        for (LocaleApplyBO localeApplyBO : localeApplyBOList) {
            localeApplyBO.setLocaleName(localeManager.findLocaleName(localeApplyBO.getLocaleCode()).getLocaleName());
            localeApplyBO.setUserName(userBasicService.getByUserId(localeApplyBO.getUserId()).getUserInfo().getRealName());
        }
        localeApplyBOPageList.setContent(localeApplyBOList);

        return localeApplyBOPageList;
    }

    /**
     * 结束可以结束的场地申请
     * 申请超过两天还在COMMIT状态 退回
     * @return
     */
    @Override
    public List<LocaleApplyBO> systemFinishLocaleApply() {
        List<LocaleApplyBO> localeApplyBOList = localeApplyDORepoService.queryLocaleApplyByStatus(LocaleApplyStatusEnum.COMMIT.getCode());
        List<LocaleApplyBO> systemFinishLocaleApplies = new ArrayList<>();
        for (LocaleApplyBO localeApplyBO : localeApplyBOList) {
            if (localeApplyBO.canFinish()) {
                localeApplyBO.setStatus(LocaleApplyStatusEnum.CANCEL.getCode());
                localeApplyBO.putExtInfo(SYSTEM_FINISH_SIGN, SYSTEM_FINISH_SIGN);
                systemFinishLocaleApplies.add(localeApplyDORepoService.updateLocaleApply(localeApplyBO));
            }
        }
        return systemFinishLocaleApplies;
    }

    private LocaleApplyBO updateStatus(LocaleApplyManagerRequest request){
        LocaleApplyStatusEnum localeApplyStatusEnum = LocaleApplyStatusEnum.getByCode(request.getStatus());
        AssertUtil.assertNotNull(localeApplyStatusEnum, "申请场地状态不存在");
        if(localeApplyStatusEnum.getCode().equals(LocaleAreaStatusEnum.CANCEL.getCode())){
            localeAreaManager.cancel(request.getLocaleAreaId());
        }else{
            request.setFailureMessage(null);
        }
        return localeApplyManager.update(request);
    }
}
