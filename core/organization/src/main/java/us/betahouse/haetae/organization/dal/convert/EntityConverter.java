/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.dal.convert;

import com.alibaba.fastjson.JSON;
import us.betahouse.haetae.organization.dal.model.OrganizationDO;
import us.betahouse.haetae.organization.dal.model.OrganizationMemberDO;
import us.betahouse.haetae.organization.dal.model.OrganizationRelationDO;
import us.betahouse.haetae.organization.model.OrganizationBO;
import us.betahouse.haetae.organization.model.OrganizationMemberBO;
import us.betahouse.haetae.organization.model.OrganizationRelationBO;

import java.util.Map;

/**
 * 实体转换器
 *
 * @author dango.yxm
 * @version : EntityConverter.java 2019/03/25 13:37 dango.yxm
 */
final public class EntityConverter {

    /**
     * 组织DO2BO
     *
     * @param organizationDO
     * @return
     */
    @SuppressWarnings("unchecked")
    public static OrganizationBO convert(OrganizationDO organizationDO) {
        if (organizationDO == null) {
            return null;
        }
        OrganizationBO organizationBO = new OrganizationBO();
        organizationBO.setOrganizationId(organizationDO.getOrganizationId());
        organizationBO.setOrganizationName(organizationDO.getOrganizationName());
        organizationBO.setOrganizationType(organizationDO.getOrganizationType());
        organizationBO.setExtInfo(JSON.parseObject(organizationDO.getExtInfo(), Map.class));
        return organizationBO;
    }

    /**
     * 组织BO2DO
     *
     * @param organizationBO
     * @return
     */
    public static OrganizationDO convert(OrganizationBO organizationBO) {
        if (organizationBO == null) {
            return null;
        }
        OrganizationDO organizationDO = new OrganizationDO();
        organizationDO.setOrganizationId(organizationBO.getOrganizationId());
        organizationDO.setOrganizationName(organizationBO.getOrganizationName());
        organizationDO.setOrganizationType(organizationBO.getOrganizationType());
        organizationDO.setExtInfo(JSON.toJSONString(organizationBO.getExtInfo()));
        return organizationDO;
    }

    /**
     * 组织成员关系DO2BO
     *
     * @param memberDO
     * @return
     */
    @SuppressWarnings("unchecked")
    public static OrganizationMemberBO convert(OrganizationMemberDO memberDO) {
        if (memberDO == null) {
            return null;
        }
        OrganizationMemberBO memberBO = new OrganizationMemberBO();
        memberBO.setOrganizationMemberId(memberDO.getOrganizationMemberId());
        memberBO.setMemberId(memberDO.getMemberId());
        memberBO.setOrganizationId(memberDO.getOrganizationId());
        memberBO.setMemberType(memberDO.getMemberType());
        memberBO.setMemberDescription(memberDO.getMemberDescription());
        memberBO.setOrganizationName(memberDO.getOrganizationName());
        memberBO.setExtInfo(JSON.parseObject(memberDO.getExtInfo(), Map.class));
        return memberBO;
    }

    /**
     * 组织成员关系BO2DO
     *
     * @param memberBO
     * @return
     */
    public static OrganizationMemberDO convert(OrganizationMemberBO memberBO) {
        if (memberBO == null) {
            return null;
        }
        OrganizationMemberDO memberDO = new OrganizationMemberDO();
        memberDO.setOrganizationMemberId(memberBO.getOrganizationMemberId());
        memberDO.setMemberId(memberBO.getMemberId());
        memberDO.setOrganizationId(memberBO.getOrganizationId());
        memberDO.setOrganizationName(memberBO.getOrganizationName());
        memberDO.setMemberType(memberBO.getMemberType());
        memberDO.setMemberDescription(memberBO.getMemberDescription());
        memberDO.setExtInfo(JSON.toJSONString(memberBO.getExtInfo()));
        return memberDO;
    }

    /**
     * 组织关系DO2BO
     *
     * @param relationDO
     * @return
     */
    @SuppressWarnings("unchecked")
    public static OrganizationRelationBO convert(OrganizationRelationDO relationDO) {
        if (relationDO == null) {
            return null;
        }
        OrganizationRelationBO relationBO = new OrganizationRelationBO();
        relationBO.setOrganizationRelationId(relationDO.getOrganizationRelationId());
        relationBO.setPrimaryOrganizationId(relationDO.getPrimaryOrganizationId());
        relationBO.setSubOrganizationId(relationDO.getSubOrganizationId());
        relationBO.setExtInfo(JSON.parseObject(relationDO.getExtInfo(), Map.class));
        return relationBO;
    }

    /**
     * 组织关系BO2DO
     *
     * @param relationBO
     * @return
     */
    public static OrganizationRelationDO convert(OrganizationRelationBO relationBO) {
        if (relationBO == null) {
            return null;
        }
        OrganizationRelationDO relationDO = new OrganizationRelationDO();
        relationDO.setOrganizationRelationId(relationBO.getOrganizationRelationId());
        relationDO.setPrimaryOrganizationId(relationBO.getPrimaryOrganizationId());
        relationDO.setSubOrganizationId(relationBO.getSubOrganizationId());
        relationDO.setExtInfo(JSON.toJSONString(relationBO.getExtInfo()));
        return relationDO;
    }
}