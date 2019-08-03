package us.betahouse.haetae.serviceimpl.activity.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.activity.builder.ActivityEntryBOBuilder;
import us.betahouse.haetae.activity.builder.ActivityEntryRecordBOBuilder;
import us.betahouse.haetae.activity.dal.service.ActivityEntryRecordRepoService;
import us.betahouse.haetae.activity.dal.service.ActivityEntryRepoService;
import us.betahouse.haetae.activity.dal.service.ActivityRepoService;
import us.betahouse.haetae.activity.enums.ActivityEntryStateEnum;
import us.betahouse.haetae.activity.enums.ActivityTypeEnum;
import us.betahouse.haetae.activity.model.basic.ActivityBO;
import us.betahouse.haetae.activity.model.basic.ActivityEntryBO;
import us.betahouse.haetae.activity.model.basic.ActivityEntryRecordBO;
import us.betahouse.haetae.activity.model.common.PageList;
import us.betahouse.haetae.activity.request.ActivityEntryRecordRequest;
import us.betahouse.haetae.activity.request.ActivityEntryRequest;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityEntry;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityEntryList;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityEntryService;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.util.utils.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * 活动报名信息服务
 *
 * @author zjb
 * @version : ActivityEntryServiceImpl.java 2019/7/7 17:05 zjb
 */
@Service
public class ActivityEntryServiceImpl implements ActivityEntryService {

    /**
     * 系统结束标志
     */
    private final static String SYSTEM_FINISH_SIGN = "systemFinish";

    @Autowired
    private ActivityEntryRepoService activityEntryRepoService;

    @Autowired
    private ActivityRepoService activityRepoService;

    @Autowired
    private ActivityEntryRecordRepoService activityEntryRecordRepoService;

    @Autowired
    private UserInfoRepoService userInfoRepoService;


    /**
     * 通过活动id查找报名信息
     *
     * @param activityId
     * @return
     */
    public ActivityEntryList activityEntryQueryByActivityId(String activityId){
        List<ActivityEntryBO> activityEntryBOList = activityEntryRepoService.findAllByActivityId(activityId);
        AssertUtil.assertTrue( !activityEntryBOList.isEmpty(),"无报名信息");
        return new ActivityEntryList(Long.parseLong(""+activityEntryBOList.size()),
                1,
                activityEntryBOList.size(),
                1,
                activityEntryBOList.size(),
                true,
                true,
                activityEntryBOList);
    }

//    /**
//     * 通过报名信息id查找报名记录,返回Excel文件地址
//     *
//     * @param activityEntryId
//     * @return
//     */
//    @Override
//    public String getActivityEntryRecordFileByActivityEntryId(String activityEntryId) {
//        List<ActivityEntryRecordBO> activityEntryRecordBOList = activityEntryRecordRepoService.findAllByActivityEntryId(activityEntryId);
//        AssertUtil.assertTrue( !activityEntryRecordBOList.isEmpty(),"无报名记录");
//
//        String title = activityEntryRepoService.findByActivityEntryId(activityEntryId).getTitle();
//        List<UserInfoBO> userInfoBOList = new ArrayList<>();
//        for (ActivityEntryRecordBO activityEntryRecordBO:activityEntryRecordBOList) {
//            UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByStuId(activityEntryRecordBO.getUserId());
//            if(userInfoBO == null) {
//                UserInfoBO newuserInfoBO  = new UserInfoBO();
//                newuserInfoBO.setStuId(activityEntryRecordBO.getUserId());
//                userInfoBOList.add(newuserInfoBO);
//            }else {
//                userInfoBOList.add(userInfoBO);
//            }
//        }
//
//        return ExcelUtil.list2ExcelFile(userInfoBOList,null,title);
//    }

