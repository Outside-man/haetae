/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.request;

import org.hibernate.validator.constraints.NotBlank;
import us.betahouse.util.common.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guofan.cp
 * @version : BaseRequest.java 2019/01/20 22:35 guofan.cp
 */
public abstract class BaseRequest extends ToString {
    private static final long serialVersionUID = 7606672139580193914L;
    /**
     * 请求id
     */
    @NotBlank
    private String requestId;
    /**
     * 拓展信息
     */
    private Map<String, String> extInfo = new HashMap<>();

    /**
     * @Description: 放入拓展信息
     * @Param:
     */
    public void putExtInfo(String key, String value) {
        if (extInfo == null) {
            extInfo = new HashMap<>();
        }
        extInfo.put(key, value);
    }

    /**
     * @Description: 取出拓展信息
     * @Param:
     */
    public String fetchExtInfo(String key) {
        if (extInfo == null) {
            return null;
        }
        return extInfo.get(key);
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }
}
