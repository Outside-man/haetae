/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.controller.certificate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import us.betahouse.haetae.certificate.model.basic.CertificateBO;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.model.user.request.ConfirmRequest;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.AssertUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 证书审核员 接口
 *
 * @author guofan.cp
 * @version : CertificateStampController.java 2019/04/22 22:16 guofan.cp
 */
@RestController
@RequestMapping(value = "/certificateStamp")
public class CertificateStampController {

    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(CertificateController.class);
    @GetMapping
    @Log(loggerName = LoggerName.WEB_DIGEST)
    @CheckLogin
    public Result<List<CertificateBO>> findUnrecivedCertificatesByUserId(ConfirmRequest request, HttpServletRequest httpServletRequest){
        return RestOperateTemplate.operate(LOGGER, "获取该生未审核记录", request, new RestOperateCallBack<List<CertificateBO>>() {
            @Override
            public void before(){
                AssertUtil.assertNotNull(request.getStuId(),"待审核学生id不能为空");
            }
            @Override
            public Result<List<CertificateBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));

                return null;
            }
        });
    }
    @PutMapping
    @Log(loggerName = LoggerName.WEB_DIGEST)
    @CheckLogin
    public Result<CertificateBO> confirmCertificate(ConfirmRequest request, HttpServletRequest httpServletRequest){
        return RestOperateTemplate.operate(LOGGER, "证书审核通过", request, new RestOperateCallBack<CertificateBO>() {
            @Override
            public void before(){

            }
            @Override
            public Result<CertificateBO> execute() {
                return null;
            }
        });
    }
}
