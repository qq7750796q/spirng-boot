package com.zyl.design.decorate;

import com.zyl.design.composite.AbstractDistanceHandler;
import com.zyl.design.composite.impl.BeforeAdjustHandler;
import com.zyl.design.domain.Taxi;
import com.zyl.design.service.AbroadTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/** 如果是高峰期 按照高峰期来算 ，早上9.  10点 晚上 7-9点
 * 这个时间段都在白天路段。
 * 举例 10公里内的
 * @Primary 这个注解告诉spring 假如有多个实现或者继承的话，先去这里找
 * Created by z1761 on 2018/10/11.
 */
@Component
@Primary
public class BeforeAdjustHandlerDecorator extends AbstractDistanceHandler {

    @Autowired
    private AbroadTimeService abroadTimeService;

    @Autowired
    private BeforeAdjustHandler beforeAdjustHandler;

    @Override
    public BigDecimal getUnitPrice(Date abroadTime, Taxi taxi) {
        //在这里进行判断 时间是否在高峰期内，在的话 return单价，假设高峰期单价为TEN(10)
        if (abroadTimeService.isPeak(abroadTime)) {
            return BigDecimal.TEN;
        }
        //不在的话 高峰期又在白天内; 所已调用
        return this.beforeAdjustHandler.getUnitPrice(abroadTime, taxi);
    }

    @Override
    public BigDecimal calculateDistance(BigDecimal distance) {
        return this.beforeAdjustHandler.calculateDistance(distance);
    }
}
