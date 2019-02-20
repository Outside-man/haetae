/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.controller.finance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.model.finance.request.FinanceRestRequest;
import us.betahouse.haetae.model.finance.vo.FinanceVO;
import us.betahouse.util.common.Result;
import us.betahouse.util.log.Log;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 财务接口
 *
 * @author MessiahJK
 * @version : FinanceController.java 2019/02/21 1:15 MessiahJK
 */
@RestController
@RequestMapping(value = "/finance")
public class FinanceController {
    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(FinanceController.class);

    /**
     * 获取财务信息列表
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @GetMapping("/message")
    @Log(loggerName = LoggerName.FINANCE_DIGEST)
    public Result<List<FinanceVO>> getMessage(FinanceRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "操作活动", request, new RestOperateCallBack<List<FinanceVO>>(){

            @Override
            public void before() {

            }

            @Override
            public Result<List<FinanceVO>> execute() {
                return null;
            }
        });
    }

    /**
     * 提交预算
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PostMapping("/budget")
    @Log(loggerName =LoggerName.FINANCE_DIGEST)
    public Result<FinanceVO> submitBudget(FinanceRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "操作活动", request, new RestOperateCallBack<FinanceVO>(){

            @Override
            public void before() {

            }

            @Override
            public Result<FinanceVO> execute() {
                return null;
            }
        });
    }

    /**
     * 审核
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PutMapping("/audite")
    @Log(loggerName =LoggerName.FINANCE_DIGEST)
    public Result<FinanceVO> audite(FinanceRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "操作活动", request, new RestOperateCallBack<FinanceVO>(){

            @Override
            public void before() {

            }

            @Override
            public Result<FinanceVO> execute() {
                return null;
            }
        });
    }

    /**
     * 核算
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PutMapping("/check")
    @Log(loggerName =LoggerName.FINANCE_DIGEST)
    public Result<FinanceVO> check(FinanceRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "操作活动", request, new RestOperateCallBack<FinanceVO>(){

            @Override
            public void before() {

            }

            @Override
            public Result<FinanceVO> execute() {
                return null;
            }
        });
    }

    /**
     * 记账
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PutMapping("/tally")
    @Log(loggerName =LoggerName.FINANCE_DIGEST)
    public Result<FinanceVO> tally(FinanceRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "操作活动", request, new RestOperateCallBack<FinanceVO>(){

            @Override
            public void before() {

            }

            @Override
            public Result<FinanceVO> execute() {
                return null;
            }
        });
    }
}
