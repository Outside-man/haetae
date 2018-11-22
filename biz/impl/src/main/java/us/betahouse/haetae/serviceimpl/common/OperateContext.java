/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.common;

import us.betahouse.util.common.ToString;

/**
 * 操作上下文
 *
 * @author dango.yxm
 * @version : OperateContext.java 2018/11/22 1:34 PM dango.yxm
 */
public class OperateContext extends ToString {

    private static final long serialVersionUID = 4106891104857327576L;

    private String OperateIP;

    public String getOperateIP() {
        return OperateIP;
    }

    public void setOperateIP(String operateIP) {
        OperateIP = operateIP;
    }
}
