/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.finance.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.finance.enums.FinanceMessageTypeEnum;
import us.betahouse.haetae.finance.manager.FinanceManager;
import us.betahouse.haetae.finance.model.basic.FinanceMessageBO;
import us.betahouse.haetae.finance.model.basic.FinanceTotalBO;
import us.betahouse.haetae.finance.model.basic.builder.FinanceMessageBOBuilder;
import us.betahouse.haetae.finance.model.common.PageList;
import us.betahouse.haetae.finance.request.FinanceRequest;
import us.betahouse.haetae.finance.request.builder.FinanceRequestBuilder;
import us.betahouse.haetae.organization.dal.service.OrganizationRepoService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.common.utils.TermUtil;
import us.betahouse.haetae.serviceimpl.finance.constant.FinancePermExInfoKey;
import us.betahouse.haetae.serviceimpl.finance.constant.FinancePermType;
import us.betahouse.haetae.serviceimpl.finance.request.FinanceManagerRequest;
import us.betahouse.haetae.serviceimpl.finance.service.FinanceService;
import us.betahouse.haetae.user.model.basic.perm.PermBO;
import us.betahouse.haetae.user.user.service.UserBasicService;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.NumberUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author MessiahJK
 * @version : FinanceServiceImpl.java 2019/02/22 0:30 MessiahJK
 */
@Service
public class FinanceServiceImpl implements FinanceService {

    @Autowired
    private FinanceManager financeManager;
    @Autowired
    private UserBasicService userBasicService;

    @Autowired
    private OrganizationRepoService organizationRepoService;

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
    public List<FinanceMessageBO> findMyMessage(FinanceManagerRequest request, OperateContext context) {
        FinanceRequest financeRequest=FinanceRequestBuilder.aFinanceRequest()
                .withOrganizationId(request.getOrganizationId())
                .withApplicantUserId(request.getUserId())
                .withTerm(request.getTerm())
                .build();
        return financeManager.findMyMessage(financeRequest);
    }


    @Override
    public FinanceMessageBO submitBudget(FinanceManagerRequest request, OperateContext context) {
        FinanceMessageBO financeMessageBO=FinanceMessageBOBuilder
                .aFinanceMessageBO()
                .withApplicantUserId(request.getUserId())
                .withApplicantName(userBasicService.getByUserId(request.getUserId()).getUserInfo().getRealName())
                .withOrganizationId(request.getOrganizationId())
                .withOrganizationName(organizationRepoService.queryByOrganizationId(request.getOrganizationId()).getOrganizationName())
                .withFinanceName(request.getFinanceName())
                .withFinanceInfo(request.getFinanceInfo())
                .withBudget(request.getBudget())
                .withTerm(TermUtil.getNowTerm())
                .build();
        return financeManager.createFinanceMessageByBudget(financeMessageBO);
    }

    @Override
    public FinanceMessageBO changeBudget(FinanceManagerRequest request, OperateContext context) {
        FinanceMessageBO financeMessageBO=financeManager.findMessageByFinanceMessageId(request.getFinanceMessageId());
        AssertUtil.assertNotNull(financeMessageBO, "无法获取财务信息实体");
        AssertUtil.assertEquals(financeMessageBO.getStatus(),FinanceMessageTypeEnum.UNAUDITED.getCode(),"状态信息错误" );
        if(StringUtils.isNotBlank(request.getFinanceName())){
            financeMessageBO.setFinanceName(request.getFinanceName());
        }
        if(StringUtils.isNotBlank(request.getFinanceInfo())){
            financeMessageBO.setFinanceInfo(request.getFinanceInfo());
        }
        if(StringUtils.isNotBlank(request.getRemark())){
            financeMessageBO.setRemark(request.getRemark());
        }
        if(request.getBudget()!=null&&request.getBudget().compareTo(BigDecimal.ZERO)>0){
            financeMessageBO.setBudget(request.getBudget());
        }
        return financeManager.changeFinanceMessage(financeMessageBO);
    }

    @Override
    public FinanceMessageBO audite(FinanceManagerRequest request, OperateContext context) {
        if(request.getAudite()){
            return financeManager.changeStatus(request.getFinanceMessageId(), FinanceMessageTypeEnum.AUDITED.getCode());
        }else{
            return financeManager.changeStatus(request.getFinanceMessageId(), FinanceMessageTypeEnum.AUDITED_FAIL.getCode());
        }
    }

    @Override
    public FinanceMessageBO checkMessage(FinanceManagerRequest request, OperateContext context) {
        FinanceMessageBO financeMessageBO=financeManager.findMessageByFinanceMessageId(request.getFinanceMessageId());
        financeMessageBO.setAuditorUserId(request.getUserId());
        financeMessageBO.setAuditorName(userBasicService.getByUserId(request.getUserId()).getUserInfo().getRealName());
        financeMessageBO.setTrueMoney(request.getTrueMoney());

        return financeManager.check(financeMessageBO);
    }

    @Override
    public FinanceMessageBO tally(FinanceManagerRequest request, OperateContext context) {
        FinanceMessageBO financeMessageBO=FinanceMessageBOBuilder
                .aFinanceMessageBO()
                .withAuditorName(userBasicService.getByUserId(request.getUserId()).getUserInfo().getRealName())
                .withAuditorUserId(request.getUserId())
                .withOrganizationId(request.getOrganizationId())
//                .withOrganizationName(organizationManager.findOrganizationByOrganizationId(request.getOrganizationId()).getOrganizationName())
                .withFinanceName(request.getFinanceName())
                .withFinanceInfo(request.getFinanceInfo())
                .withTrueMoney(request.getTrueMoney())
                .withTerm(TermUtil.getNowTerm())
                .withType(request.getType())
                .build();
        return financeManager.createFinanceMessageByTally(financeMessageBO);
    }

    @Override
    public FinanceTotalBO total(FinanceManagerRequest request, OperateContext context) {
        return financeManager.getTotalMoney(
                FinanceRequestBuilder.aFinanceRequest()
                        .withOrganizationId(request.getOrganizationId())
                        .build());
    }

    @Override
    public void initFinanceMessageTotal(FinanceTotalBO financeTotalBO) {
        financeManager.initTotalMoney(financeTotalBO);
    }

    @Override
    public Boolean checkPerm(FinanceManagerRequest request, OperateContext context) {
        Map<String, PermBO> map = userBasicService.fetchUserPerms(request.getUserId());
        AtomicReference<Boolean> result= new AtomicReference<>(false);
        map.values().forEach(permBO ->{
            if(request.getOrganizationId().equals(permBO.getExtInfo().get(FinancePermExInfoKey.FINANCE_ORGANIZATION_ID))&&permBO.getPermType().equals(FinancePermType.FINANCE_MANAGER)){
                result.set(true);
            }
        });
        return result.get();
    }

    @Override
    public Boolean checkBan(FinanceManagerRequest request, OperateContext context) {
        Map<String, PermBO> map = userBasicService.fetchUserPerms(request.getUserId());
        AtomicReference<Boolean> result= new AtomicReference<>(false);
        map.values().forEach(permBO ->{
            if(permBO.getPermType().equals(FinancePermType.FINANCE_BAN)){
                result.set(true);
            }
        });
        return result.get();
    }

}
