/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.idfactory;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import us.betahouse.util.utils.DateUtil;

import java.util.Date;

/**
 * Id 工厂实现
 *
 * @author MessiahJK
 * @version : BizIdFactoryImpl.java 2018/11/17 17:32 MessiahJK
 */
@Service("activityBizIdFactory")
public class BizIdFactoryImpl implements BizIdFactory {

    /**
     * 随机数范围
     */
    private static int RANDOM_RANGE = 100000000;

    @Override
    public String getActivityId() {
        StringBuilder builder = new StringBuilder(32);
        Date now = new Date();
        // 1-16 系统时间 16位
        builder.append(DateUtil.getShortDatesStr(now));
        // 16-24 随机数 8位随机数
        builder.append(getRandNum(8));
        // 24-28 业务码 4位业务码
        builder.append(IdTypeEnum.Activity_ID.getBizNum());
        // 28-32 业务自定义码
        builder.append(DateUtil.getYear(now));
        return builder.toString();
    }

    @Override
    public String getActivityRecordId() {
        StringBuilder builder = new StringBuilder(32);
        Date now = new Date();
        // 1-16 系统时间 16位
        builder.append(DateUtil.getShortDatesStr(now));
        // 16-24 随机数 8位随机数
        builder.append(getRandNum(8));
        // 24-28 业务码 4位业务码
        builder.append(IdTypeEnum.Activity_Record_ID.getBizNum());
        // 28-32 业务自定义码
        builder.append(DateUtil.getYear(now));
        return builder.toString();
    }

    @Override
    public String getPositionRecordId() {
        StringBuilder builder = new StringBuilder(32);
        Date now = new Date();
        // 1-16 系统时间 16位
        builder.append(DateUtil.getShortDatesStr(now));
        // 16-24 随机数 8位随机数
        builder.append(getRandNum(8));
        // 24-28 业务码 4位业务码
        builder.append(IdTypeEnum.Position_Record_ID.getBizNum());
        // 28-32 业务自定义码
        builder.append(DateUtil.getYear(now));
        return builder.toString();
    }

    @Override
    public String getPastActivityId() {
        StringBuilder builder = new StringBuilder(32);
        Date now = new Date();
        // 1-16 系统时间 16位
        builder.append(DateUtil.getShortDatesStr(now));
        // 16-24 随机数 8位随机数
        builder.append(getRandNum(8));
        // 24-28 业务码 4位业务码
        builder.append(IdTypeEnum.Past_Activity_ID.getBizNum());
        // 28-32 业务自定义码
        builder.append(DateUtil.getYear(now));
        return builder.toString();
    }

    @Override
    public String getActivityEntryId() {
        StringBuilder builder = new StringBuilder(32);
        Date now = new Date();
        // 1-16 系统时间 16位
        builder.append(DateUtil.getShortDatesStr(now));
        // 16-24 随机数 8位随机数
        builder.append(getRandNum(8));
        // 24-28 业务码 4位业务码
        builder.append(IdTypeEnum.Activity_Entry_ID.getBizNum());
        // 28-32 业务自定义码
        builder.append(DateUtil.getYear(now));
        return builder.toString();
    }

    @Override
    public String getActivityEntryRecordId() {
        StringBuilder builder = new StringBuilder(32);
        Date now = new Date();
        // 1-16 系统时间 16位
        builder.append(DateUtil.getShortDatesStr(now));
        // 16-24 随机数 8位随机数
        builder.append(getRandNum(8));
        // 24-28 业务码 4位业务码
        builder.append(IdTypeEnum.Activity_Entry_Record_ID.getBizNum());
        // 28-32 业务自定义码
        builder.append(DateUtil.getYear(now));
        return builder.toString();
    }

    @Override
    public String getActivityBlacklistId() {
        StringBuilder builder = new StringBuilder(32);
        Date now = new Date();
        // 1-16 系统时间 16位
        builder.append(DateUtil.getShortDatesStr(now));
        // 16-24 随机数 8位随机数
        builder.append(getRandNum(8));
        // 24-28 业务码 4位业务码
        builder.append(IdTypeEnum.Activity_Blacklist_ID.getBizNum());
        // 28-32 业务自定义码
        builder.append(DateUtil.getYear(now));
        return builder.toString();
    }

    /**
     * 获取指定长度的随机数，不足向左补0
     *
     * @param length
     * @return
     */
    private String getRandNum(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("截取长度非法");
        }
        String numStr = String.valueOf((int) (Math.random() * 100000000));
        return getLengthString(numStr, length);
    }

    /**
     * 获取指定长度字符串，不足向左补0
     *
     * @param str
     * @param length
     * @return
     */
    private String getLengthString(String str, int length) {
        String lengthString = StringUtils.right(str, length);
        if (StringUtils.isBlank(str)) {
            return getZeroString(length);
        }
        if (length > str.length()) {
            return getZeroString(length - str.length()) + str;
        }
        return lengthString;
    }

    /**
     * 获取长度lenth的0字符串
     *
     * @param lenth
     * @return
     */
    private String getZeroString(int lenth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lenth; i++) {
            sb.append("0");
        }
        return sb.toString();
    }
}