    /**
     * 通过报名信息id查找报名记录,返回Excel文件地址
     *
     * @param activityEntryId
     * @return
     */
    @Override
    public List<UserInfoBO> getActivityEntryRecordUserInfoFileByActivityEntryId(String activityEntryId) {
        List<ActivityEntryRecordBO> activityEntryRecordBOList = activityEntryRecordRepoService.findAllByActivityEntryId(activityEntryId);
        AssertUtil.assertTrue( !activityEntryRecordBOList.isEmpty(),"无报名记录");

        List<UserInfoBO> userInfoBOList = new ArrayList<>();
        for (ActivityEntryRecordBO activityEntryRecordBO:activityEntryRecordBOList) {
            UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByUserId(activityEntryRecordBO.getUserId());
            if(userInfoBO == null) {
                UserInfoBO newuserInfoBO  = new UserInfoBO();
                newuserInfoBO.setStuId(activityEntryRecordBO.getUserId());
                userInfoBOList.add(newuserInfoBO);
            }else {
                userInfoBOList.add(userInfoBO);
            }
        }
        return userInfoBOList;
    }


    /**
     * 获取报名信息title
     * @param activityEntryId
     * @return
     */
    @Override
    public String getActivityEntryTitle(String activityEntryId) {
        return activityEntryRepoService.findByActivityEntryId(activityEntryId).getTitle();
    }

