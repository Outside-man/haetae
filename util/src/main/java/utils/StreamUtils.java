/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package utils;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * 流工具
 * @author dango.yxm
 * @version : StreamUtils.java 2018/11/16 下午10:08 dango.yxm
 */
public class StreamUtils {

    public static<T> Stream<T> toStream(Collection<T> collection){
        if(collection == null || collection.isEmpty()){
            return Stream.empty();
        }
        return collection.stream();
    }
}
