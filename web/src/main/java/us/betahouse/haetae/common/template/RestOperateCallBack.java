/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.common.template;

import us.betahouse.util.common.Result;

import java.io.IOException;

/**
 * 操作模板
 *
 * @author dango.yxm
 * @version : OperateCallBack.java 2018/10/06 下午2:19 dango.yxm
 */
public interface RestOperateCallBack<T> {

    default void before() {
    }

    ;

    Result<T> execute() throws IOException;

    default void after() {
    }

    ;
}
