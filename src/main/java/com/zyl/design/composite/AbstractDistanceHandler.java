package com.zyl.design.composite;

import com.zyl.design.domain.Taxi;

import java.math.BigDecimal;
import java.util.Date;

/** 出租类
 * Created by z1761 on 2018/10/11.
 */
public abstract class AbstractDistanceHandler {

    public BigDecimal calculatePrice(Date abroadTime, BigDecimal distance, Taxi taxi) {
        BigDecimal multiply = this.getUnitPrice(abroadTime, taxi).multiply(this.calculateDistance(distance));
        System.out.println("计算出 单价和公里相乘的价格"+multiply);
        return multiply;
    }

    public abstract BigDecimal getUnitPrice(Date abroadTime, Taxi taxi); //每公里的单价

    public abstract BigDecimal calculateDistance(BigDecimal distance); //行驶距离
}
