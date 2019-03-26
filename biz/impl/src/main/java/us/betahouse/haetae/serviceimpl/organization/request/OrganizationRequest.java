/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.organization.request;

import us.betahouse.haetae.organization.request.OrganizationManageRequest;

/**
 * 组织请求
 *
 * @author dango.yxm
 * @version : OrganizationRequest.java 2019/03/26 22:52 dango.yxm
 */
public class OrganizationRequest extends OrganizationManageRequest {

    private static final long serialVersionUID = -565032900622148594L;

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
}