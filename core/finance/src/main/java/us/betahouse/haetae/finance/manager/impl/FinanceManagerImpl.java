/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.finance.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.finance.dal.service.FinanceMessageDORepoService;
import us.betahouse.haetae.finance.dal.service.FinanceTotalDORepoService;
import us.betahouse.haetae.finance.enums.FinanceMessageTypeEnum;
import us.betahouse.haetae.finance.enums.MoneyRecordTypeEnum;
import us.betahouse.haetae.finance.manager.FinanceManager;
import us.betahouse.haetae.finance.model.basic.FinanceMessageBO;
import us.betahouse.haetae.finance.model.basic.FinanceTotalBO;
import us.betahouse.haetae.finance.model.common.PageList;
import us.betahouse.haetae.finance.request.FinanceRequest;

import java.util.Date;

/**
 * @author MessiahJK
 * @version : FinanceManagerImpl.java 2019/02/22 0:42 MessiahJK
 */
public class FinanceManagerImpl implements FinanceManager {

    @Autowired
    FinanceMessageDORepoService financeMessageDORepoService;

    @Autowired
    FinanceTotalDORepoService financeTotalDORepoService;

    @Override
    public PageList<FinanceMessageBO> findMessage(FinanceRequest request) {
        return financeMessageDORepoService.findByOrganizationIdAndTermAndStatus(request.getOrganizationId(), request.getTerm(), request.getStatus(), request.getPage(), request.getLimit());
    }

    @Override
    public FinanceTotalBO getTotalMoney(FinanceRequest request) {
        return financeTotalDORepoService.findByOrganizationId(request.getOrganizationId());
    }

    @Override
    public FinanceMessageBO createFinanceMessageByBudget(FinanceMessageBO financeMessageBO) {
        financeMessageBO.setType(MoneyRecordTypeEnum.negative.getCode());
        financeMessageBO.setStatus(FinanceMessageTypeEnum.UNAUDITED.getCode());
        financeMessageBO.setFinishTime(new Date());
        return financeMessageDORepoService.createFinanceMessage(financeMessageBO);
    }

    @Override
    public void initTotalMoney(FinanceTotalBO financeTotalBO) {
        FinanceTotalBO totalBO=financeTotalDORepoService.findByOrganizationId(financeTotalBO.getOrganizationId());
        if(totalBO==null){
            financeTotalDORepoService.createFinanceTotal(financeTotalBO);
        }
    }


}
