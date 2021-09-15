package us.betahouse.haetae.serviceimpl.user.request;

import org.springframework.web.multipart.MultipartFile;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyRequest;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.haetae.user.model.basic.perm.UserBO;

import java.util.List;

/**
 *
 */
public class UploadUserExcelRequest implements VerifyRequest {
    /**
     * 导表员id
     */
    private String userId;

    /**
     * excel表
     */
    private MultipartFile file;

    /**
     *表中用户信息
     */
    private List<UserInfoBO> userInfoBOS;

    /**
     * 表中用户实体
     */
    private List<UserBO> userBOS;

    public List<UserBO> getUserBOS() {
        return userBOS;
    }

    public void setUserBOS(List<UserBO> userBOS) {
        this.userBOS = userBOS;
    }

    @Override
    public String getVerifyUserId() {
        return getUserId();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public List<UserInfoBO> getUserInfoBOS() {
        return userInfoBOS;
    }

    public void setUserInfoBOS(List<UserInfoBO> userInfoBOS) {
        this.userInfoBOS = userInfoBOS;
    }
}
