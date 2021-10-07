/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.user.builder;

import us.betahouse.haetae.user.model.basic.perm.PermBO;
import us.betahouse.util.common.ResultCode;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.DateUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 权限构建者
 *
 * @author dango.yxm
 * @version : PermBOBuilder.java 2018/11/18 上午12:01 dango.yxm
 */
final public class PermBOBuilder {

    /**
     * 权限类型
     */
    private String permType;

    /**
     * 权限名称
     */
    private String permName;

    /**
     * 权限描述
     */
    private String permDesc;

    /**
     * 权限有效期结束时间(现仅为导章时使用)
     */
    private Date start;

    /**
     * 权限有效期结束时间(现仅为导章时使用)
     */
    private Date end;

    /**
     * 拓展信息
     */
    private Map<String, String> extInfo = new HashMap<>();

    public static PermBOBuilder getInstance(String permType, String permName) {
        return new PermBOBuilder(permType, permName);
    }

    public static PermBOBuilder getInstance() {
        return new PermBOBuilder();
    }

    public PermBO build() {
        PermBO permBO = new PermBO();
        permBO.setPermType(permType);
        permBO.setPermName(permName);
        permBO.setPermDesc(permDesc);

        if(start!=null&&end!=null){
            permBO.setStart(start);
            permBO.setEnd(end);
        }
        if (extInfo != null) {
            permBO.setExtInfo(extInfo);
        }
        return permBO;
    }

    private PermBOBuilder(String permType, String permName) {
        this.permType = permType;
        this.permName = permName;
    }

    private PermBOBuilder() {
    }

    public PermBOBuilder withPermType(String permType) {
        this.permType = permType;
        return this;
    }

    public PermBOBuilder withPermName(String permName) {
        this.permName = permName;
        return this;
    }

    public PermBOBuilder withPermDesc(String permDesc) {
        this.permDesc = permDesc;
        return this;
    }

    public PermBOBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }

    public PermBOBuilder withStart(Long start){
        Date date=new Date(start);
        this.start= date;
        return this;
    }

    public PermBOBuilder withEnd(Long end){
        Date date=new Date(end);
        this.end= date;
        AssertUtil.assertTrue(start.before(this.end),CommonResultCode.ILLEGAL_PARAMETERS,"盖章开始时间必须早于结束时间");
        return this;
    }
}
