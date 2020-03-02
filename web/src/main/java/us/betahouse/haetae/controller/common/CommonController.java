/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.controller.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.controller.user.UserController;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.aliyun.AliyunOssUtil;
import us.betahouse.util.common.Result;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
     * 日志
     */
    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    /**
     * 上传文件到aliyun云存储
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
                String path =aliyunOssUtil.uploadFile(fileName, "/haetae/", inputStream);
                Map map=new HashMap(4);
                map.put("path", path);
                return RestResultUtil.buildSuccessResult(map, "上传成功");
            }
        });
    }
}
