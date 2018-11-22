/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * ip 处理工具类
 *
 * @author dango.yxm
 * @version : IPUtil.java 2018/11/22 2:57 PM dango.yxm
 */
public class IPUtil {

    /**
     * 空字符串
     */
    private final static String EMPTY_STRING = "";

    /**
     * 获取请求里面的ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        if (request == null) {
            return EMPTY_STRING;
        }
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }
}