    /**
     * 获取报名信息
     * @param request;
     * @param userID;
     * @return ActivityEntryList;
     */
    @Override
    public ActivityEntryList activityEntryQuery(ActivityEntryRequest request, String userID){

        //默认值 学期不限 状态不限 类型不限 第0页 每页五条
        String term="";
        String state="";
        String type="";
        Integer page=0;
        Integer limit=5;

        if(StringUtils.isNotBlank(request.getTerm())){
            term=request.getTerm();
        }
        if(StringUtils.isNotBlank(request.getState())){
            AssertUtil.assertNotNull(ActivityEntryStateEnum.getByCode(request.getState()), "报名状态不存在");
            state=request.getState();
        }
        if(StringUtils.isNotBlank(request.getType())){
            ActivityTypeEnum typeEnum=ActivityTypeEnum.getByCode(request.getType());
            AssertUtil.assertNotNull(typeEnum, "活动类型不存在");
            type=request.getType();
        }
        if(NumberUtils.isNotBlank(request.getPage())){
            page=request.getPage();
        }
        if(NumberUtils.isNotBlank(request.getLimit())){
            limit=request.getLimit();
        }

        List<ActivityEntry> activityEntryList = new ArrayList<>();
        PageList<ActivityEntryBO> activityEntryBOPageList =
                activityEntryRepoService.queryActivityEntryByTermAndStateAndTypePagerASC(term, state, type, page, limit);
//        return new PageList<ActivityEntry>(activityEntryBOPageList,this::convert);

        for (ActivityEntryBO activityEntryBO : activityEntryBOPageList.getContent()) {
            long seconds = (activityEntryBO.getStart().getTime() - new Date().getTime())/1000;
            ActivityBO activityBO = activityRepoService.queryActivityByActivityId(activityEntryBO.getActivityId());

            String status;
            //状态转换
            {
                status = "0";
                if (new Date().before(activityEntryBO.getStart())) {
                    if (0 < seconds && seconds < 3600) status = "1";         //倒计时
                } else if (DateUtil.nowIsBetween(activityEntryBO.getStart(), activityEntryBO.getEnd())) {
                    status = "2";             //报名中
                }

                if (new Date().after(activityEntryBO.getStart())) {
                    ActivityEntryRecordBO activityEntryRecordBO = activityEntryRecordRepoService.findByActivityEntryIdAndUserId(activityEntryBO.getActivityEntryId(), userID);
                    if (activityEntryRecordBO != null) {
                        status = "3";    //已报名
                    } else if (activityEntryBO.getNumber() <= activityEntryRecordRepoService.countByActivityEntryId(activityEntryBO.getActivityEntryId())) {
                        status = "4"; //人已满
                    } else {
                        status = "2";  //报名中
                    }
                }
                if (new Date().after(activityEntryBO.getEnd())) {
                    status = "5";            //已结束
                    if (!ActivityEntryStateEnum.FINISHED.getCode().equals(activityEntryBO.getState()) &&
                            !ActivityEntryStateEnum.CANCELED.getCode().equals(activityEntryBO.getState())) {
                        activityEntryBO.setState(ActivityEntryStateEnum.FINISHED.getCode());
                        activityEntryRepoService.updateActivityEntryByActivityEntryId(activityEntryBO);
                    } else if (ActivityEntryStateEnum.CANCELED.getCode().equals(activityEntryBO.getState())) {
                        status = "6";            //已取消
                    }
                }
            }


            ActivityEntry activityEntry = new ActivityEntry();
            activityEntry.setActivityId(activityEntryBO.getActivityId());
            activityEntry.setActivityName(activityBO.getActivityName());
            activityEntry.setActivityType(activityEntryBO.getType());
            activityEntry.setDescription(activityBO.getDescription());
            activityEntry.setLocation(activityBO.getLocation());
            activityEntry.setSecond(seconds);
            activityEntry.setStart(DateUtil.format(activityEntryBO.getStart(), "yyyy年MM月dd日 HH:mm:ss"));
            activityEntry.setStatus(status);
            activityEntryList.add(activityEntry);
        }


        return new ActivityEntryList(activityEntryBOPageList.getTotalElements(),
                activityEntryBOPageList.getTotalPages(),
                activityEntryBOPageList.getSize(),
                activityEntryBOPageList.getNumber(),
                activityEntryBOPageList.getNumberOfElements(),
                activityEntryBOPageList.isFirst(),
                activityEntryBOPageList.isEnd(),
                activityEntryList);
    }



//    private Object convert(Object o) {
//        if(o instanceof ActivityEntryBO){
//            return convert((ActivityEntryBO)o);
//        }else{
//            return null;
//        }
//    }
//
//    private ActivityEntry convert(ActivityEntryBO activityEntryBO) {
//        if (activityEntryBO == null) {
//            return null;
//        }
//        long seconds =0;
//        String status = "0";
//        ActivityBO activityBO = activityRepoService.queryActivityByActivityId(activityEntryBO.getActivityId());
//
//
//        if( new Date().before(activityEntryBO.getStart())){
//            seconds = new Date().getTime() - activityEntryBO.getStart().getTime();
//            if(0 < seconds && seconds <3600000) status = "1";         //倒计时
//        }else if( DateUtil.nowIsBetween(activityEntryBO.getStart(),activityEntryBO.getEnd() )){
//            status = "2";             //报名中
//            ActivityEntryRecordBO activityEntryRecordBO =activityEntryRecordRepoService.findByActivityEntryIdAndUserId(activityEntryBO.getActivityEntryId(),userID);
//            if(activityEntryRecordBO != null)  status = "3";          //已报名
//            if(activityEntryBO.getNumber() <= activityEntryRecordRepoService.countByActivityEntryId(activityEntryBO.getActivityEntryId())) status = "4"; //人已满
//        }else if(activityEntryBO.getState() == ActivityEntryStateEnum.FINISHED.getCode()){
//            status = "5";            //已过期
//        }else if(activityEntryBO.getState() == ActivityEntryStateEnum.CANCELED.getCode()){
//            status = "6";            //已取消
//        }
//
//        ActivityEntry activityEntry = new ActivityEntry();
//        activityEntry.setActivityId(activityEntryBO.getActivityId());
//        activityEntry.setActivityName(activityBO.getActivityName());
//        activityEntry.setDescription(activityBO.getDescription());
//        activityEntry.setLocation(activityBO.getLocation());
//        activityEntry.setSecond(seconds/1000);
//        activityEntry.setStart(DateUtil.format(activityEntryBO.getStart(),"yyyy年mm月dd日 hh时mm分ss秒"));
//        activityEntry.setStatus(status);
//
//        return activityEntry;
//    }


