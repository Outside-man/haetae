/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.organization.idfactory;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import us.betahouse.util.utils.DateUtil;

import java.util.Date;
import java.util.Random;

/**
 * Id 工厂实现
 *
 * @author dango.yxm
 * @version : BizIdFactoryImpl.java 2018/10/06 下午1:28 dango.yxm
 */
@Service("organizationBizIdFactory")
public class BizIdFactoryImpl implements BizIdFactory {

    /**
     * 随机数范围
     */
    private static int RANDOM_RANGE = 100000000;

    /**
     * 随机器
     */
    private final static Random random = new Random();


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
        String numStr = String.valueOf(random.nextInt(RANDOM_RANGE));
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
     * 获取长度length的0字符串
     *
     * @param length
     * @return
     */
    private String getZeroString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append("0");
        }
        return sb.toString();
    }

    @Override
    public String getOrganizationId() {
        StringBuilder builder = new StringBuilder(32);
        Date now = new Date();
        // 1-16 系统时间 16位
        builder.append(DateUtil.getShortDatesStr(now));
        // 16-22 随机数 6位随机数
        builder.append(getRandNum(6));
        // 22-26 业务码 4位业务码
//        builder.append(IdTypeEnum.USER_ID.getBizNum());
        // 26-30 业务自定义码
        builder.append(DateUtil.getYear(now));
        // 30-32 随机 2位
        builder.append(getRandNum(2));
        return builder.toString();
    }
}
