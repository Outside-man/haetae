/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.idfactory;

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
@Service
public class BizIdFactoryImpl implements BizIdFactory {

    /**
     * 随机数范围
     */
    private static int RANDOM_RANGE = 100000000;

    /**
     * 随机器
     */
    private static Random random = new Random();

    @Override
    public String getUserId() {
        StringBuilder builder = new StringBuilder(32);
        Date now = new Date();
        // 1-16 系统时间 16位
        builder.append(DateUtil.getShortDatesStr(now));
        // 16-22 随机数 6位随机数
        builder.append(getRandNum(6));
        // 22-26 业务码 4位业务码
        builder.append(IdTypeEnum.USER_ID.getBizNum());
        // 26-30 业务自定义码
        builder.append(DateUtil.getYear(now));
        // 30-32 随机 2位
        builder.append(getRandNum(2));
        return builder.toString();
    }

    @Override
    public String getRoleId() {
        StringBuilder builder = new StringBuilder(32);
        Date now = new Date();
        // 1-16 系统时间 16位
        builder.append(DateUtil.getShortDatesStr(now));
        // 16-24 随机数 8位随机数
        builder.append(getRandNum(8));
        // 24-28 业务码 4位业务码
        builder.append(IdTypeEnum.ROLE_ID.getBizNum());
        // 28-32 业务自定义码
        builder.append(DateUtil.getMonthDay(now));
        return builder.toString();
    }

    @Override
    public String getRoleUserRelationId(String roleId, String userId) {
        StringBuilder builder = new StringBuilder(32);
        Date now = new Date();
        // 1-16 系统时间 16位
        builder.append(DateUtil.getShortDatesStr(now));
        // 16-24 随机数 8位随机数
        builder.append(getRandNum(8));
        // 24-28 业务码 4位业务码
        builder.append(IdTypeEnum.USER_ROLE_RELATION_ID.getBizNum());
        // 28-30 roleId 尾部2位
        builder.append(getLengthString(roleId, 2));
        // 30-32 userId 尾部2位
        builder.append(getLengthString(userId, 2));
        return builder.toString();
    }

    @Override
    public String getPermId() {
        StringBuilder builder = new StringBuilder(32);
        Date now = new Date();
        // 1-16 系统时间 16位
        builder.append(DateUtil.getShortDatesStr(now));
        // 16-24 随机数 8位随机数
        builder.append(getRandNum(8));
        // 24-28 业务码 4位业务码
        builder.append(IdTypeEnum.PERM_ID.getBizNum());
        // 28-32 业务自定义码
        builder.append(DateUtil.getMonthDay(now));
        return builder.toString();
    }

    @Override
    public String getRolePermRelationId(String roleId, String permId) {
        StringBuilder builder = new StringBuilder(32);
        Date now = new Date();
        // 1-16 系统时间 16位
        builder.append(DateUtil.getShortDatesStr(now));
        // 16-24 随机数 8位随机数
        builder.append(getRandNum(8));
        // 24-28 业务码 4位业务码
        builder.append(IdTypeEnum.ROLE_PERM_RELATION_ID.getBizNum());
        // 28-30 roleId 尾部2位
        builder.append(getLengthString(roleId, 2));
        // 30-32 permId 尾部2位
        builder.append(getLengthString(permId, 2));
        return builder.toString();
    }

    @Override
    public String getUserPermRelationId(String userId, String permId) {
        StringBuilder builder = new StringBuilder(32);
        Date now = new Date();
        // 1-16 系统时间 16位
        builder.append(DateUtil.getShortDatesStr(now));
        // 16-24 随机数 8位随机数
        builder.append(getRandNum(8));
        // 24-28 业务码 4位业务码
        builder.append(IdTypeEnum.USER_PERM_RELATION_ID.getBizNum());
        // 28-30 userId 尾部2位
        builder.append(getLengthString(userId, 2));
        // 30-32 permId 尾部2位
        builder.append(getLengthString(permId, 2));
        return builder.toString();
    }

    @Override
    public String getUserInfoId(String userId) {
        StringBuilder builder = new StringBuilder(32);
        Date now = new Date();
        // 1-16 系统时间 16位
        builder.append(DateUtil.getShortDatesStr(now));
        // 16-24 随机数 8位随机数
        builder.append(getRandNum(8));
        // 24-28 业务码 4位业务码
        builder.append(IdTypeEnum.USER_INFO_ID.getBizNum());
        // 28-30 日
        builder.append(DateUtil.getDay(new Date()));
        // 30-32 userId 尾部2位
        builder.append(getLengthString(userId, 2));
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
        String numStr = String.valueOf(random.nextInt(100000000));
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
