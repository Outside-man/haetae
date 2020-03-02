/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.model.user.vo;

import us.betahouse.haetae.user.model.BasicUser;

import java.util.List;

/**
 * 用户页面模型
 *
 * @author dango.yxm
 * @version : UserVO.java 2018/11/25 10:39 AM dango.yxm
 */
public class UserVO extends BasicUser {

    private static final long serialVersionUID = -6471152477592399776L;

    private List<String> roleInfo;

    public List<String> jobInfo;

    public List<String> getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(List<String> roleInfo) {
        this.roleInfo = roleInfo;
    }

    public List<String> getJobInfo() {
        return jobInfo;
    }

    public void setJobInfo(List<String> jobInfo) {
        this.jobInfo = jobInfo;
    }
}
