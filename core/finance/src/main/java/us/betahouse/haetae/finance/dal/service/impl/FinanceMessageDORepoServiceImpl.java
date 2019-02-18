/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.finance.dal.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import us.betahouse.haetae.finance.dal.model.FinanceMessageDO;
import us.betahouse.haetae.finance.dal.repo.FinanceMessageDORepo;
import us.betahouse.haetae.finance.dal.service.FinanceMessageDORepoService;
import us.betahouse.haetae.finance.idfactory.BizIdFactory;
import us.betahouse.haetae.finance.model.basic.FinanceMessageBO;
import us.betahouse.haetae.finance.model.common.PageList;

import java.util.Map;

/**
 * @author MessiahJK
 * @version : FinanceMessageDORepoServiceImpl.java 2019/02/14 15:05 MessiahJK
 */
public class FinanceMessageDORepoServiceImpl implements FinanceMessageDORepoService {

    @Autowired
    FinanceMessageDORepo financeMessageDORepo;
    @Autowired
    BizIdFactory financeBizFactory;

    @Override
    public FinanceMessageBO createFinanceMessage(FinanceMessageBO financeMessageBO) {
        if(financeMessageBO==null||StringUtils.isNotBlank(financeMessageBO.getId().toString())||StringUtils.isNotBlank(financeMessageBO.getFinanceMessageId())){
            return null;
        }
        financeMessageBO.setFinanceMessageId(financeBizFactory.getFinanceMessageId());
        return convert(financeMessageDORepo.save(convert(financeMessageBO)));
    }

    @Override
    public FinanceMessageBO update(FinanceMessageBO financeMessageBO) {
        if(financeMessageBO==null||StringUtils.isBlank(financeMessageBO.getFinanceMessageId())||findByFinanceMessageId(financeMessageBO.getFinanceMessageId())==null){
            return null;
        }
        return convert(financeMessageDORepo.save(convert(financeMessageBO)));
    }

    @Override
    public PageList<FinanceMessageBO> findByOrganizationIdAndTermAndStatus(String organizationId, String term, String status,Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return new PageList<>(financeMessageDORepo.findAllByOrganizationIdContainsAndTermContainsAndStatusContains(pageable, organizationId, term, status), this::convert);
    }

    @Override
    public FinanceMessageBO findByFinanceMessageId(String financeMessageId) {
        if(StringUtils.isBlank(financeMessageId)){
            return null;
        }
        return convert(financeMessageDORepo.findByFinanceMessageId(financeMessageId));
    }


    private Object convert(Object o) {
        if(o instanceof FinanceMessageDO){
            return convert(o);
        }else if(o instanceof FinanceMessageBO){
            return convert(o);
        }
        return null;
    }


    /**
     * BO2DO
     *
     * @param financeMessageBO
     * @return
     */
    public FinanceMessageDO convert(FinanceMessageBO financeMessageBO){
        if(financeMessageBO==null){
            return null;
        }
        FinanceMessageDO financeMessageDO= new FinanceMessageDO();
        financeMessageDO.setId(financeMessageBO.getId());
        financeMessageDO.setFinanceMessageId(financeMessageBO.getFinanceMessageId());
        financeMessageDO.setFinanceName(financeMessageBO.getFinanceName());
        financeMessageDO.setFinanceInfo(financeMessageBO.getFinanceInfo());
        financeMessageDO.setType(financeMessageBO.getType());
        financeMessageDO.setBudget(financeMessageBO.getBudget());
        financeMessageDO.setTrueMoney(financeMessageBO.getTrueMoney());
        financeMessageDO.setStatus(financeMessageBO.getStatus());
        financeMessageDO.setTerm(financeMessageBO.getTerm());
        financeMessageDO.setOrganizationId(financeMessageBO.getOrganizationId());
        financeMessageDO.setOrganizationName(financeMessageBO.getOrganizationName());
        financeMessageDO.setApplicantUserId(financeMessageBO.getApplicantUserId());
        financeMessageDO.setApplicantName(financeMessageBO.getApplicantName());
        financeMessageDO.setAuditorUserId(financeMessageBO.getAuditorUserId());
        financeMessageDO.setAuditorName(financeMessageBO.getAuditorName());
        financeMessageDO.setFinishTime(financeMessageBO.getFinishTime());
        financeMessageDO.setRemark(financeMessageBO.getRemark());
        financeMessageDO.setExinfo(JSON.toJSONString(financeMessageBO.getFinanceInfo()));
        financeMessageDO.setGmtCreate(financeMessageBO.getGmtCreate());
        financeMessageDO.setGmtModified(financeMessageBO.getGmtModified());
        return financeMessageDO;
    }

    /**
     * DO2BO
     *
     * @param financeMessageDO
     * @return
     */
    @SuppressWarnings("unchecked")
    public FinanceMessageBO convert(FinanceMessageDO financeMessageDO){
        if(financeMessageDO==null){
            return null;
        }
        FinanceMessageBO financeMessageBO= new FinanceMessageBO();
        financeMessageBO.setFinanceMessageId(financeMessageDO.getFinanceMessageId());
        financeMessageBO.setFinanceName(financeMessageDO.getFinanceName());
        financeMessageBO.setFinanceInfo(financeMessageDO.getFinanceInfo());
        financeMessageBO.setType(financeMessageDO.getType());
        financeMessageBO.setBudget(financeMessageDO.getBudget());
        financeMessageBO.setTrueMoney(financeMessageDO.getTrueMoney());
        financeMessageBO.setStatus(financeMessageDO.getStatus());
        financeMessageBO.setTerm(financeMessageDO.getTerm());
        financeMessageBO.setOrganizationId(financeMessageDO.getOrganizationId());
        financeMessageBO.setOrganizationName(financeMessageDO.getOrganizationName());
        financeMessageBO.setApplicantUserId(financeMessageDO.getApplicantUserId());
        financeMessageBO.setApplicantName(financeMessageDO.getApplicantName());
        financeMessageBO.setAuditorUserId(financeMessageDO.getAuditorUserId());
        financeMessageBO.setAuditorName(financeMessageDO.getAuditorName());
        financeMessageBO.setFinishTime(financeMessageDO.getFinishTime());
        financeMessageBO.setRemark(financeMessageDO.getRemark());
        financeMessageBO.setExInfo(JSON.parseObject(financeMessageDO.getExinfo(),Map.class));
        financeMessageBO.setGmtCreate(financeMessageDO.getGmtCreate());
        financeMessageBO.setGmtModified(financeMessageDO.getGmtModified());
        return financeMessageBO;
    }
}
