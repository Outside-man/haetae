package us.betahouse.haetae.activity.dal.model;

public class ActualStamperDO {
    private String activityId;
    private Long stamperNumber;

    public ActualStamperDO(String activityId,Long stamperNumber){
        this.activityId=activityId;
        this.stamperNumber=stamperNumber;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public Long getStamperNumber() {
        return stamperNumber;
    }

    public void setStamperNumber(Long stamperNumber) {
        this.stamperNumber = stamperNumber;
    }
}
