/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.util.yiban;

import us.betahouse.util.common.ToString;

/**
 * @author MessiahJK
 * @version : YiModel.java 2019/07/04 8:57 MessiahJK
 */
public class YiModel extends ToString {

    /**
     * 访问令牌
     */
    private String accessToken;

    private String userId;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
