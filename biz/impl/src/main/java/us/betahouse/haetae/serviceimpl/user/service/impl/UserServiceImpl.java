/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.user.service.impl;

import cn.hutool.poi.excel.ExcelReader;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import us.betahouse.haetae.serviceimpl.activity.constant.ActivityPermType;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.common.constant.UserRequestExtInfoKey;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyPerm;
import us.betahouse.haetae.serviceimpl.user.constant.GeneralPermType;
import us.betahouse.haetae.serviceimpl.user.constant.UserPermType;
import us.betahouse.haetae.serviceimpl.user.enums.GeneralManagerPermTypeEnum;
import us.betahouse.haetae.serviceimpl.user.enums.UserRoleCode;
import us.betahouse.haetae.serviceimpl.user.request.CommonUserRequest;
import us.betahouse.haetae.serviceimpl.user.request.UploadUserExcelRequest;
import us.betahouse.haetae.serviceimpl.user.routingtable.UserRoutingTable;
import us.betahouse.haetae.serviceimpl.user.service.UserService;
import us.betahouse.haetae.user.dal.service.PermRepoService;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.dal.service.UserRepoService;
import us.betahouse.haetae.user.enums.RoleCode;
import us.betahouse.haetae.user.enums.UserErrorCode;
import us.betahouse.haetae.user.manager.UserManager;
import us.betahouse.haetae.user.model.CommonUser;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.haetae.user.model.basic.perm.PermBO;
import us.betahouse.haetae.user.model.basic.perm.RoleBO;
import us.betahouse.haetae.user.model.basic.perm.UserBO;
import us.betahouse.haetae.user.request.UserManageRequest;
import us.betahouse.haetae.user.user.service.UserBasicService;
import us.betahouse.haetae.user.utils.EncryptUtil;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.template.ExcelTemplate;
import us.betahouse.util.utils.*;
import us.betahouse.util.validator.MultiValidator;
import us.betahouse.util.wechat.WeChatLoginUtil;
import us.betahouse.util.yiban.YibanUtil;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户服务实现
 *
 * @author dango.yxm
 * @version : UserServiceImpl.java 2018/11/21 6:39 PM dango.yxm
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${wechat.appId}")
    private String APP_ID;

    @Value("${wechat.secret}")
    private String SECRET;

    @Autowired
    private UserRepoService userRepoService;

    @Autowired
    private UserInfoRepoService userInfoRepoService;

    @Autowired
    private UserManager userManager;

    @Autowired
    private UserBasicService userBasicService;

    @Autowired
    private YibanUtil yibanUtil;

    @Autowired
    private PermRepoService permRepoService;

    /**
     * 密码规则校验器
     */
    @Autowired
    private MultiValidator<UserManageRequest> passwordValidator;


    @Override
    public CommonUser register(CommonUserRequest request, OperateContext context) {
        return userManager.create(request);
    }


    @Override
    public CommonUser login(CommonUserRequest request, OperateContext context) {
        // 获取openId
        String openId = null;
        if (StringUtils.isNotBlank(request.getCode())) {
            openId = WeChatLoginUtil.fetchOpenId(request.getCode(), APP_ID, SECRET);
        }
        return userBasicService.login(request.getUsername(), request.getPassword(), request.getAvatarUrl(), openId, context.getOperateIP());
    }

    @Override
    public CommonUser yiLogin(CommonUserRequest request, OperateContext context) {
        String yiStuId=yibanUtil.getStuId(yibanUtil.getAccessToken(request.getCode()));
        AssertUtil.assertNotNull(yiStuId, UserErrorCode.USER_NOT_EXIST);
        CommonUser commonUser=userBasicService.getByStuId(yiStuId);
        AssertUtil.assertNotNull(commonUser, UserErrorCode.USER_NOT_EXIST);
        return userBasicService.setToken(commonUser);
    }

    @Override
    public CommonUser fetchUser(CommonUserRequest request, OperateContext context) {
        return userBasicService.getByUserId(request.getUserId());
    }

    @Override
    public void logout(CommonUserRequest request, OperateContext context) {
        userBasicService.loginOut(request.getUserId());
    }

    @Override
    public void wxLogout(CommonUserRequest request, OperateContext context) {
        userBasicService.wxLoginOut(request.getUserId());
    }

    @Override
    public Map<String, PermBO> fetchUserPerms(CommonUserRequest request, OperateContext context) {
        return userBasicService.fetchUserPerms(request.getUserId());
    }

    @Override
    public Map<String, RoleBO> fetchUserRoles(CommonUserRequest request, OperateContext context) {
        return userBasicService.fetchUserRoles(request.getUserId());
    }

    @Override
    public void modifyUser(CommonUserRequest request, OperateContext context) {

        UserBO userBO = userRepoService.queryByUserId(request.getUserId());
        AssertUtil.assertNotNull(userBO, "用户不存在");

        // 传入了新密码 就认为是要修改密码
        if (StringUtils.isNotBlank(request.fetchExtInfo(UserRequestExtInfoKey.USER_NEW_PASSWORD))) {
            // 传入了老密码就认为是要 校验老密码
            if (StringUtils.isNotBlank(request.getPassword())) {
                String oldEncodePwd = EncryptUtil.encryptPassword(request.getPassword(), userBO.getSalt());
                AssertUtil.assertTrue(StringUtils.equals(userBO.getPassword(), oldEncodePwd), "旧密码错误");
            }

            // 将新密码组装入管理请求
            request.setPassword(request.fetchExtInfo(UserRequestExtInfoKey.USER_NEW_PASSWORD));
            // 校验新密码是否满足 密码规则
            passwordValidator.validate(request);

            userBO.setSalt(UUID.randomUUID().toString());
            userBO.setPassword(EncryptUtil.encryptPassword(request.getPassword(), userBO.getSalt()));
        }
        userRepoService.updateUserByUserId(userBO);
    }

    @Override
    @VerifyPerm(permType = UserPermType.USER_PASSWORD_RESET)
    public void modifyPwdByStuId(CommonUserRequest request, OperateContext context) {

        UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByStuId(request.getStuId());
        AssertUtil.assertNotNull(userInfoBO, "学号不存在");
        UserBO userBO = userRepoService.queryByUserId(userInfoBO.getUserId());
        AssertUtil.assertNotNull(userBO, "用户不存在");

        // 将新密码组装入管理请求
        request.setPassword(request.fetchExtInfo(UserRequestExtInfoKey.USER_NEW_PASSWORD));
        // 校验新密码是否满足 密码规则
        passwordValidator.validate(request);

        userBO.setSalt(UUID.randomUUID().toString());
        userBO.setPassword(EncryptUtil.encryptPassword(request.getPassword(), userBO.getSalt()));

        userRepoService.updateUserByUserId(userBO);
    }

    @Override
    public void modifyUserMajorAndClassAndGrade(CommonUserRequest request, OperateContext context) {
        userBasicService.modifyMajorAndClassAndGrade(request.getUserId(), request.getUserInfoBO().getMajor(), request.getUserInfoBO().getClassId(), request.getUserInfoBO().getGrade());
    }

    @Override
    public UserBO queryByUserId(String userId, OperateContext context) {
      return userRepoService.queryByUserId(userId);
    }

    @Override
    public CommonUser queryCommonByUserId(String userId, OperateContext context) {
        CommonUser commonUser = userBasicService.getByUserId(userId);
        commonUser.setAvatarUrl(queryByUserId(userId,context).getAvatarUrl());
       return commonUser;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
//    @VerifyPerm(permType = )
    public List<String> saveUserByExcel(UploadUserExcelRequest request, MultipartFile file, OperateContext context) {
        int index=file.getOriginalFilename().indexOf(".");
        String fileType=file.getOriginalFilename().substring(index+1);
        Workbook workbook;
        List<String> titles=new ArrayList<>();
        try {
            if(fileType.equalsIgnoreCase("xls")){
                workbook=new HSSFWorkbook(file.getInputStream());
            }else if(fileType.equalsIgnoreCase("xlsx")){
                workbook=new XSSFWorkbook(file.getInputStream());
            }else {
                throw new BetahouseException(CommonResultCode.SYSTEM_ERROR,"文件格式错误");
            }
        } catch (IOException e) {
            throw new BetahouseException(CommonResultCode.SYSTEM_ERROR,"读取文件失败");
        }
        List<ExcelTemplate> excelTemplates = ExcelUtil.parseExcel(workbook);

        for (ExcelTemplate excelTemplate : excelTemplates) {
            Map<String, List<String>> data = excelTemplate.getDate();
            for (int i = 0; i < excelTemplate.getRowNum(); i++) {
                List<String> list;
                CommonUserRequest commonUserRequest=new CommonUserRequest();
                UserInfoBO userInfoBO=new UserInfoBO();
                if(data.get("姓名")!=null){
                    list=data.get("姓名");
                    userInfoBO.setRealName(list.get(i));
                }
                if(data.get("学号")!=null){
                    list=data.get("学号");
                    userInfoBO.setStuId(list.get(i));
                    commonUserRequest.setStuId(list.get(i));
                    commonUserRequest.setUsername(list.get(i));
                }
                if(data.get("初始密码")!=null){
                    list=data.get("初始密码");
                    commonUserRequest.setPassword(list.get(i));
                }
                if(data.get("性别")!=null){
                    list=data.get("性别");
                    userInfoBO.setSex(list.get(i));
                }
                if(data.get("专业")!=null){
                    list=data.get("专业");
                    userInfoBO.setMajor(list.get(i));
                }
                if(data.get("年级")!=null){
                    list=data.get("年级");
                    userInfoBO.setGrade(list.get(i));
                }
                if(data.get("班级")!=null){
                    list=data.get("班级");
                    userInfoBO.setClassId(list.get(i));
                }
                commonUserRequest.setSalt(UUID.randomUUID().toString());
                userInfoBO.setEnrollDate(new Date());
                commonUserRequest.setUserInfoBO(userInfoBO);
                userManager.create(commonUserRequest);
            }
            if(StringUtils.isNotBlank(excelTemplate.getTitle())){
                titles.add(excelTemplate.getTitle());
            }
        }
        return titles;
    }

    /**
     * 获取路由表
     * @param userId
     * @return
     */
    @Override
    public List<UserRoutingTable> getRoutingTable(String userId) {
        List<UserRoutingTable> userRoutingTable=new ArrayList<>();
        Map<String, PermBO> permBOMap = userBasicService.fetchUserPerms(userId);
        List<String> permTypes = CollectionUtils.toStream(permBOMap.values()).filter(Objects::nonNull).map(PermBO::getPermType).collect(Collectors.toList());

        //默认能登录的都是有Activity_Manage角色的人，都有活动的查询与创建权限，此方法未验证账户是否存在
        List<UserRoutingTable> activityList=new ArrayList<>();
        UserRoutingTable inquiry=new UserRoutingTable("/inquiry","活动查询与创建","inquiry",false,null);
        activityList.add(inquiry);

        List<UserRoutingTable> chapterList=new ArrayList<>();
        if(permTypes.contains(ActivityPermType.STAMPER_MANAGE)){
            UserRoutingTable importTable=new UserRoutingTable("/import","导入章","importChapter",false,null);
            chapterList.add(importTable);
        }
        if(permTypes.contains(GeneralPermType.PERM_OPERATOR)){
            //预警与总览
            UserRoutingTable overview=new UserRoutingTable("/overview","预警与总览","overview",false,null);
            userRoutingTable.add(overview);

            //管理员额外活动模块
            UserRoutingTable approval=new UserRoutingTable("/approval","活动审批","approval",false,null);
            activityList.add(approval);
            UserRoutingTable approveDetail=new UserRoutingTable("/approvedetail","活动审批详情","approvedetail",false,null);
            activityList.add(approveDetail);
            UserRoutingTable authority=new UserRoutingTable("/authority","权限分配","authority",false,null);
            activityList.add(authority);

            //管理员额外活动章模块
            UserRoutingTable manage=new UserRoutingTable("/manage","导入/导出章","manageChapter",false,null);
            chapterList.add(manage);

            //管理员教务模块
            List<UserRoutingTable> officeList=new ArrayList<>();
            UserRoutingTable infoEntry=new UserRoutingTable("/infoentry","新生信息录入","infoEntry",false,null);
            officeList.add(infoEntry);
            UserRoutingTable office=new UserRoutingTable("/office","教务模块",null,true,officeList);
            userRoutingTable.add(office);
        }
        //放入活动模块
        UserRoutingTable activity=new UserRoutingTable("/activity","活动模块",null,true,activityList);
        userRoutingTable.add(activity);
        if(!chapterList.isEmpty()){
            //放入活动章模块
            UserRoutingTable chapter=new UserRoutingTable("/chapter","活动章模块",null,true,chapterList);
            userRoutingTable.add(chapter);
        }
        return userRoutingTable;
    }

    @Override
    public CommonUser loginProxy(CommonUserRequest request, OperateContext context){
        CommonUser commonUser = login(request, context);
        List<RoleCode> list=new ArrayList(2);
        list.add(UserRoleCode.ACTIVITY_MANAGER);
        list.add(UserRoleCode.GENERAL_MANAGER);
        boolean b = userBasicService.verifyPermissionByRoleCode(commonUser.getUserId(), list);
        AssertUtil.assertTrue(b,CommonResultCode.FORBIDDEN,"没有权限，您无法登录");
        return commonUser;
    }

    @Override
//    @VerifyPerm(permType = {GeneralPermType.PERM_OPERATOR})
    public void giveStamperPerm(CommonUserRequest request, OperateContext context) {
        PermBO permBO = permRepoService.queryPermByPermType(ActivityPermType.STAMPER_MANAGE);
        AssertUtil.assertNotNull(permBO,CommonResultCode.SYSTEM_ERROR.getCode(),"查无此权限");
        UserManageRequest userManageRequest=new UserManageRequest();
        userManageRequest.setUserId(request.getUserId());
        userManageRequest.setPermIds(Collections.singletonList(permBO.getPermId()));
        userManager.batchBindPerm(userManageRequest);
    }

    @Override
    public CommonUser findByStuid(String stuid) {
        return userBasicService.getByStuId(stuid);
    }

}
