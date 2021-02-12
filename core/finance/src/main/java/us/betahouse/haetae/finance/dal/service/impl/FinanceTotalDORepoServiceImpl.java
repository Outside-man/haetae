/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.finance.dal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.finance.dal.model.FinanceTotalDO;
import us.betahouse.haetae.finance.dal.repo.FinanceTotalDORepo;
import us.betahouse.haetae.finance.dal.service.FinanceTotalDORepoService;
import us.betahouse.haetae.finance.idfactory.BizIdFactory;
import us.betahouse.haetae.finance.model.basic.FinanceTotalBO;
import us.betahouse.util.utils.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author MessiahJK
 * @version : FinanceTotalDORepoServiceImpl.java 2019/02/20 23:59 MessiahJK
 */
@Service
public class FinanceTotalDORepoServiceImpl implements FinanceTotalDORepoService {

    @Autowired
    FinanceTotalDORepo financeTotalDORepo;
    @Autowired
    BizIdFactory financeBizFactory;

    @Override
    public FinanceTotalBO createFinanceTotal(FinanceTotalBO financeTotalBO) {
        financeTotalBO.setFinanceTotalId(financeBizFactory.getFinanceTotalId());
        return convert(financeTotalDORepo.save(convert(financeTotalBO)));
    }


    @Override
    public FinanceTotalBO findByFinanceTotalId(String financeTotalId) {
        return convert(financeTotalDORepo.findByFinanceTotalId(financeTotalId));
    }

    @Override
    public FinanceTotalBO findByOrganizationId(String organizationId) {
        return convert(financeTotalDORepo.findByOrganizationId(organizationId));
    }

    @Override
    public FinanceTotalBO update(FinanceTotalBO financeTotalBO) {
        if(financeTotalBO==null){
            return null;
        }
        return convert(financeTotalDORepo.save(convert(financeTotalBO)));
    }

    @Override
    public List<FinanceTotalBO> findAll() {
        return CollectionUtils.toStream(financeTotalDORepo.findAll())
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }


    private FinanceTotalDO  convert(FinanceTotalBO financeTotalBO){
        if(financeTotalBO==null){
            return null;
        }
        FinanceTotalDO financeTotalDO= new FinanceTotalDO();
        financeTotalDO.setFinanceTotalId(financeTotalBO.getFinanceTotalId());
        financeTotalDO.setOrganizationId(financeTotalBO.getOrganizationId());
        financeTotalDO.setOrganizationName(financeTotalBO.getOrganizationName());
        financeTotalDO.setTotalMoney(financeTotalBO.getTotalMoney());
        financeTotalDO.setTotalMoneyIncludeBudget(financeTotalBO.getTotalMoneyIncludeBudget());
        financeTotalDO.setRemark(financeTotalBO.getRemark());
        financeTotalDO.setId(financeTotalBO.getId());
        financeTotalDO.setGmtCreate(financeTotalBO.getGmtCreate());
        financeTotalDO.setGmtModified(financeTotalBO.getGmtModified());
        return financeTotalDO;
    }

    private FinanceTotalBO convert(FinanceTotalDO financeTotalDO){
        if(financeTotalDO==null){
            return null;
        }
        FinanceTotalBO financeTotalBO= new FinanceTotalBO();
        financeTotalBO.setId(financeTotalDO.getId());
        financeTotalBO.setFinanceTotalId(financeTotalDO.getFinanceTotalId());
        financeTotalBO.setOrganizationId(financeTotalDO.getOrganizationId());
        financeTotalBO.setOrganizationName(financeTotalDO.getOrganizationName());
        financeTotalBO.setTotalMoney(financeTotalDO.getTotalMoney());
        financeTotalBO.setTotalMoneyIncludeBudget(financeTotalDO.getTotalMoneyIncludeBudget());
        financeTotalBO.setRemark(financeTotalDO.getRemark());
        financeTotalBO.setGmtCreate(financeTotalDO.getGmtCreate());
        financeTotalBO.setGmtModified(financeTotalDO.getGmtModified());
        return financeTotalBO;
    }
}
