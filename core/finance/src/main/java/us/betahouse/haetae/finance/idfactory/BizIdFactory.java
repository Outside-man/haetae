/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.finance.idfactory;

import org.springframework.stereotype.Service;

/**
 * @author MessiahJK
 * @version : BizIdFactory.java 2019/02/14 15:08 MessiahJK
 */
@Service("financeBizFactory")
public interface BizIdFactory {

    /**
     * 生成财务信息id
     *
     * @return
     */
    String getFinanceMessageId();

    /**
     * 生成财务统计id
     *
     * @return
     */
    String getFinanceTotalId();
}
