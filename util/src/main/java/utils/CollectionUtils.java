/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package utils;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * 集合工具
 *
 * @author dango.yxm
 * @version : CollectionUtils.java 2018/11/16 下午10:08 dango.yxm
 */
public class CollectionUtils {

    /**
     * 转换stream 如果为空 返回空流 避免npe
     *
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> Stream<T> toStream(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return Stream.empty();
        }
        return collection.stream();
    }

    /**
     * 判断集合是否为空
     *
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> boolean isEmpty(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return true;
        }
        return false;
    }
}
