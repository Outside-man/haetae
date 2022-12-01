/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.model.organization;

import us.betahouse.haetae.organization.model.OrganizationBO;

/**
 * 组织展示对象
 *
 * @author dango.yxm
 * @version : OrganizationVO.java 2019/03/26 21:42 dango.yxm
 */
public class OrganizationVO extends OrganizationBO {

    private static final long serialVersionUID = 2370416437078583527L;

    /**
     * 首字母
     */
    private String firstAlpha;

    public String getFirstAlpha() {
        return firstAlpha;
    }

    public void setFirstAlpha(String firstAlpha) {
        this.firstAlpha = firstAlpha;
    }
}