    /**
     * 获取已报名状态的报名信息
     * @param request;
     * @param userID;
     * @return ActivityEntryList;
     */
    @Override
    public ActivityEntryList registeredActivityEntryQuery(ActivityEntryRequest request, String userID) {
        //默认值 学期不限  类型不限
        String term="";
        String type="";
        if(StringUtils.isNotBlank(request.getTerm())){
            term=request.getTerm();
        }
        if(StringUtils.isNotBlank(request.getState())){
                AssertUtil.assertTrue("REGISTERED".equals(request.getState()),"活动状态异常");
        }
        if(StringUtils.isNotBlank(request.getType())){
            ActivityTypeEnum typeEnum=ActivityTypeEnum.getByCode(request.getType());
            AssertUtil.assertNotNull(typeEnum, "活动类型不存在");
            type=request.getType();
        }



        List<ActivityEntry> activityEntryList = new ArrayList<>();
        List<ActivityEntryRecordBO> activityEntryRecordBOList = activityEntryRecordRepoService.findAllByUserId(userID);

        for(ActivityEntryRecordBO activityEntryRecordBO :activityEntryRecordBOList){



            ActivityEntryBO activityEntryBO = activityEntryRepoService.findByActivityEntryId(activityEntryRecordBO.getActivityEntryId());
            ActivityBO activityBO = activityRepoService.queryActivityByActivityId(activityEntryBO.getActivityId());
            long seconds = (activityEntryBO.getStart().getTime() - new Date().getTime())/1000;

            String status = "3";  //默认已报名
            if (ActivityEntryStateEnum.FINISHED.getCode().equals(activityEntryBO.getState())) {
                status = "5";            //已结束
            } else if (ActivityEntryStateEnum.CANCELED.getCode().equals(activityEntryBO.getState())) {
                status = "6";            //已取消
            }


            ActivityEntry activityEntry = new ActivityEntry();
            activityEntry.setActivityId(activityEntryBO.getActivityId());
            activityEntry.setActivityName(activityBO.getActivityName());
            activityEntry.setActivityType(activityEntryBO.getType());
            activityEntry.setDescription(activityBO.getDescription());
            activityEntry.setLocation(activityBO.getLocation());
            activityEntry.setSecond(seconds);
            activityEntry.setStart(DateUtil.format(activityEntryBO.getStart(), "yyyy年MM月dd日 HH:mm:ss"));
            activityEntry.setStatus(status);
            activityEntryList.add(activityEntry);
        }

        final String activityType = type;
        final String activityTerm = term;
        List<ActivityEntry> newList =  CollectionUtils.toStream(activityEntryList)
                .filter((ActivityEntry a)->a.typeAndStatusAndTermFilter(activityType,null,activityTerm))
                .collect(Collectors.toList());
        Long totalElements = Long.parseLong(""+newList.size());
        //为了一致，假分页数据
        return new ActivityEntryList(totalElements,1,Integer.valueOf(""+totalElements),
                0,Integer.valueOf(""+totalElements) ,true,true,newList);
    }

    /**
     * 创建报名
     *
     * @param request
     * @return
     */
    @Override
    public ActivityEntryBO createActivityEntry(ActivityEntryRequest request) {
        if (StringUtils.isBlank(request.getTitle() )) {
            request.setTitle(activityRepoService.queryActivityByActivityId(request.getActivityId()).getActivityName());
        }
        if (request.getStart() == null) {
            request.setStart(0L);
        }
        if (request.getEnd() == null) {
            request.setEnd(0L);
        }
        ActivityEntryBOBuilder builder = ActivityEntryBOBuilder.getInstance()
                .withActivityEntryId(request.getActivityEntryId())
                .withActivityId(request.getActivityId())
                .withType(activityRepoService.queryActivityByActivityId(request.getActivityId()).getType())
                .withTerm(activityRepoService.queryActivityByActivityId(request.getActivityId()).getTerm())
                .withState(ActivityEntryStateEnum.APPROVED.getCode())
                .withTitle(request.getTitle())
                .withNumber(request.getNumber())
                .withLinkman(request.getLinkman())
                .withContact(request.getContact())
                .withStart(new Date(request.getStart()))
                .withEnd(new Date(request.getEnd()))
                .withChoose(request.getChoose())
                .withTop(request.getTop())
                .withNote(request.getNote())
                .withExtInfo(request.getExtInfo());
        return activityEntryRepoService.createActivityEntry(builder.build());
    }

