/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.session;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import us.betahouse.haetae.request.RestRequest;
import us.betahouse.haetae.user.model.basic.perm.UserBO;
import us.betahouse.haetae.user.user.service.UserBasicService;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.utils.AssertUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 会话服务
 *
 * @author dango.yxm
 * @version : SessionService.java 2018/11/25 12:19 AM dango.yxm
 */
@Order(1)
@Aspect
@Component
public class SessionService {

    @Autowired
    private UserBasicService userBasicService;

    @Pointcut("execution(* us.betahouse.haetae.controller..*(..)) && @annotation(us.betahouse.haetae.session.CheckLogin)")
    public void checkLogin() {
    }

    /**
     * 校验登陆态
     *
     * @param proceedingJoinPoint
     */
    @Around("checkLogin()")
    public Object verify(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] objs = proceedingJoinPoint.getArgs();
        RestRequest request = null;
        HttpServletRequest httpServletRequest = null;
        for (Object o : objs) {
            if (o instanceof RestRequest) {
                request = (RestRequest) o;
            }
            if (o instanceof HttpServletRequest) {
                httpServletRequest = (HttpServletRequest) o;
            }
        }
        AssertUtil.assertNotNull(request, CommonResultCode.SYSTEM_ERROR.getCode(), "登陆检测失败, 没有登陆请求");

        if (StringUtils.isBlank(request.getToken())) {
            return RestResultUtil.buildResult(RestResultCode.UNAUTHORIZED, "用户未登录");
        }

        // 检测登录态
        UserBO userBO = userBasicService.checkLogin(request.getToken(), IPUtil.getIpAddr(httpServletRequest));
        if (userBO == null) {
            return RestResultUtil.buildResult(RestResultCode.UNAUTHORIZED, "用户未登录");
        }
        request.setUserId(userBO.getUserId());

        return proceedingJoinPoint.proceed();
    }
}
