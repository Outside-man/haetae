/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.common.session;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import us.betahouse.haetae.common.RestAop;
import us.betahouse.haetae.common.RestRequest;
import us.betahouse.haetae.user.model.basic.perm.UserBO;
import us.betahouse.haetae.user.user.service.UserBasicService;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.utils.AssertUtil;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;

/**
 * 会话服务
 *
 * @author dango.yxm
 * @version : SessionService.java 2018/11/25 12:19 AM dango.yxm
 */
@Order(1)
@Aspect
@Component
public class SessionService extends RestAop {

    /**
     * token 请求头
     */
    private static final String AUTHORIZATION = "Authorization";

    @Autowired
    private UserBasicService userBasicService;

    @Pointcut("execution(* us.betahouse.haetae.controller..*(..)) && @annotation(us.betahouse.haetae.common.session.CheckLogin)")
    public void checkLogin() {
    }

    /**
     * 校验登陆态
     *
     * @param proceedingJoinPoint
     */
    @Around("checkLogin()")
    public Object verify(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        RestRequest request = parseRestRequest(proceedingJoinPoint);
        HttpServletRequest httpServletRequest = parseHttpServletRequest(proceedingJoinPoint);
        AssertUtil.assertNotNull(request, CommonResultCode.SYSTEM_ERROR.getCode(), "登陆检测失败, 没有登陆请求");
        AssertUtil.assertNotNull(httpServletRequest, CommonResultCode.SYSTEM_ERROR.getCode(), "登陆检测失败, 没有登陆凭证");

        String token = httpServletRequest.getHeader(AUTHORIZATION);
        if (StringUtils.isBlank(token)) {
            return RestResultUtil.buildResult(RestResultCode.UNAUTHORIZED, "用户未登录");
        }
        BigInteger bigInteger =new BigInteger("123");
        bigInteger.mod(BigInteger.ONE);
        // 检测登录态
        UserBO userBO = userBasicService.checkLogin(token, IPUtil.getIpAddr(httpServletRequest));
        if (userBO == null) {
            return RestResultUtil.buildResult(RestResultCode.UNAUTHORIZED, "用户未登录");
        }
        request.setUserId(userBO.getUserId());

        return proceedingJoinPoint.proceed();
    }
}
