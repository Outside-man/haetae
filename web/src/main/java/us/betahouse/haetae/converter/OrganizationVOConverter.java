/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.converter;

import us.betahouse.haetae.model.organization.OrganizationVO;
import us.betahouse.haetae.organization.model.OrganizationBO;
import us.betahouse.util.dictionary.PinyinUtils;

/**
 * 组织VO转换器
 *
 * @author dango.yxm
 * @version : OrganizationVOConverter.java 2019/03/26 21:44 dango.yxm
 */
final public class OrganizationVOConverter {

    /**
     * 转换组织VO
     *
     * @param organizationBO
     * @return
     */
    public static OrganizationVO convert(OrganizationBO organizationBO) {
        OrganizationVO organizationVO = new OrganizationVO();
        organizationVO.setOrganizationId(organizationBO.getOrganizationId());
        organizationVO.setOrganizationName(organizationBO.getOrganizationName());
        organizationVO.setOrganizationType(organizationBO.getOrganizationType());
        organizationVO.setExtInfo(organizationBO.getExtInfo());
        organizationVO.setFirstAlpha(PinyinUtils.getFirstAlpha(organizationVO.getOrganizationName()));
        return organizationVO;
    }

}