/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.convert;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.user.dal.model.MajorDO;
import us.betahouse.haetae.user.dal.model.UserInfoDO;
import us.betahouse.haetae.user.dal.model.perm.PermDO;
import us.betahouse.haetae.user.dal.model.perm.RoleDO;
import us.betahouse.haetae.user.dal.model.perm.UserDO;
import us.betahouse.haetae.user.dal.model.perm.UserRoleRelationDO;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.model.basic.MajorBO;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.haetae.user.model.basic.perm.PermBO;
import us.betahouse.haetae.user.model.basic.perm.RoleBO;
import us.betahouse.haetae.user.model.basic.perm.UserBO;
import us.betahouse.haetae.user.model.basic.perm.UserRoleRelationBO;

import java.util.Map;

/**
 * 实体转换器
 *
 * @author dango.yxm
 * @version : EntityConverter.java 2018/11/23 11:09 AM dango.yxm
 */
final public class EntityConverter {

    @Autowired
    UserInfoRepoService userInfoRepoService;

    /**
     * 权限DO2BO
     *
     * @param permDO
     * @return
     */
    @SuppressWarnings("unchecked")
    public static PermBO convert(PermDO permDO) {
        if (permDO == null) {
            return null;
        }
        PermBO permBO = new PermBO();
        permBO.setPermId(permDO.getPermId());
        permBO.setPermType(permDO.getPermType());
        permBO.setPermName(permDO.getPermName());
        permBO.setPermDesc(permDO.getPermDesc());
        permBO.setExtInfo(JSON.parseObject(permDO.getExtInfo(), Map.class));
        return permBO;
    }

    /**
     * 权限BO2DO
     *
     * @param permBO
     * @return
     */
    public static PermDO convert(PermBO permBO) {
        if (permBO == null) {
            return null;
        }
        PermDO permDO = new PermDO();
        permDO.setPermId(permBO.getPermId());
        permDO.setPermType(permBO.getPermType());
        permDO.setPermName(permBO.getPermName());
        permDO.setPermDesc(permBO.getPermDesc());
        permDO.setExtInfo(JSON.toJSONString(permBO.getExtInfo()));
        return permDO;
    }

    /**
     * 角色DO2BO
     *
     * @param roleDO
     * @return
     */
    @SuppressWarnings("unchecked")
    public static RoleBO convert(RoleDO roleDO) {
        if (roleDO == null) {
            return null;
        }
        RoleBO roleBO = new RoleBO();
        roleBO.setRoleCode(roleDO.getRoleCode());
        roleBO.setRoleName(roleDO.getRoleName());
        roleBO.setRoleId(roleDO.getRoleId());
        roleBO.setRoleDesc(roleDO.getRoleDesc());
        roleBO.setExtInfo(JSON.parseObject(roleDO.getExtInfo(), Map.class));
        return roleBO;
    }

    /**
     * 角色BO2DO
     *
     * @param roleBO
     * @return
     */
    public static RoleDO convert(RoleBO roleBO) {
        if (roleBO == null) {
            return null;
        }
        RoleDO roleDO = new RoleDO();
        roleDO.setRoleCode(roleBO.getRoleCode());
        roleDO.setRoleName(roleBO.getRoleName());
        roleDO.setRoleId(roleBO.getRoleId());
        roleDO.setRoleDesc(roleBO.getRoleDesc());
        roleDO.setExtInfo(JSON.toJSONString(roleBO.getExtInfo()));
        return roleDO;
    }

    /**
     * 用户信息 DO2BO
     *
     * @param userInfoDO
     * @return
     */
    @SuppressWarnings("unchecked")
    public static UserInfoBO convert(UserInfoDO userInfoDO) {
        if (userInfoDO == null) {
            return null;
        }
        UserInfoBO userInfoBO = new UserInfoBO();
        userInfoBO.setUserInfoId(userInfoDO.getUserInfoId());
        userInfoBO.setUserId(userInfoDO.getUserId());
        userInfoBO.setStuId(userInfoDO.getStuId());
        userInfoBO.setRealName(userInfoDO.getRealName());
        userInfoBO.setSex(userInfoDO.getSex());
        userInfoBO.setMajor(userInfoDO.getMajorId());
        userInfoBO.setGrade(userInfoDO.getGrade());
        userInfoBO.setClassId(userInfoDO.getClassId());
        userInfoBO.setEnrollDate(userInfoDO.getEnrollDate());
        userInfoBO.setExtInfo(JSON.parseObject(userInfoDO.getExtInfo(), Map.class));
        return userInfoBO;
    }


