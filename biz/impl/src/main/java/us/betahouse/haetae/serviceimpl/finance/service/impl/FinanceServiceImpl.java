/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.finance.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.finance.enums.FinanceMessageTypeEnum;
import us.betahouse.haetae.finance.manager.FinanceManager;
import us.betahouse.haetae.finance.model.basic.FinanceMessageBO;
import us.betahouse.haetae.finance.model.basic.FinanceTotalBO;
import us.betahouse.haetae.finance.model.common.PageList;
import us.betahouse.haetae.finance.request.FinanceRequest;
import us.betahouse.haetae.finance.request.builder.FinanceRequestBuilder;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.finance.request.FinanceManagerRequest;
import us.betahouse.haetae.serviceimpl.finance.service.FinanceService;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.NumberUtils;

/**
 * @author MessiahJK
 * @version : FinanceServiceImpl.java 2019/02/22 0:30 MessiahJK
 */
public class FinanceServiceImpl implements FinanceService {

    @Autowired
    FinanceManager financeManager;

    @Override
    public PageList<FinanceMessageBO> findMessage(FinanceManagerRequest request, OperateContext context) {
        //默认值 学期不限 状态不限 类型不限 第0页 每页十条
        String term="";
        String status="";
        Integer page=0;
        Integer limit=10;
        if(StringUtils.isNotBlank(request.getTerm())){
            term=request.getTerm();
        }
        if(StringUtils.isNotBlank(request.getStatus())){
            FinanceMessageTypeEnum state = FinanceMessageTypeEnum.getByCode(request.getStatus());
            AssertUtil.assertNotNull(state, "活动状态不存在");
            status=request.getStatus();
        }
        if(NumberUtils.isNotBlank(request.getPage())){
            page=request.getPage();
        }
        if(NumberUtils.isNotBlank(request.getLimit())){
            limit=request.getLimit();
        }
        FinanceRequest financeRequest=FinanceRequestBuilder.aFinanceRequest()
                .withOrganizationId(request.getOrganizationId())
                .withTerm(term)
                .withStatus(status)
                .withPage(page)
                .withLimit(limit)
                .build();
        return financeManager.findMessage(financeRequest);
    }

    @Override
    public FinanceMessageBO submitBudget(FinanceManagerRequest request, OperateContext context) {
        return null;
    }

    @Override
    public FinanceMessageBO audite(FinanceManagerRequest request, OperateContext context) {
        return null;
    }

    @Override
    public FinanceMessageBO checkMessage(FinanceManagerRequest request, OperateContext context) {
        return null;
    }

    @Override
    public FinanceMessageBO tally(FinanceManagerRequest request, OperateContext context) {
        return null;
    }

    @Override
    public FinanceTotalBO total(FinanceManagerRequest request, OperateContext context) {
        return financeManager.getTotalMoney(
                FinanceRequestBuilder.aFinanceRequest()
                        .withOrganizationId(request.getOrganizationId())
                        .build());
    }
}
