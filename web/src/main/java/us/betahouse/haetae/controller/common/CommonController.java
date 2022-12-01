/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.controller.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.controller.user.UserController;
import us.betahouse.haetae.serviceimpl.schedule.manager.AccessTokenManage;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.aliyun.AliyunOssUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.HttpUtils;
import us.betahouse.util.wechat.WeChatAccessTokenUtil;
import us.betahouse.util.wechat.WeChatShareImgUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.*;

/**
 * @author MessiahJK
 * @version : CommonController.java 2019/10/06 0:14 MessiahJK
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/common")
public class CommonController {
    @Autowired
    private AliyunOssUtil aliyunOssUtil;
    
    /**
     * 请求路径模板
     */
    private static final String SHARE_IMG_URL_TEMPLATE = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=";
    
    private static final ReentrantLock lock = new ReentrantLock();
    
    /**
     * 返回体的错误码
     */
    private final static String ERROR_CODE = "errcode";
    
    /**
     * 日志
     */
    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * 上传文件到aliyun云存储
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @PostMapping("/aliyun")
    @ResponseBody
    public Result<Map> uploadImgAliyunOSS(@RequestParam("file") MultipartFile multipartFile, @RequestParam("fileName") String fileName) throws IOException {
        return RestOperateTemplate.operate(LOGGER, "用户文件上传", null, new RestOperateCallBack<Map>() {
            @Override
            public Result<Map> execute() throws IOException {
                FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
                String path = aliyunOssUtil.uploadFile(fileName, "/haetae/", inputStream);
                Map map = new HashMap(4);
                map.put("path", path);
                return RestResultUtil.buildSuccessResult(map, "上传成功");
            }
        });
    }

    /**
     * 上传钉钉审批截图到aliyun云存储
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @PostMapping("/aliyun/ding")
    @ResponseBody
    public Result<Map> uploadImgAliyunOSSDing(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return RestOperateTemplate.operate(LOGGER, "用户文件上传", null, new RestOperateCallBack<Map>() {
            @Override
            public Result<Map> execute() throws IOException {
                FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
                String path = aliyunOssUtil.uploadFileDing("/haetae/ding", inputStream);
                Map map = new HashMap(4);
                map.put("path", path);
                return RestResultUtil.buildSuccessResult(map, "上传成功");
            }
        });
    }
    
    /**
     * 调用微信接口处理生成 Base64 形式二维码
     * @param scene 二维码信息
     * @return Base64 形式二维码
     */
    @PostMapping("/getShareImgCode")
    public Result<String> getShareImgCode(String page, String scene) {
        return RestOperateTemplate.operate(LOGGER, "生成 Base64 形式二维码", scene, new RestOperateCallBack<String>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(page, "缺少参数 page");
                AssertUtil.assertNotNull(scene, "缺少参数 scene");
                AssertUtil.assertStringNotBlank(scene, "scene参数长度不能为零");
            }
    
            @Override
            public Result<String> execute() throws IOException {
                String accessToken = AccessTokenManage.GetToken();
                String url = SHARE_IMG_URL_TEMPLATE + accessToken;
                Map<String, Object> param = new HashMap<>(1);
                param.put("page", page);
                param.put("scene", scene);
                param.put("is_hyaline", false);
                // 判断 accessToken 是否过期
                String result = HttpUtils.doPostUseJson(url, param.toString());
                JSONObject resultJson = JSONObject.parseObject(result);
                String resCode = resultJson.getString(ERROR_CODE);
                if (StringUtils.equals(resCode, "42001")){
                    //防止token被连续刷新多次
                    lock.lock();
                    //第一个知道令牌过期的调用 刷新令牌
                    if (StringUtils.equals(accessToken, AccessTokenManage.GetToken())) {
                        accessToken = AccessTokenManage.refreshToken();
                        lock.unlock();
                    }else {
                        //重新获取新的令牌
                        accessToken=AccessTokenManage.GetToken();
                        lock.unlock();
                    }
                }
                String code = WeChatShareImgUtil.getWXCode(accessToken, param);
                String[] parts = code.split(",");
                if (parts[1].length() == 0) {
                    throw new BetahouseException(CommonResultCode.SYSTEM_ERROR.getCode(), CommonResultCode.SYSTEM_ERROR.getMessage());
                }
                return RestResultUtil.buildSuccessResult(code, "Base64 形式二维码生成成功");
            }
        });
    }

    @GetMapping("/term")
    public Result<List<String>> getTermInfo() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        int nowYear = cal.get(Calendar.YEAR);
        int startYear = 2017;
        List<String> termList = new ArrayList<>();
        // 开始学期到目前学期
        for (; startYear < nowYear; startYear++) {
            termList.add(startYear + "A");
            termList.add(startYear + "B");
        }
        termList.add(nowYear + "A");
        if (month > 7) {
            termList.add(nowYear + "B");
        }
        return RestResultUtil.buildSuccessResult(termList);
    }
}
