/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.idfactory;

/**
 * 业务id生成工厂
 * 生成32位业务id
 *
 * @author dango.yxm
 * @version : BizIdFactory.java 2018/10/06 下午1:23 dango.yxm
 */
public interface BizIdFactory {

    /**
     * 生成用户id
     * @return
     */
    String getUserId();

    /**
     * 生成角色id
     * @return
     */
    String getRoleId();

    /**
     * 生成用户角色关联关系id
     * @param roleId
     * @param userId
     * @return
     */
    String getRoleUserRelationId(String roleId, String userId);

    /**
     * 生成权限id
     * @return
     */
    String getPermId();

    /**
     * 生成角色权限关联关系id
     * @param roleId
     * @param permId
     * @return
     */
    String getRolePermRelationId(String roleId, String permId);

    /**
     * 生成用户权限关联关系id
     * @param userId
     * @param permId
     * @return
     */
    String getUserPermRelationId(String userId, String permId);
}
