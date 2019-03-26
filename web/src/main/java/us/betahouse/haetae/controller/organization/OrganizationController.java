/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.controller.organization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.converter.OrganizationVOConverter;
import us.betahouse.haetae.model.organization.OrganizationRestRequest;
import us.betahouse.haetae.model.organization.OrganizationVO;
import us.betahouse.haetae.serviceimpl.activity.service.OrganizationService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 组织接口
 *
 * @author MessiahJK
 * @version : OrganizationController.java 2018/11/25 15:05 MessiahJK
 */
@RestController
@RequestMapping("/organization")
public class OrganizationController {
    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationService organizationService;

    @CheckLogin
    @GetMapping("/all")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<OrganizationVO>> getAllOrganization(OrganizationRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取组织列表", request, new RestOperateCallBack<List<OrganizationVO>>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
            }

            @Override
            public Result<List<OrganizationVO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                List<OrganizationVO> organizationVOList = CollectionUtils.toStream(organizationService.findAll(context))
                        .filter(Objects::nonNull)
                        .map(OrganizationVOConverter::convert)
                        .sorted(Comparator.comparingInt(o -> o.getFirstAlpha().charAt(0)))
                        .collect(Collectors.toList());
                return RestResultUtil.buildSuccessResult(organizationVOList, "获取组织列表成功");
            }
        });
    }
}
