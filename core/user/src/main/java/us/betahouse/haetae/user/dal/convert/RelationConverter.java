/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.convert;

import com.alibaba.fastjson.JSON;
import us.betahouse.haetae.user.dal.model.perm.RolePermRelationDO;
import us.betahouse.haetae.user.dal.model.perm.UserPermRelationDO;
import us.betahouse.haetae.user.dal.model.perm.UserRoleRelationDO;
import us.betahouse.haetae.user.model.basic.perm.RolePermRelationBO;
import us.betahouse.haetae.user.model.basic.perm.UserPermRelationBO;
import us.betahouse.haetae.user.model.basic.perm.UserRoleRelationBO;

import java.util.Map;

/**
 * 关系转换器
 *
 * @author dango.yxm
 * @version : RelationConverter.java 2018/11/23 11:06 AM dango.yxm
 */
final public class RelationConverter {

    /**
     * 用户角色关系 DO2BO
     *
     * @param relationDO
     * @return
     */
    @SuppressWarnings("unchecked")
    public static UserRoleRelationBO convert(UserRoleRelationDO relationDO) {
        if (relationDO == null) {
            return null;
        }
        UserRoleRelationBO relationBO = new UserRoleRelationBO();
        relationBO.setUserRoleId(relationDO.getUserRoleId());
        relationBO.setRoleId(relationDO.getRoleId());
        relationBO.setUserId(relationDO.getUserId());
        relationBO.setExtInfo(JSON.parseObject(relationDO.getExtInfo(), Map.class));
        return relationBO;
    }

    /**
     * 用户角色关系 BO2DO
     *
     * @param relationBO
     * @return
     */
    public static UserRoleRelationDO convert(UserRoleRelationBO relationBO) {
        if (relationBO == null) {
            return null;
        }
        UserRoleRelationDO relationDO = new UserRoleRelationDO();
        relationDO.setUserRoleId(relationBO.getUserRoleId());
        relationDO.setRoleId(relationBO.getRoleId());
        relationDO.setUserId(relationBO.getUserId());
        relationDO.setExtInfo(JSON.toJSONString(relationBO.getExtInfo()));
        return relationDO;
    }


    /**
     * 用户权限关系DO2BO
     *
     * @param relationDO
     * @return
     */
    @SuppressWarnings("unchecked")
    public static UserPermRelationBO convert(UserPermRelationDO relationDO) {
        if (relationDO == null) {
            return null;
        }
        UserPermRelationBO relationBO = new UserPermRelationBO();
        relationBO.setUserPermId(relationDO.getUserPermId());
        relationBO.setUserId(relationDO.getUserId());
        relationBO.setPermId(relationDO.getPermId());
        relationBO.setExtInfo(JSON.parseObject(relationDO.getExtInfo(), Map.class));
        return relationBO;
    }

    /**
     * 用户权限关系BO2DO
     *
     * @param relationBO
     * @return
     */
    public static UserPermRelationDO convert(UserPermRelationBO relationBO) {
        if (relationBO == null) {
            return null;
        }
        UserPermRelationDO relationDO = new UserPermRelationDO();
        relationDO.setUserPermId(relationBO.getUserPermId());
        relationDO.setUserId(relationBO.getUserId());
        relationDO.setPermId(relationBO.getPermId());
        relationDO.setExtInfo(JSON.toJSONString(relationBO.getExtInfo()));
        return relationDO;
    }

    /**
     * 角色权限关系DO2BO
     *
     * @param relationDO
     * @return
     */
    @SuppressWarnings("unchecked")
    public static RolePermRelationBO convert(RolePermRelationDO relationDO) {
        if (relationDO == null) {
            return null;
        }
        RolePermRelationBO relationBO = new RolePermRelationBO();
        relationBO.setRolePermId(relationDO.getRolePermId());
        relationBO.setRoleId(relationDO.getRoleId());
        relationBO.setPermId(relationDO.getPermId());
        relationBO.setExtInfo(JSON.parseObject(relationDO.getExtInfo(), Map.class));
        return relationBO;
    }

    /**
     * 角色权限关系BO2DO
     *
     * @param relationBO
     * @return
     */
    public static RolePermRelationDO convert(RolePermRelationBO relationBO) {
        if (relationBO == null) {
            return null;
        }
        RolePermRelationDO relationDO = new RolePermRelationDO();
        relationDO.setRolePermId(relationBO.getRolePermId());
        relationDO.setRoleId(relationBO.getRoleId());
        relationDO.setPermId(relationBO.getPermId());
        relationDO.setExtInfo(JSON.toJSONString(relationBO.getExtInfo()));
        return relationDO;
    }
}
