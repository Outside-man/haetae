/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.controller.certificate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.betahouse.haetae.certificate.model.basic.CertificateBO;
import us.betahouse.haetae.certificate.request.CertificateManagerRequest;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.model.user.request.ConfirmRequest;
import us.betahouse.haetae.serviceimpl.certificate.builder.CertificateRequestBuilder;
import us.betahouse.haetae.serviceimpl.certificate.service.CertificateManagerService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.log.Log;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后端管理员接口
 *
 * @author Rade
 * @date 2020-02-20
 */
@RestController
@RequestMapping(value = "/certificateManager")
public class CertificateManagerController {
    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(CertificateController.class);

    @Autowired
    private CertificateManagerService certificateManagerService;

    @Autowired
    private UserInfoRepoService userInfoRepoService;

    /**
     * 查询证书列表
     */
    @GetMapping(value = "certificate/list")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    @CheckLogin
    public Result<List<CertificateBO>> certificateList(ConfirmRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取全部记录", request, new RestOperateCallBack<List<CertificateBO>>() {
            @Override
            public void before() {

            }

            @Override
            public Result<List<CertificateBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                CertificateManagerRequest certificateManagerRequest = CertificateRequestBuilder.getInstance()
                        .withUserID(request.getUserId())
                        .build();
                List<CertificateBO> certificateBOList = certificateManagerService.fetchAllCertificateList(certificateManagerRequest, context);
                for (CertificateBO certificateBO : certificateBOList) {
                    Map<String, String> extInfo = certificateBO.getExtInfo();
                    extInfo.put("stuId", userInfoRepoService.queryUserInfoByUserId(certificateBO.getUserId()).getStuId());
                    extInfo.put("stuName", userInfoRepoService.queryUserInfoByUserId(certificateBO.getUserId()).getRealName());
                }
                return RestResultUtil.buildSuccessResult(certificateBOList, "获取所有证书信息成功");
            }

        });
    }

    /**
     * 证书信息导入
     */
}
