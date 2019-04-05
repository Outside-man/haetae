/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.controller.certificate;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.betahouse.haetae.certificate.enums.CertificateTypeEnum;
import us.betahouse.haetae.certificate.model.basic.CertificateBO;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.model.certificate.request.CertificateRestRequest;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.AssertUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 证书接口
 *
 * @author guofan.cp
 * @version : CertificateController.java 2019/04/04 20:30 guofan.cp
 */
@RestController
@RequestMapping(value = "/certificate")
public class CertificateController {

    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(CertificateController.class);

    /**
     * 创建证书
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @PostMapping
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<CertificateBO> certificate(@RequestBody CertificateRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "新增证书记录", request, new RestOperateCallBack<CertificateBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getCertificateName(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "证书名称不能为空");
                AssertUtil.assertStringNotBlank(request.getCertificateType(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "证书类型不能为空");
                AssertUtil.assertNotNull(request.getCertificatePublishTime(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "证书发布时间不能为空");
                AssertUtil.assertNotNull(request.getDescription(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "证书详细信息不能为空");
                boolean validateTime = new Date(request.getCertificatePublishTime()).before(new Date());
                AssertUtil.assertTrue(validateTime, "发布时间必须早于当前时间");
            }

            @Override
            public Result<CertificateBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                return null;
            }
        });
//        System.out.println("certificateId"+request.getCertificateId());
//        System.out.println("wirkUserid"+request.getWorkUserId());
//        System.out.println("teacher"+request.getTeacher());
    }
    /**
     * 修改证书信息
     */
    /**
     * 删除证书
     */
    /**
     * 获取证书详细信息
     */
    /**
     * 获取多条证书记录
     */
    /**
     * 盖章
     */
    /**
     *
     */
}
