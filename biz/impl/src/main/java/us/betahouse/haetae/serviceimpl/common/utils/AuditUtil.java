package us.betahouse.haetae.serviceimpl.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import us.betahouse.haetae.serviceimpl.activity.model.AuditMessage;
import us.betahouse.haetae.serviceimpl.schedule.manager.AccessTokenManage;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.DateUtil;
import us.betahouse.util.utils.HttpUtils;

import java.text.MessageFormat;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class AuditUtil {
    /**
     * 模板id
     */
    private static String TEMPLATE_ID;

    @Value("${wechat.AuditTemplateId}")
    public void setTemplateId(String templateId) {    //注意这里的set方法不能是静态的
        AuditUtil.TEMPLATE_ID = templateId;
    }

    /**
     *推送api
     */
    private final static String PUBLISH_URL="https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token={0}";

    /**
     * 返回体的错误码
     */
    private final static String ERROR_CODE = "errcode";

    /**
     * 返回体的错误信息
     */
    private final static String ERROR_MESSAGE = "errmsg";

    private static final ReentrantLock lock = new ReentrantLock();

    public static String publishAuditByOpenId(String page , String openId  , String accessToken, AuditMessage audit){

        String url = MessageFormat.format(PUBLISH_URL,accessToken);


        JSONObject jsonObject=new JSONObject();

        jsonObject.put("touser",openId);
        jsonObject.put("template_id",TEMPLATE_ID);
        if (!StringUtils.isEmpty(page)){
            jsonObject.put("page" , page);
        }
        JSONObject data=new JSONObject();
        JSONObject phrase = new JSONObject();
        phrase.put("value",audit.getResult());

        JSONObject detail = new JSONObject();
        detail.put("value",audit.getDetail());
        JSONObject date = new JSONObject();
        date.put("value",audit.getAuditTime());
        JSONObject applicant = new JSONObject();
        applicant.put("value",audit.getApplicant());
        JSONObject Note = new JSONObject();
        String note = audit.getNote();
        if (note !=null ) {
            Note.put("value",note);
        }else {
            Note.put("value","如有疑问请联系管理者");
        }

        data.put("phrase1", phrase);
        data.put("thing2",detail);
        data.put("date3",date);
        data.put("thin11",applicant);
        data.put("thing7",Note);

        jsonObject.put("data",data);

        try {
            String result = HttpUtils.doPostUseJson(url,jsonObject.toString());

            JSONObject resultJson = JSONObject.parseObject(result);
            String code = resultJson.getString(ERROR_CODE);
            //GC
            jsonObject=null;
            result=null;
            if (StringUtils.equals(code,"0")) {
                return CommonResultCode.SUCCESS.getCode();
            }else if (StringUtils.equals(code,"42001")){
                //防止token被连续刷新多次
                lock.lock();
                //第一个知道令牌过期的调用 刷新令牌
                if (StringUtils.equals(accessToken, AccessTokenManage.GetToken())) {
                    accessToken = AccessTokenManage.refreshToken();
                    lock.unlock();
                    //刷新后再次推送
                    return AuditUtil.publishAuditByOpenId(page,openId, accessToken, audit);
                }else {
                    //重新获取新的令牌
                    accessToken=AccessTokenManage.GetToken();
                    lock.unlock();
                    //再次推送
                    return AuditUtil.publishAuditByOpenId(page,openId, accessToken, audit);
                }

            }else if (StringUtils.equals(code,"43101"))
                return CommonResultCode.FORBIDDEN.getCode();

        } catch (Exception e) {
            throw new BetahouseException(e, CommonResultCode.SYSTEM_ERROR.getCode(), "请求推送失败, 网络异常");
        }
        return CommonResultCode.SYSTEM_ERROR.getMessage();
    }
}
