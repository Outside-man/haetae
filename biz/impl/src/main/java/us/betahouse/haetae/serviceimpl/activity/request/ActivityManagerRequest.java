/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.request;

import us.betahouse.haetae.activity.request.ActivityRequest;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyRequest;

/**
 * 活动管理请求
 *
 * @author MessiahJK
 * @version : ActivityManagerRequest.java 2018/11/24 22:07 MessiahJK
 */
public class ActivityManagerRequest extends ActivityRequest implements VerifyRequest {

    private static final long serialVersionUID = -7830970003881573855L;


    private final static String STAMPER_STU_ID = "stamperStuId";

    @Override
    public String getVerifyUserId() {
        return getUserId();
    }


    /**
     * 存入赋权盖章员学号
     *
     * @param stuId
     */
    public void putStamperStuId(String stuId) {
        putExtInfo(STAMPER_STU_ID, stuId);
    }

    /**
     * 取出赋权盖章员学号
     *
     * @return
     */
    public String fetchStamperStuId() {
        return fetchExtInfo(STAMPER_STU_ID);
    }
}
