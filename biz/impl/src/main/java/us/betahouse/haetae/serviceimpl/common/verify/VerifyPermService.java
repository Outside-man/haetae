/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.common.verify;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.betahouse.haetae.user.user.service.UserBasicService;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.LoggerUtil;

/**
 * 鉴权切面服务
 *
 * @author dango.yxm
 * @version : VerifyPermService.java 2018/11/24 7:59 PM dango.yxm
 */
@Aspect
@Component
public class VerifyPermService {

    private final Logger LOGGER = LoggerFactory.getLogger(VerifyPermService.class);

    @Autowired
    private UserBasicService userBasicService;

    @Pointcut("execution(* us.betahouse.haetae.serviceimpl..*(..)) && @annotation(us.betahouse.haetae.serviceimpl.common.verify.VerifyPerm)")
    public void targetVerify() {
    }

    /**
     * 判断是否有权
     *
     * @param joinPoint
     * @param verify
     */
    @Before("targetVerify() && @annotation(verify)")
    public void verify(JoinPoint joinPoint, VerifyPerm verify) {
        Object[] objs = joinPoint.getArgs();
        VerifyRequest request = null;
        for (Object o : objs) {
            if (o instanceof VerifyRequest) {
                request = (VerifyRequest) o;
                break;
            }
        }
        AssertUtil.assertNotNull(request, CommonResultCode.SYSTEM_ERROR.getCode(), "鉴权失败, 没有鉴权对象");

        boolean verifyPerm = userBasicService.verifyPermission(request.getUserId(), verify.value());
        if (!verifyPerm) {
            LoggerUtil.warn(LOGGER, "用户无权操作 userId={0}, permType={1}", request.getUserId(), verify.value());
            throw new BetahouseException(CommonResultCode.UNAUTHORIZED);
        }
    }
}
