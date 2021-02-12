/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.user.model.basic;

import us.betahouse.util.common.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MessiahJK
 * @version : MajorBO.java 2019/03/05 20:03 MessiahJK
 */
public class MajorBO extends ToString {

    private static final long serialVersionUID = 605663238888983218L;

    private String majorId;

    private String majorName;

    private Map<String, String> extInfo = new HashMap<>();

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }
}
