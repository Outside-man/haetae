/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.enums;

/**
 * 权限类型
 *
 * @author dango.yxm
 * @version : PermType.java 2018/11/24 7:18 PM dango.yxm
 */
public interface PermType {

    /**
     * 获取权限类型
     *
     * @return
     */
    String getCode();

    /**
     * 获取权限描述
     *
     * @return
     */
    String getDesc();

    /**
     * 是否需要初始化
     *
     * @return
     */
    boolean isInit();
}
