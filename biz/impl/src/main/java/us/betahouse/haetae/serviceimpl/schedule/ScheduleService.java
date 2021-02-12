/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.schedule;


/**
 * 定时任务服务
 *
 * @author dango.yxm
 * @version : ScheduleService.java 2018/11/30 12:23 PM dango.yxm
 */
public interface ScheduleService {

    /**
     * 结束活动
     */
    void finishActivity();

    /**
     * 结束报名
     */
    void finishActivityEntries();
}
