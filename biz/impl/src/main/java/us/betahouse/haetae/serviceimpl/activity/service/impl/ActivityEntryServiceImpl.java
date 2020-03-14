package us.betahouse.haetae.serviceimpl.activity.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.activity.builder.ActivityEntryBOBuilder;
import us.betahouse.haetae.activity.builder.ActivityEntryRecordBOBuilder;
import us.betahouse.haetae.activity.dal.service.ActivityEntryRecordRepoService;
import us.betahouse.haetae.activity.dal.service.ActivityEntryRepoService;
import us.betahouse.haetae.activity.dal.service.ActivityRepoService;
import us.betahouse.haetae.activity.enums.ActivityEntryRecordStateEnum;
import us.betahouse.haetae.activity.enums.ActivityEntryStateEnum;
import us.betahouse.haetae.activity.enums.ActivityTypeEnum;
import us.betahouse.haetae.activity.model.basic.ActivityBO;
import us.betahouse.haetae.activity.model.basic.ActivityEntryBO;
import us.betahouse.haetae.activity.model.basic.ActivityEntryRecordBO;
import us.betahouse.haetae.activity.model.common.PageList;
import us.betahouse.haetae.activity.request.ActivityEntryRecordRequest;
import us.betahouse.haetae.activity.request.ActivityEntryRequest;
import us.betahouse.haetae.serviceimpl.activity.enums.ActivityEntryStatusType;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityEntry;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityEntryList;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityEntryService;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.*;

