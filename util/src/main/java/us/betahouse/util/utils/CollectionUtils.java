/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.util.utils;

import java.util.*;
import java.util.function.Function;
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

    /**
     * 不同的两个集合 按一定方法去重
     *
     * @param collection
     * @param remove
     * @param getR
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> void removeDuplicate(Collection<T> collection, Collection<R> remove, Function<T, R> getR) {
        if (!isEmpty(remove)) {
            Iterator<T> iterator = collection.iterator();
            while (iterator.hasNext()) {
                T t = iterator.next();
                for (R r : remove) {
                    if (r.equals(getR.apply(t))) {
                        iterator.remove();
                    }
                }
            }
        }
    }
}
