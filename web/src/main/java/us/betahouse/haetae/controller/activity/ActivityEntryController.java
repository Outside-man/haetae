package us.betahouse.haetae.controller.activity;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.betahouse.haetae.activity.enums.ActivityEntryStateEnum;
import us.betahouse.haetae.activity.model.basic.ActivityEntryBO;
import us.betahouse.haetae.activity.request.ActivityEntryRequest;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.model.activity.request.ActivityEntryRestRequest;
import us.betahouse.haetae.serviceimpl.activity.builder.ActivityEntryRequestBuilder;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityEntryList;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityEntryQueryRequest;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityEntryService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.ExcelUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 活动报名信息接口
 *
 * @author zjb
 * @version : ActivityEntryController.java 2019/7/8 1:27 zjb
 */
@RestController
@RequestMapping(value = "/activityEntry")
public class ActivityEntryController {
    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private ActivityEntryService activityEntryService;

//    @CheckLogin
    @GetMapping
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<ActivityEntryList> queryActivityEntry(ActivityEntryRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取活动报名信息列表", request, new RestOperateCallBack<ActivityEntryList>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
//                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
            }

            @Override
            public Result<ActivityEntryList> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                if (request.getActivityId()!="" && request.getActivityId()!= null)
                    return RestResultUtil.buildSuccessResult(activityEntryService.activityEntryQueryByActivityId(request.getActivityId()), "获取创建报名信息成功");;
                ActivityEntryRequest activityEntryRequest = ActivityEntryRequestBuilder.anActivityEntryRequest()
//                        .withActivityId(request.getActivityId())
                        .withState(request.getState())
                        .withTerm(request.getTerm())
                        .withType(request.getActivityType())
                        .withPage(request.getPage())
                        .withLimit(request.getLimit())
                        .build();
                    return RestResultUtil.buildSuccessResult(activityEntryService.activityEntryQuery(activityEntryRequest, request.getUserId()), "获取报名信息列表成功");
            }
        });
    }



    /**
     * 创建报名
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
//    @CheckLogin
    @PostMapping
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<ActivityEntryBO> add(ActivityEntryRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "新增活动", request, new RestOperateCallBack<ActivityEntryBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
//                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertStringNotBlank(request.getActivityId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "活动id不能为空");
                AssertUtil.assertStringNotBlank(request.getTitle(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "报名标题不能为空");
                AssertUtil.assertTrue(request.getNumber()<=0,  "报名人数不符合要求");
                AssertUtil.assertNotNull(request.getStart(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "活动开始时间不能为空");
                AssertUtil.assertNotNull(request.getEnd(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "活动结束时间不能为空");
                boolean validateTime = new Date(request.getStart()).before(new Date(request.getEnd()));
                AssertUtil.assertTrue(validateTime, "活动开始时间必须早于结束时间");
            }

            @Override
            public Result<ActivityEntryBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                ActivityEntryRequest activityEntryRequest = ActivityEntryRequestBuilder.anActivityEntryRequest()
                        .withActivityId(request.getActivityId())
                        .withTitle(request.getTitle())
                        .withNumber(request.getNumber())
                        .withLinkman(request.getLinkman())
                        .withContact(request.getContact())
                        .withStart(request.getStart())
                        .withEnd(request.getEnd())
                        .withChoose(request.getChoose())
                        .withTop(request.getTop())
                        .withNote(request.getNote())
                        .build();
                ActivityEntryBO activityEntryBO = activityEntryService.createActivityEntry(activityEntryRequest);
                return RestResultUtil.buildSuccessResult(activityEntryBO, "创建报名成功");
            }
        });
    }

    /**
     * 导出报名记录
     * @param request
     * @param httpServletRequest
     * @return
     */
//    @CheckLogin
    @GetMapping(value = "activityEntryRecordFile",produces = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    @Log(loggerName = LoggerName.WEB_DIGEST)
    public ResponseEntity<byte[]> getActivityEntryRecordFileByActivityEntryId(ActivityEntryRestRequest request, HttpServletRequest httpServletRequest) {

            AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
//            AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
            AssertUtil.assertStringNotBlank(request.getActivityEntryId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "报名信息id不能为空");

            OperateContext context = new OperateContext();
            context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));

            HSSFWorkbook excel = ExcelUtil.createExcel(activityEntryService.getActivityEntryRecordUserInfoFileByActivityEntryId(request.getActivityEntryId()));
            HttpHeaders headers = new HttpHeaders();

            try {
                headers.setContentDispositionFormData("attachment", new String( (activityEntryService.getActivityEntryTitle(request.getActivityEntryId())+ ".xls").getBytes("UTF-8"), "ISO8859-1"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<byte[]>(excel.getBytes(), headers, HttpStatus.OK);
    }


    /**
     * 更新报名信息
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
//    @CheckLogin
    @PutMapping
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<ActivityEntryBO> update(ActivityEntryRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "操作活动", request, new RestOperateCallBack<ActivityEntryBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getActivityEntryId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "报名信息id不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
//                AssertUtil.assertStringNotBlank(request.getOperation(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "操作不能为空");
            }

            @Override
            public Result<ActivityEntryBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                ActivityEntryRequest activityEntryRequest = ActivityEntryRequestBuilder.anActivityEntryRequest()
                        .withActivityEntryId(request.getActivityEntryId())
                        .withState(request.getState())
                        .withTitle(request.getTitle())
                        .withNumber(request.getNumber())
                        .withLinkman(request.getLinkman())
                        .withContact(request.getContact())
                        .withStart(request.getStart())
                        .withEnd(request.getEnd())
                        .withChoose(request.getChoose())
                        .withTop(request.getTop())
                        .withNote(request.getNote())
                        .build();
                ActivityEntryBO activityEntryBO = activityEntryService.updateActivityEntry(activityEntryRequest);
                return RestResultUtil.buildSuccessResult(activityEntryBO, "报名信息更新成功");
            }
        });
    }

}