import javax.validation.constraints.AssertTrue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
     * 通过报名id查找报名信息
     *
     * @param activityId
     * @return
     */
    @Override
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


    /**
     * 通过报名信息id查找报名记录对应用户信息
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
                activityEntryRepoService.queryActivityEntryByTermAndStateAndTypeOrderByStartPager(term, state, type, page, limit);
//        return new PageList<ActivityEntry>(activityEntryBOPageList,this::convert);

        for (ActivityEntryBO activityEntryBO : activityEntryBOPageList.getContent()) {
            long seconds = (activityEntryBO.getStart().getTime() - System.currentTimeMillis())/1000;
            ActivityBO activityBO = activityRepoService.queryActivityByActivityId(activityEntryBO.getActivityId());

            String status;
            //状态转换
            {
                status = ActivityEntryStatusType.APPROVED.getCode();
                if (new Date().before(activityEntryBO.getStart())) {
                    if (0 < seconds && seconds < 3600) {
                        //倒计时
                        status = ActivityEntryStatusType.COUNTDOWN.getCode();
                    }
                } else if (DateUtil.nowIsBetween(activityEntryBO.getStart(), activityEntryBO.getEnd())) {
                    //报名中
                    status = ActivityEntryStatusType.REGISTRATION.getCode();
                }

                if (new Date().after(activityEntryBO.getStart())) {
                    ActivityEntryRecordBO activityEntryRecordBO = activityEntryRecordRepoService.findByActivityEntryIdAndUserId(activityEntryBO.getActivityEntryId(), userID);
                    if (activityEntryRecordBO != null) {
                        //后一个半小时时间,90分钟
                        Date anHourAndAHalfAfter = new Date(System.currentTimeMillis()+90*1000*60);
                        if(anHourAndAHalfAfter.before(activityRepoService.queryActivityByActivityId(activityEntryBO.getActivityId()).getStart())){
                            //取消报名
                            status = ActivityEntryStatusType.CANCEL_REGISTERED.getCode();
                        }else{
                            //已报名
                            status = ActivityEntryStatusType.REGISTERED.getCode();
                        }
                    } else if (activityEntryBO.getNumber() <= activityEntryRecordRepoService.countByActivityEntryIdAndState(activityEntryBO.getActivityEntryId(),ActivityEntryRecordStateEnum.SIGN_UP.getCode())) {
                        //人已满
                        status = ActivityEntryStatusType.EXCEED.getCode();
                    } else {
                        //报名中
                        status = ActivityEntryStatusType.REGISTRATION.getCode();
                    }
                }
                if (new Date().after(activityEntryBO.getEnd())) {
                    //已结束
                    status = ActivityEntryStatusType.FINISHED.getCode();
                    if (!ActivityEntryStateEnum.FINISHED.getCode().equals(activityEntryBO.getState()) &&
                            !ActivityEntryStateEnum.CANCELED.getCode().equals(activityEntryBO.getState())) {
                        activityEntryBO.setState(ActivityEntryStateEnum.FINISHED.getCode());
                        activityEntryRepoService.updateActivityEntryByActivityEntryId(activityEntryBO);
                    } else if (ActivityEntryStateEnum.CANCELED.getCode().equals(activityEntryBO.getState())) {
                        //已取消
                        status = ActivityEntryStatusType.CANCELED.getCode();
                    }
                }
            }


            ActivityEntry activityEntry = new ActivityEntry();
            activityEntry.setActivityId(activityEntryBO.getActivityId());
            activityEntry.setActivityEntryId(activityEntryBO.getActivityEntryId());
            activityEntry.setTitle(activityEntryBO.getTitle());
            activityEntry.setActivityEntryStart(DateUtil.format(activityEntryBO.getStart(), "yyyy年MM月dd日 HH:mm:ss"));
            activityEntry.setActivityEntryEnd(DateUtil.format(activityEntryBO.getEnd(), "yyyy年MM月dd日 HH:mm:ss"));
            activityEntry.setActivityName(activityBO.getActivityName());
            activityEntry.setActivityType(activityEntryBO.getType());
            activityEntry.setDescription(activityEntryBO.getNote());
            activityEntry.setLocation(activityBO.getLocation());
            activityEntry.setSecond(seconds);
            activityEntry.setNumber(activityEntryBO.getNumber());
            activityEntry.setLinkman(activityEntryBO.getLinkman());
            activityEntry.setContact(activityEntryBO.getContact());
            activityEntry.setChoose(activityEntryBO.getChoose());
            activityEntry.setTop(activityEntryBO.getTop());
            activityEntry.setStart(DateUtil.format(activityBO.getStart(), "yyyy年MM月dd日 HH:mm:ss"));
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
                AssertUtil.assertTrue("REGISTERED".equals(request.getState()),"报名状态异常");
        }
        if(StringUtils.isNotBlank(request.getType())){
            ActivityTypeEnum typeEnum=ActivityTypeEnum.getByCode(request.getType());
            AssertUtil.assertNotNull(typeEnum, "活动类型不存在");
            type=request.getType();
        }



        List<ActivityEntry> activityEntryList = new ArrayList<>();
        List<ActivityEntryRecordBO> activityEntryRecordBOList = CollectionUtils.toStream(activityEntryRecordRepoService.findAllByUserId(userID))
                .filter((ActivityEntryRecordBO a) -> ActivityEntryRecordStateEnum.SIGN_UP.getCode().equals(a.getState())).collect(Collectors.toList());
        for(ActivityEntryRecordBO activityEntryRecordBO :activityEntryRecordBOList){



            ActivityEntryBO activityEntryBO = activityEntryRepoService.findByActivityEntryId(activityEntryRecordBO.getActivityEntryId());
            ActivityBO activityBO = activityRepoService.queryActivityByActivityId(activityEntryBO.getActivityId());
            long seconds = (activityEntryBO.getStart().getTime() - System.currentTimeMillis())/1000;

            String status;
            //后一个半小时时间,90分钟
            Date anHourAndAHalfAfter = new Date(System.currentTimeMillis()+90*1000*60);
            if(anHourAndAHalfAfter.before(activityRepoService.queryActivityByActivityId(activityEntryBO.getActivityId()).getStart())){
                //取消报名
                status = ActivityEntryStatusType.CANCEL_REGISTERED.getCode();
            }else{
                //已报名
                status = ActivityEntryStatusType.REGISTERED.getCode();
            }

            if (ActivityEntryStateEnum.FINISHED.getCode().equals(activityEntryBO.getState())) {
                //已结束
                status = ActivityEntryStatusType.FINISHED.getCode();
            } else if (ActivityEntryStateEnum.CANCELED.getCode().equals(activityEntryBO.getState())) {
                //已取消
                status = ActivityEntryStatusType.CANCELED.getCode();
            }


            ActivityEntry activityEntry = new ActivityEntry();
            activityEntry.setActivityId(activityEntryBO.getActivityId());
            activityEntry.setActivityEntryId(activityEntryBO.getActivityEntryId());
            activityEntry.setTitle(activityEntryBO.getTitle());
            activityEntry.setActivityEntryStart(DateUtil.format(activityEntryBO.getStart(), "yyyy年MM月dd日 HH:mm:ss"));
            activityEntry.setActivityEntryEnd(DateUtil.format(activityEntryBO.getEnd(), "yyyy年MM月dd日 HH:mm:ss"));
            activityEntry.setActivityName(activityBO.getActivityName());
            activityEntry.setActivityType(activityEntryBO.getType());
            activityEntry.setDescription(activityEntryBO.getNote());
            activityEntry.setLocation(activityBO.getLocation());
            activityEntry.setSecond(seconds);
            activityEntry.setNumber(activityEntryBO.getNumber());
            activityEntry.setLinkman(activityEntryBO.getLinkman());
            activityEntry.setContact(activityEntryBO.getContact());
            activityEntry.setChoose(activityEntryBO.getChoose());
            activityEntry.setTop(activityEntryBO.getTop());
            activityEntry.setStart(DateUtil.format(activityBO.getStart(), "yyyy年MM月dd日 HH:mm:ss"));
            activityEntry.setStatus(status);
            activityEntryList.add(activityEntry);
        }

        final String activityType = type;
        final String activityTerm = term;
        List<ActivityEntry> newList = CollectionUtils.toStream(activityEntryList)
                .filter((ActivityEntry a) -> a.typeAndStatusAndTermFilter(activityType, null, activityTerm)).sorted((o1, o2) -> {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    try {
                        Date o1Date = simpleDateFormat.parse(o1.getStart());
                        Date o2Date = simpleDateFormat.parse(o2.getStart());
                        return o2Date.compareTo(o1Date);
                    } catch (ParseException e) {
                        throw new BetahouseException("ActivityEntry[start]日期解析异常");
                    }
                }).collect(Collectors.toList());
        PageUtil<ActivityEntry> pagelist = new PageUtil<>( newList,request.getPage(),request.getLimit());
        return new ActivityEntryList(
                (long)pagelist.getTotalElements(),
                pagelist.getTotalPages(),
                pagelist.getSize(),
                pagelist.getNumber(),
                pagelist.getNumberOfElements(),
                pagelist.getFirst(),
                pagelist.getEnd(),
                pagelist.getData());
    }

    /**
     * 创建报名
     *
     * @param request
     * @return
     */
    @Override
    public ActivityEntryBO createActivityEntry(ActivityEntryRequest request) {
        AssertUtil.assertTrue(activityEntryRepoService.findAllByActivityId(request.getActivityId()).isEmpty(),"一个活动只允许发起一次报名");
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
                .withState(ActivityEntryStateEnum.PUBLISHED.getCode())
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
        //发布报名  发布的同时不能修改其他报名信息,仅可改状态
        if(activityEntryBO.getState().equals(ActivityEntryStateEnum.APPROVED.getCode()) || request.getState().equals(ActivityEntryStateEnum.PUBLISHED.getCode())){
            activityEntryBO.setState(ActivityEntryStateEnum.PUBLISHED.getCode());
            return activityEntryRepoService.updateActivityEntryByActivityEntryId(activityEntryBO);
        }

        //只有发布前可以修改报名信息
        AssertUtil.assertTrue((activityEntryBO.getState().equals(ActivityEntryStateEnum.APPROVED.getCode())),"当前状态不允许更新报名信息");
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
        if (activityEntryBO.getNumber() <= activityEntryRecordRepoService.countByActivityEntryIdAndState(request.getActivityEntryId(),ActivityEntryRecordStateEnum.SIGN_UP.getCode())) {
            return null;
        }else{
            AssertUtil.assertTrue(activityEntryRecordRepoService.findByActivityEntryIdAndUserId(request.getActivityEntryId(),request.getUserId()) == null,"您已报名");
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
     * 取消报名
     * @param request
     * @return
     */
    @Override
    public ActivityEntryRecordBO undoSignUp(ActivityEntryRecordRequest request) {
        ActivityEntryBO activityEntryBO = activityEntryRepoService.findByActivityEntryId(request.getActivityEntryId());
        AssertUtil.assertNotNull(activityEntryBO, "报名信息不存在");
        ActivityEntryRecordBO activityEntryRecordBO = activityEntryRecordRepoService.findByActivityEntryIdAndUserId(request.getActivityEntryId(),request.getUserId());
        AssertUtil.assertNotNull(activityEntryRecordBO,"您未报名该活动");
        AssertUtil.assertTrue(ActivityEntryRecordStateEnum.UNDO_SIGN_UP.getCode().equals(activityEntryRecordBO.getState()),"您已取消报名");
        String activityId = activityEntryBO.getActivityId();
        //后一个半小时时间,90分钟
        Date anHourAndAHalfAfter = new Date(System.currentTimeMillis()+90*1000*60);
        AssertUtil.assertTrue(anHourAndAHalfAfter.before(activityRepoService.queryActivityByActivityId(activityId).getStart()),"距离活动开始不足1.5小时，不允许取消报名");
        activityEntryRecordBO.setState(ActivityEntryRecordStateEnum.UNDO_SIGN_UP.getCode());
        return activityEntryRecordRepoService.updateActivityEntryRecord(activityEntryRecordBO);
    }


    /**
     * 结束可以结束的报名
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
