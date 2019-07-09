package us.betahouse.haetae.serviceimpl.activity.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.activity.dal.service.ActivityEntryRecordRepoService;
import us.betahouse.haetae.activity.dal.service.ActivityEntryRepoService;
import us.betahouse.haetae.activity.dal.service.ActivityRepoService;
import us.betahouse.haetae.activity.enums.ActivityEntryStateEnum;
import us.betahouse.haetae.activity.enums.ActivityTypeEnum;
import us.betahouse.haetae.activity.model.basic.ActivityBO;
import us.betahouse.haetae.activity.model.basic.ActivityEntryBO;
import us.betahouse.haetae.activity.model.basic.ActivityEntryRecordBO;
import us.betahouse.haetae.activity.model.common.PageList;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityEntry;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityEntryList;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityEntryQueryRequest;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityEntryService;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;
import us.betahouse.util.utils.DateUtil;
import us.betahouse.util.utils.NumberUtils;

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

    @Autowired
    private ActivityEntryRepoService activityEntryRepoService;

    @Autowired
    private ActivityRepoService activityRepoService;

    @Autowired
    private ActivityEntryRecordRepoService activityEntryRecordRepoService;


    /**
     * 获取报名信息
     * @param request
     * @param userID
     * @return
     */
    @Override
    public ActivityEntryList activityEntryQuery(ActivityEntryQueryRequest request, String userID){

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
            ActivityEntryStateEnum aese = ActivityEntryStateEnum.getByCode(request.getState());
            AssertUtil.assertNotNull(aese, "报名状态不存在");
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
            long seconds = activityEntryBO.getStart().getTime() - new Date().getTime();
            ActivityBO activityBO = activityRepoService.queryActivityByActivityId(activityEntryBO.getActivityId());

            String status = "";
            //状态转换
            {
                status = "0";
                if (new Date().before(activityEntryBO.getStart())) {
                    if (0 < seconds && seconds < 3600000) status = "1";         //倒计时
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
                        status = "5";  //已结束(报名结束，活动未结束)
                    }
                }

                if (ActivityEntryStateEnum.FINISHED.getCode().equals(activityEntryBO.getState())) {
                    status = "6";            //已过期(活动过期)
                } else if (ActivityEntryStateEnum.CANCELED.getCode().equals(activityEntryBO.getState())) {
                    status = "7";            //已取消
                }
            }


            ActivityEntry activityEntry = new ActivityEntry();
            activityEntry.setActivityId(activityEntryBO.getActivityId());
            activityEntry.setActivityName(activityBO.getActivityName());
            activityEntry.setActivityType(activityEntryBO.getType());
            activityEntry.setDescription(activityBO.getDescription());
            activityEntry.setLocation(activityBO.getLocation());
            activityEntry.setSecond(seconds / 1000);
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
     * @param request
     * @param userID
     * @return
     */
    @Override
    public ActivityEntryList registeredActivityEntryQuery(ActivityEntryQueryRequest request, String userID) {
        //默认值 学期不限 状态不限 类型不限 第0页 每页十条 逆序
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


        String status = "";
        List<ActivityEntry> activityEntryList = new ArrayList<>();
        List<ActivityEntryRecordBO> activityEntryRecordBOList = activityEntryRecordRepoService.findAllByUserId(userID);

        for(ActivityEntryRecordBO activityEntryRecordBO :activityEntryRecordBOList){



            ActivityEntryBO activityEntryBO = activityEntryRepoService.findByActivityEntryId(activityEntryRecordBO.getActivityEntryId());
            ActivityBO activityBO = activityRepoService.queryActivityByActivityId(activityEntryBO.getActivityId());
            long seconds = (activityEntryBO.getStart().getTime() - new Date().getTime())/1000;

            status = "3";  //默认已报名
            if (ActivityEntryStateEnum.FINISHED.getCode().equals(activityEntryBO.getState())) {
                status = "6";            //已过期(活动过期)
            } else if (ActivityEntryStateEnum.CANCELED.getCode().equals(activityEntryBO.getState())) {
                status = "7";            //已取消
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

        return new ActivityEntryList(totalElements,1,Integer.valueOf(""+totalElements),
                0,Integer.valueOf(""+totalElements) ,true,true,newList);
    }


}
