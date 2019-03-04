/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.finance.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import java.util.List;

/**
 * @author MessiahJK
 * @version : FinanceManagerImpl.java 2019/02/22 0:42 MessiahJK
 */
@Service
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
    public List<FinanceMessageBO> findMyMessage(FinanceRequest request) {
        return financeMessageDORepoService.findAllByOrganizationIdAndTermAndApplicantUserId(request.getOrganizationId(),request.getTerm(),request.getApplicantUserId());
    }

    @Override
    public FinanceMessageBO findMessageByFinanceMessageId(String financeMessageId) {
        return financeMessageDORepoService.findByFinanceMessageId(financeMessageId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FinanceMessageBO changeStatus(String financeMessageId, String status) {
        FinanceMessageBO financeMessageBO=financeMessageDORepoService.findByFinanceMessageId(financeMessageId);
        financeMessageBO.setStatus(status);
        if(status.equals(FinanceMessageTypeEnum.AUDITED.getCode())){
            FinanceTotalBO financeTotalBO=financeTotalDORepoService.findByOrganizationId(financeMessageBO.getOrganizationId());
            financeTotalBO.setTotalMoneyIncludeBudget(financeTotalBO.getTotalMoneyIncludeBudget().subtract(financeMessageBO.getBudget()));
            financeTotalDORepoService.update(financeTotalBO);
            System.out.println(financeMessageBO);
            return financeMessageDORepoService.update(financeMessageBO);
        }else if(status.equals(FinanceMessageTypeEnum.AUDITED_FAIL.getCode())){
            return financeMessageDORepoService.update(financeMessageBO);
        }
        return null;
    }

    @Override
    public FinanceMessageBO check(FinanceMessageBO financeMessageBO) {
        financeMessageBO.setStatus(FinanceMessageTypeEnum.CHECKED.getCode());
        financeMessageBO.setFinishTime(new Date());

        FinanceTotalBO financeTotalBO=financeTotalDORepoService.findByOrganizationId(financeMessageBO.getOrganizationId());
        System.out.println(financeTotalBO);
        System.out.println(financeMessageBO);
        financeTotalBO.setTotalMoneyIncludeBudget(financeTotalBO.getTotalMoneyIncludeBudget().add(financeMessageBO.getBudget()).subtract(financeMessageBO.getTrueMoney()));
        financeTotalBO.setTotalMoney(financeTotalBO.getTotalMoney().subtract(financeMessageBO.getTrueMoney()));
        financeTotalDORepoService.update(financeTotalBO);

        return financeMessageDORepoService.update(financeMessageBO);
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
    public FinanceMessageBO createFinanceMessageByTally(FinanceMessageBO financeMessageBO) {
        financeMessageBO.setStatus(FinanceMessageTypeEnum.CHECKED.getCode());
        financeMessageBO.setFinishTime(new Date());
        FinanceTotalBO financeTotalBO=financeTotalDORepoService.findByOrganizationId(financeMessageBO.getOrganizationId());
        if(financeMessageBO.getType().equals(MoneyRecordTypeEnum.negative.getCode())){
            financeTotalBO.setTotalMoney(financeTotalBO.getTotalMoney().subtract(financeMessageBO.getTrueMoney()));
            financeTotalBO.setTotalMoneyIncludeBudget(financeTotalBO.getTotalMoney().subtract(financeMessageBO.getTrueMoney()));
        }else if(financeMessageBO.getType().equals(MoneyRecordTypeEnum.positive.getCode())){
            financeTotalBO.setTotalMoney(financeTotalBO.getTotalMoney().add(financeMessageBO.getTrueMoney()));
            financeTotalBO.setTotalMoneyIncludeBudget(financeTotalBO.getTotalMoney().add(financeMessageBO.getTrueMoney()));
        }
        financeTotalDORepoService.update(financeTotalBO);
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
