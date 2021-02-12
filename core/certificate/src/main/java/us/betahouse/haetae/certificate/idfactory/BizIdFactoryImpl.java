/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.idfactory;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import us.betahouse.util.utils.DateUtil;

import java.util.Date;
import java.util.Random;

/**
 * @author guofan.cp
 * @version : BizIdFactoryImpl.java 2019/04/02 13:54 guofan.cp
 */
@Service("certificateIdFactory")
public class BizIdFactoryImpl implements BizIdFactory {
    /**
     * @Description: 随机数范围
     * @param:
     */
    private static int RANDOM_RANGE = 100000000;
    /**
     * @Description: 随机器
     * @param:
     */
    private final static Random random = new Random();

    @Override
    public String getCertificateId() {
        StringBuilder builer = new StringBuilder(32);
        Date now = new Date();
        //1-16位系统时间
        builer.append(DateUtil.getShortDatesStr(now));
        //16-22位 6位随机数字
        builer.append(getRandNum(6));
        //22-26位 4位
        builer.append(idTypeEnum.CERTIFICATE_ID.getBizNum());
        //26-30业务自定义码
        builer.append(DateUtil.getYear(now));
        //2位随机数
        builer.append(getRandNum(2));
        return builer.toString();
    }

    @Override
    public String getCompetitionId() {
        StringBuilder builder = new StringBuilder(32);
        Date now = new Date();
        //1-16位系统时间
        builder.append(DateUtil.getShortDatesStr(now));
        //16-22 6位系统随机数
        builder.append(getRandNum(6));
        //22-26位 业务自定义码
        builder.append(idTypeEnum.COMPETITION_ID.getBizNum());
        //26-30 业务自定义码
        builder.append(DateUtil.getYear(now));
        //30-32 随机2位
        builder.append(getRandNum(2));
        return builder.toString();
    }

    @Override
    public String getCompetitionTeamId() {
        StringBuilder builder = new StringBuilder(32);
        Date now = new Date();
        //1-16位系统时间
        builder.append(DateUtil.getShortDatesStr(now));
        //16-22 6位系统随机数
        builder.append(getRandNum(6));
        //22-26位 业务自定义码
        builder.append(idTypeEnum.COMPETITION_TEAMID.getBizNum());
        //26-30 业务自定义码
        builder.append(DateUtil.getYear(now));
        //30-32 随机2位
        builder.append(getRandNum(2));
        return builder.toString();
    }

    @Override
    public String getSkillId() {
        StringBuilder builder = new StringBuilder(32);
        Date now = new Date();
        //1-16位系统时间
        builder.append(DateUtil.getShortDatesStr(now));
        //16-22 6位系统随机数
        builder.append(getRandNum(6));
        //22-26位 业务自定义码
        builder.append(idTypeEnum.SKILL_ID.getBizNum());
        //26-30 业务自定义码
        builder.append(DateUtil.getYear(now));
        //30-32 随机2位
        builder.append(getRandNum(2));
        return builder.toString();
    }

    /**
     * @Description: 获取指定长度的随机数
     * @param: [length]
     */
    private String getRandNum(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("截取非法长度");
        }
        String numStr = String.valueOf(random.nextInt(RANDOM_RANGE));
        return getLengthString(numStr, length);
    }

    /**
     * @Description: 获取长度字符串不足向左补零
     * @param: [str, length]
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
     * @Description: 获取一个长度为length的0字符串
     * @param:
     */
    private String getZeroString(int length) {
        StringBuilder SB = new StringBuilder();
        for (int i = 0; i < length; i++) {
            SB.append("0");
        }
        return SB.toString();
    }
}
