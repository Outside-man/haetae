package us.betahouse.haetae.controller.activity;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import us.betahouse.haetae.activity.model.basic.ActivityEntryBO;
import us.betahouse.haetae.activity.request.ActivityEntryRequest;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.model.activity.request.ActivityEntryRestRequest;
import us.betahouse.haetae.serviceimpl.activity.builder.ActivityEntryRequestBuilder;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityEntryList;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityEntryService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.DateUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

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

    @CheckLogin
    @GetMapping
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<ActivityEntryList> queryActivityEntry(ActivityEntryRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取活动报名信息列表", request, new RestOperateCallBack<ActivityEntryList>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertNotNull(request.getPage(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "page参数不能为空");
                AssertUtil.assertNotNull(request.getLimit(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "limit参数不能为空");
            }

            @Override
            public Result<ActivityEntryList> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                if (request.getActivityId()!="" && request.getActivityId()!= null)
                    return RestResultUtil.buildSuccessResult(activityEntryService.activityEntryQueryByActivityId(request.getActivityId()), "获取创建报名信息成功");;
                ActivityEntryRequest activityEntryRequest = ActivityEntryRequestBuilder.anActivityEntryRequest()
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
    @CheckLogin
    @PostMapping
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<ActivityEntryBO> creatActivityEntry(ActivityEntryRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "新增活动对应报名信息", request, new RestOperateCallBack<ActivityEntryBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertStringNotBlank(request.getActivityId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "报名id不能为空");
                AssertUtil.assertStringNotBlank(request.getTitle(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "报名标题不能为空");
                AssertUtil.assertTrue(request.getNumber()>0,  "报名人数不符合要求");
                AssertUtil.assertStringNotBlank(request.getLinkman(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "联系人不能为空");
                AssertUtil.assertStringNotBlank(request.getContact(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "手机号不能为空");
                AssertUtil.assertTrue(request.getContact().matches("^(13[0-9]|14[5-9]|15[0-3,5-9]|16[2,5,6,7]|17[0-8]|18[0-9]|19[1,3,5,8,9])\\d{8}$"),"手机号码格式错误");
                AssertUtil.assertNotNull(request.getStart(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "报名开始时间不能为空");
                AssertUtil.assertNotNull(request.getEnd(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "报名结束时间不能为空");
                boolean validateTime = DateUtil.parse(request.getStart(),"yyyy-MM-dd hh:mm:ss").before(DateUtil.parse(request.getEnd(),"yyyy-MM-dd hh:mm:ss"));
                AssertUtil.assertTrue(validateTime, "报名开始时间设置必须早于结束时间");
                AssertUtil.assertStringNotBlank(request.getNote(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "报名说明不能为空");
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
                        .withStart(DateUtil.parse(request.getStart(),"yyyy-MM-dd hh:mm:ss").getTime())
                        .withEnd(DateUtil.parse(request.getEnd(),"yyyy-MM-dd hh:mm:ss").getTime())
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
     * @param response
     * @return
     */
    @CheckLogin
    @GetMapping(value = "activityEntryRecordFile",produces = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void getActivityEntryRecordFileByActivityEntryId(ActivityEntryRestRequest request, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {

        AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
            AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
        AssertUtil.assertStringNotBlank(request.getActivityEntryId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "报名信息id不能为空");

        OperateContext context = new OperateContext();
        context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
        List<UserInfoBO> UserInfoBOLists=  activityEntryService.getActivityEntryRecordUserInfoFileByActivityEntryId(request.getActivityEntryId());
        String title = activityEntryService.getActivityEntryTitle(request.getActivityEntryId());
        ExcelWriter writer = ExcelUtil.getWriter(true);

        writer.addHeaderAlias("userInfoId", "用户信息id");
        writer.addHeaderAlias("userId", "用户id");
        writer.addHeaderAlias("stuId", "学号");
        writer.addHeaderAlias("realName", "姓名");
        writer.addHeaderAlias("sex", "性别");
        writer.addHeaderAlias("major", "专业");
        writer.addHeaderAlias("classId", "班级");
        writer.addHeaderAlias("grade", "年级");
        writer.addHeaderAlias("enrollDate", "入学时间");
        writer.addHeaderAlias("extInfo", "其他信息");
        writer.merge(9, title+"--报名信息");
        writer.write(UserInfoBOLists, true);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(title,"UTF-8") +".xls");
        ServletOutputStream out=response.getOutputStream();

        writer.flush(out, true);
        writer.close();
        IoUtil.close(out);
    }


    /**
     * 更新报名信息
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PutMapping
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<ActivityEntryBO> update(ActivityEntryRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "更新报名", request, new RestOperateCallBack<ActivityEntryBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getActivityEntryId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "报名信息id不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
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
                        .withStart(DateUtil.parse(request.getStart(),"yyyy-MM-dd hh:mm:ss").getTime())
                        .withEnd(DateUtil.parse(request.getEnd(),"yyyy-MM-dd hh:mm:ss").getTime())
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
