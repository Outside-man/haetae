/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.controller.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.betahouse.haetae.activity.model.basic.PositionRecordBO;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.model.activity.request.PositionRecordRestRequest;
import us.betahouse.haetae.serviceimpl.activity.request.PositionRecordManagerRequest;
import us.betahouse.haetae.serviceimpl.activity.request.builder.PositionRecordManagerRequestBuilder;
import us.betahouse.haetae.serviceimpl.activity.service.PositionRecordService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.AssertUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 履历接口
 *
 * @author MessiahJK
 * @version : PositionRecordController.java 2018/11/25 15:25 MessiahJK
 */
@CrossOrigin
@RestController
@RequestMapping("/positionRecord")
public class PositionRecordController {
    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(PositionRecordController.class);

    @Autowired
    private PositionRecordService positionRecordService;


    /**
     * 获取个人履历
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @Log(loggerName = LoggerName.WEB_DIGEST)
    @GetMapping(value = "/userId")
    public Result<List<PositionRecordBO>> getByUserId(PositionRecordRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取个人履历", request, new RestOperateCallBack<List<PositionRecordBO>>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
            }

            @Override
            public Result<List<PositionRecordBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                PositionRecordManagerRequest positionRecordManagerRequest = PositionRecordManagerRequestBuilder.getInstance()
                        .withPositionRecordId(request.getPositionRecordId())
                        .withUserId(request.getUserId())
                        .build();

                List<PositionRecordBO> positionRecordBOList = positionRecordService.findByUserId(positionRecordManagerRequest, context);
                return RestResultUtil.buildSuccessResult(positionRecordBOList, "获取个人履历成功");
            }
        });
    }

    /**
     * 获取组织人员列表
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @Log(loggerName = LoggerName.WEB_DIGEST)
    @GetMapping(value = "/organizationId")
    public Result<List<PositionRecordBO>> getByOrganizationId(PositionRecordRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取组织人员列表", request, new RestOperateCallBack<List<PositionRecordBO>>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
            }

            @Override
            public Result<List<PositionRecordBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                PositionRecordManagerRequest positionRecordManagerRequest = PositionRecordManagerRequestBuilder.getInstance()
                        .withPositionRecordId(request.getPositionRecordId())
                        .withOrganizationId(request.getOrganizationId())
                        .build();
                List<PositionRecordBO> positionRecordBOList = positionRecordService.findByOrganizationId(positionRecordManagerRequest, context);
                return RestResultUtil.buildSuccessResult(positionRecordBOList, "获取组织人员列表成功");
            }
        });
    }
}
