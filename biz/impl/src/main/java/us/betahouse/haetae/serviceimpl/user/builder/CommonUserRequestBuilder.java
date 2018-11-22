/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.user.builder;

import org.hibernate.validator.constraints.NotBlank;
import us.betahouse.haetae.serviceimpl.user.request.CommonUserRequest;
import us.betahouse.haetae.user.model.basic.UserInfoBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用用户请求构建器
 *
 * @author dango.yxm
 * @version : CommonUserRequestBuilder.java 2018/11/22 2:13 PM dango.yxm
 */
final public class CommonUserRequestBuilder {

    /**
     * 请求id
     */
    @NotBlank
    private String requestId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 微信小程序code
     */
    private String code;

    /**
     * 用户名
     */
    private String username;

    /**
     * 明文密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 小程序用户id
     */
    private String openId;

    /**
     * 用户信息
     */
    private UserInfoBO userInfoBO;

    /**
     * 绑定的角色ids
     */
    List<String> roleIds = new ArrayList<>();

    /**
     * 绑定的权限ids
     */
    List<String> permIds = new ArrayList<>();

    /**
     * 拓展信息
     */
    private Map<String, String> extInfo = new HashMap<>();

    public static CommonUserRequestBuilder getInstance() {
        return new CommonUserRequestBuilder();
    }

    public CommonUserRequest build() {
        CommonUserRequest request = new CommonUserRequest();
        request.setRequestId(requestId);
        request.setCode(code);
        request.setUserId(userId);
        request.setPassword(password);
        request.setUsername(username);
        request.setSalt(salt);
        request.setOpenId(openId);
        request.setUserInfoBO(userInfoBO);
        request.setRoleIds(roleIds);
        request.setPermIds(permIds);
        request.setExtInfo(extInfo);
        return request;
    }

    private CommonUserRequestBuilder() {
    }

    public CommonUserRequestBuilder withRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public CommonUserRequestBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public CommonUserRequestBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public CommonUserRequestBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public CommonUserRequestBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public CommonUserRequestBuilder withSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public CommonUserRequestBuilder withOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public CommonUserRequestBuilder withUserInfoBO(UserInfoBO userInfoBO) {
        this.userInfoBO = userInfoBO;
        return this;
    }

    public CommonUserRequestBuilder withRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
        return this;
    }

    public CommonUserRequestBuilder withPermIds(List<String> permIds) {
        this.permIds = permIds;
        return this;
    }

    public CommonUserRequestBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }
}
