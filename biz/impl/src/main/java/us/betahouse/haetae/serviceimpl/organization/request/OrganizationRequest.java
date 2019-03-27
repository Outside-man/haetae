/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.organization.request;

import us.betahouse.haetae.organization.request.OrganizationManageRequest;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyRequest;

/**
 * 组织请求
 *
 * @author dango.yxm
 * @version : OrganizationRequest.java 2019/03/26 22:52 dango.yxm
 */
public class OrganizationRequest extends OrganizationManageRequest implements VerifyRequest {

    private static final long serialVersionUID = -565032900622148594L;

    /**
     * 操作员id
     */
    private String userId;

    /**
     * 学号
     */
    private String stuId;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String getVerifyUserId() {
        return userId;
    }
}