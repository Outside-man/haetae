/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.model;

import org.apache.commons.lang.StringUtils;
import us.betahouse.haetae.activity.enums.ActivityStateEnum;
import us.betahouse.haetae.activity.enums.ActivityTypeEnum;
import us.betahouse.util.common.ToString;
import us.betahouse.util.utils.DateUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 活动领域对象
 *
 * @author dango.yxm
 * @version : ActivityBO.java 2018/11/15 下午2:16 dango.yxm
 */
public class ActivityBO extends ToString {

    private static final long serialVersionUID = 6803113655032865227L;
    /**
     * 活动id
     */
    private String activityId;

    /**
     * 活动名
     */

    private String activityName;

    /**
     * 活动类型
     */

    private String type;

    /**
     * 单位信息
     */

    private String organizationMessage;

    /**
     * 活动地点
     */
    private String location;

    /**
     * 活动开始时间
     */
    private Date start;

    /**
     * 活动结束时间
     */
    private Date end;

    /**
     * 活动分数
     */
    private Long score;

    /**
     * 活动描述
     */
    private String description;

    /**
     * 活动创建者
     */
    private String creatorId;

    /**
     * 活动状态
     *
     * @see ActivityStateEnum
     */
    private String state;

    /**
     * 活动学期
     */
    private String term;

    /**
     * 拓展信息
     */
    private Map<String, String> extInfo = new HashMap<>();

    public String fetchExtInfo(String key) {
        if (extInfo == null) {
            return null;
        }
        return extInfo.get(key);
    }

    public void putExtInfo(String key, String value) {
        if (extInfo == null) {
            extInfo = new HashMap<>();
        }
        extInfo.put(key, value);
    }

    /**
     * 判断是否能盖章
     *
     * @return
     */
    public boolean canStamp() {
        // 活动重启 直接认为可以盖章
        if (StringUtils.equals(state, ActivityStateEnum.RESTARTED.getCode())) {
            return true;
        }
        ActivityTypeEnum activityTypeEnum = ActivityTypeEnum.getByCode(type);
        if (activityTypeEnum == null) {
            return false;
        }
        switch (activityTypeEnum) {
            case VOLUNTEER_WORK:
                return true;
            case PRACTICE_ACTIVITY:
                return true;
            case VOLUNTEER_ACTIVITY:
                return StringUtils.equals(state, ActivityStateEnum.PUBLISHED.getCode()) && DateUtil.nowIsBetween(start, end);
            case SCHOOL_ACTIVITY:
                return StringUtils.equals(state, ActivityStateEnum.PUBLISHED.getCode()) && DateUtil.nowIsBetween(start, end);
            default:
                return false;
        }
    }

    /**
     * 判断是否可以结束
     *
     * @return
     */
    public boolean canFinish() {
        // 活动手动重启 系统不会去结束
        if (StringUtils.equals(state, ActivityStateEnum.RESTARTED.getCode())) {
            return false;
        }
        ActivityTypeEnum activityTypeEnum = ActivityTypeEnum.getByCode(type);
        if (activityTypeEnum == null) {
            return false;
        }
        switch (activityTypeEnum) {
            case VOLUNTEER_WORK:
                // 义工都不要结束
                return false;
            case PRACTICE_ACTIVITY:
                // 实践不要结束
                return false;
            case VOLUNTEER_ACTIVITY:
                return StringUtils.equals(state, ActivityStateEnum.PUBLISHED.getCode()) && end.before(new Date());
            case SCHOOL_ACTIVITY:
                return StringUtils.equals(state, ActivityStateEnum.PUBLISHED.getCode()) && end.before(new Date());
            default:
                return false;
        }
    }


    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrganizationMessage() {
        return organizationMessage;
    }

    public void setOrganizationMessage(String organizationMessage) {
        this.organizationMessage = organizationMessage;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }
}
