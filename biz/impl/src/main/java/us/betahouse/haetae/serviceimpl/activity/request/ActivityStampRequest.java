/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.request;

import us.betahouse.haetae.activity.enums.ActivityRecordExtInfoKey;
import us.betahouse.haetae.activity.request.ActivityRecordRequest;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyRequest;

import java.util.List;

/**
 * 活动盖章请求
 *
 * @author dango.yxm
 * @version : ActivityStampRequest.java 2018/11/24 8:23 PM dango.yxm
 */
public class ActivityStampRequest extends ActivityRecordRequest implements VerifyRequest {

    private static final long serialVersionUID = -8487946642055713409L;

    private List<String> stuIds;

    private String localURL;

    @Override
    public String getVerifyUserId() {
        return getScannerUserId();
    }

    public List<String> getStuIds() {
        return stuIds;
    }

    public void setStuIds(List<String> stuIds) {
        this.stuIds = stuIds;
    }

    public String getVolunteerWorkName() {
        return fetchExtInfo(ActivityRecordExtInfoKey.VOLUNTEER_WORK_NAME);
    }

    public void setVolunteerWorkName(String volunteerWorkName) {
        putExtInfo(ActivityRecordExtInfoKey.VOLUNTEER_WORK_NAME, volunteerWorkName);
    }

    public String getScannerName(){
        return fetchExtInfo(ActivityRecordExtInfoKey.SCANNER_NAME);
    }

    public void setScannerName(String scannerName){
        putExtInfo(ActivityRecordExtInfoKey.SCANNER_NAME, scannerName);
    }

    public String getLocalURL() {
        return localURL;
    }

    public void setLocalURL(String localURL) {
        this.localURL = localURL;
    }

}