    /**
     * 更新报名信息
     * @param request
     * @return
     */
    @Override
    public ActivityEntryBO updateActivityEntry(ActivityEntryRequest request) {
        ActivityEntryBO activityEntryBO = activityEntryRepoService.findByActivityEntryId(request.getActivityEntryId());
        AssertUtil.assertNotNull(activityEntryBO, "报名信息不存在");
        //发布报名  发布的同时不能修改报名信息
        if(activityEntryBO.getState() == ActivityEntryStateEnum.APPROVED.getCode()||request.getState() == ActivityEntryStateEnum.PUBLISHED.getCode()){
            activityEntryBO.setState(ActivityEntryStateEnum.PUBLISHED.getCode());
            return activityEntryRepoService.updateActivityEntryByActivityEntryId(activityEntryBO);
        }

        //只有发布前可以修改报名信息
        AssertUtil.assertTrue((activityEntryBO.getState() == ActivityEntryStateEnum.APPROVED.getCode()),"当前状态不允许更新报名信息");
        if(StringUtils.isNotBlank(request.getState())){
            AssertUtil.assertNotNull(ActivityEntryStateEnum.getByCode(request.getState()), "报名状态不存在");
        }
        ActivityEntryBOBuilder builder = ActivityEntryBOBuilder.getInstance()
                .withActivityEntryId(request.getActivityEntryId())
                .withState(request.getState())
                .withTitle(request.getTitle())
                .withNumber(request.getNumber())
                .withLinkman(request.getLinkman())
                .withContact(request.getContact())
                .withStart(new Date(request.getStart()))
                .withEnd(new Date(request.getEnd()))
                .withChoose(request.getChoose())
                .withTop(request.getTop())
                .withNote(request.getNote())
                .withExtInfo(request.getExtInfo());
        return activityEntryRepoService.updateActivityEntryByActivityEntryId(builder.build());
    }

    /**
     * 活动报名
     *
     * @param request
     * @return
     */
    @Override
    public synchronized ActivityEntryRecordBO createActivityEntryRecord(ActivityEntryRecordRequest request) {
        ActivityEntryBO activityEntryBO = activityEntryRepoService.findByActivityEntryId(request.getActivityEntryId());
        AssertUtil.assertNotNull(activityEntryBO,"报名信息id不存在");
        if (activityEntryBO.getNumber() <= activityEntryRecordRepoService.countByActivityEntryId(request.getActivityEntryId())) {
            return null;
        }else{
            ActivityEntryRecordBOBuilder builder = ActivityEntryRecordBOBuilder.getInstance()
                    .withActivityEntryRecordId(request.getActivityEntryRecordId())
                    .withActivityEntryId(request.getActivityEntryId())
                    .withUserId(request.getUserId())
                    .withAttend(request.getAttend())
                    .withNote(request.getNote())
                    .withChoose(request.getChoose())
                    .withExtInfo(request.getExtInfo());
            return activityEntryRecordRepoService.createActivityEntryRecord(builder.build());
        }
    }


    /**
     * 结束可以结束的活动
     *
     * @return
     */
    @Override
    public List<ActivityEntryBO> systemFinishActivityEntry() {
        List<ActivityEntryBO> activityEntryBOList = activityEntryRepoService.findAllByState(ActivityEntryStateEnum.PUBLISHED.getCode());
        List<ActivityEntryBO> systemFinishActivityEntries = new ArrayList<>();
        for (ActivityEntryBO activityEntryBO : activityEntryBOList) {
            if (activityEntryBO.canFinish()) {
                activityEntryBO.setState(ActivityEntryStateEnum.FINISHED.getCode());
                activityEntryBO.putExtInfo(SYSTEM_FINISH_SIGN, SYSTEM_FINISH_SIGN);
                systemFinishActivityEntries.add(activityEntryRepoService.updateActivityEntryByActivityEntryId(activityEntryBO));
            }
        }
        return systemFinishActivityEntries;
    }


}