    /**
     * 用户信息保存专业和年级
     *
     * @param userInfoDO
     * @return
     */
    @SuppressWarnings("unchecked")
    public static UserInfoBO majorConvert(UserInfoDO userInfoDO) {
        if (userInfoDO == null) {
            return null;
        }
        UserInfoBO userInfoBO = new UserInfoBO();
        userInfoBO.setMajor(userInfoDO.getMajorId());
        userInfoBO.setGrade(userInfoDO.getGrade());
        userInfoBO.setEnrollDate(userInfoDO.getEnrollDate());
        userInfoBO.setExtInfo(JSON.parseObject(userInfoDO.getExtInfo(), Map.class));
        return userInfoBO;
    }


    /**
     * 用户信息 BO2DO
     *
     * @param userInfoBO
     * @return
     */
    public static UserInfoDO convert(UserInfoBO userInfoBO) {
        if (userInfoBO == null) {
            return null;
        }
        UserInfoDO userInfoDO = new UserInfoDO();
        userInfoDO.setUserInfoId(userInfoBO.getUserInfoId());
        userInfoDO.setUserId(userInfoBO.getUserId());
        userInfoDO.setStuId(userInfoBO.getStuId());
        userInfoDO.setRealName(userInfoBO.getRealName());
        userInfoDO.setSex(userInfoBO.getSex());
        userInfoDO.setMajorId(userInfoBO.getMajor());
        userInfoDO.setClassId(userInfoBO.getClassId());
        userInfoDO.setGrade(userInfoBO.getGrade());
        userInfoDO.setEnrollDate(userInfoBO.getEnrollDate());
        userInfoDO.setExtInfo(JSON.toJSONString(userInfoBO.getExtInfo()));
        return userInfoDO;
    }

    /**
     * 用户角色关联DO2BO
     *
     * @param userRoleRelationDO
     * @return
     */
    public static UserRoleRelationBO convert(UserRoleRelationDO userRoleRelationDO){
        if(userRoleRelationDO==null){
            return null;
        }
        UserRoleRelationBO userRoleRelationBO=new UserRoleRelationBO();
        userRoleRelationBO.setRoleId(userRoleRelationDO.getRoleId());
        userRoleRelationBO.setUserId(userRoleRelationDO.getUserId());
        userRoleRelationBO.setUserRoleId(userRoleRelationDO.getUserRoleId());
        return userRoleRelationBO;
    }



    /**
     * DO2BO
     *
     * @param userDO
     * @return
     */
    public static UserBO convert(UserDO userDO) {
        if (userDO == null) {
            return null;
        }
        UserBO userBO = new UserBO();
        userBO.setUserId(userDO.getUserId());
        userBO.setUserName(userDO.getUsername());
        userBO.setPassword(userDO.getPassword());
        userBO.setSalt(userDO.getSalt());
        userBO.setOpenId(userDO.getOpenId());
        userBO.setLastLoginIP(userDO.getLastLoginIP());
        userBO.setLastLoginDate(userDO.getLastLoginDate());
        userBO.setSessionId(userDO.getSessionId());
        return userBO;
    }

    /**
     * BO2DO
     *
     * @param userBO
     * @return
     */
    public static UserDO convert(UserBO userBO) {
        if (userBO == null) {
            return null;
        }
        UserDO userDO = new UserDO();
        userDO.setUserId(userBO.getUserId());
        userDO.setUsername(userBO.getUserName());
        userDO.setPassword(userBO.getPassword());
        userDO.setSalt(userBO.getSalt());
        userDO.setOpenId(userBO.getOpenId());
        userDO.setLastLoginIP(userBO.getLastLoginIP());
        userDO.setLastLoginDate(userBO.getLastLoginDate());
        userDO.setSessionId(userBO.getSessionId());
        return userDO;
    }

    public static MajorDO convert(MajorBO majorBO){
        MajorDO majorDO= new MajorDO();
        majorDO.setMajorId(majorBO.getMajorId());
        majorDO.setMajorName(majorBO.getMajorName());
        majorDO.setExtInfo(JSON.toJSONString(majorBO.getExtInfo()));
        return majorDO;
    }

    @SuppressWarnings("unchecked")
    public static MajorBO convert(MajorDO majorDO){
        MajorBO majorBO= new MajorBO();
        majorBO.setMajorId(majorDO.getMajorId());
        majorBO.setMajorName(majorDO.getMajorName());
        majorBO.setExtInfo(JSON.parseObject(majorDO.getExtInfo(),Map.class));
        return majorBO;
    }
}
