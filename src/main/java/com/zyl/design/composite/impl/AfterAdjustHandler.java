package com.zyl.design.composite.impl;

import com.zyl.design.composite.AbstractDistanceHandler;
import com.zyl.design.constant.Distance;
import com.zyl.design.constant.Price;
import com.zyl.design.domain.Taxi;
import com.zyl.design.enums.AbroadTimeTypeEnum;
import com.zyl.design.enums.TravelScopeEnum;
import com.zyl.design.service.AbroadTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/** 10公里之后的价格
 * Created by z1761 on 2018/10/11.
 */
@Service
public class AfterAdjustHandler extends AbstractDistanceHandler {

    @Autowired
    private AbroadTimeService abroadTimeService;

    @Override
    public BigDecimal getUnitPrice(Date abroadTime, Taxi taxi) {
        //计算时间是白天还是晚上
        AbroadTimeTypeEnum abroadTimeType = this.abroadTimeService.determineAbroadTimeType(abroadTime);
        TravelScopeEnum travelScope = taxi.getTravelScope();
        //判断是白天并且是内换车
        if (AbroadTimeTypeEnum.DAY.equals(abroadTimeType) && TravelScopeEnum.INNER_LOOP.equals(travelScope)) {
            return Price.DAY_UNIT_PRICE_AFTER_ADJUST;
        }
        //判断是白天并且是外环车
        if (AbroadTimeTypeEnum.DAY.equals(abroadTimeType) && TravelScopeEnum.OUTER_LOOP.equals(travelScope)) {
            return Price.DAY_UNIT_PRICE_BEFORE_ADJUST;
        }
        //判断是晚上并且是内环车
        if (AbroadTimeTypeEnum.NIGHT.equals(abroadTimeType) && TravelScopeEnum.INNER_LOOP.equals(travelScope)) {
            return Price.NIGHT_UNIT_PRICE_AFTER_ADJUST;
        }
        //判断是晚上并且是外车
        if (AbroadTimeTypeEnum.NIGHT.equals(abroadTimeType) && TravelScopeEnum.OUTER_LOOP.equals(travelScope)) {
            return Price.NIGHT_UNIT_PRICE_BEFORE_ADJUST;
        }

        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal calculateDistance(BigDecimal distance) {
        return distance.subtract(Distance.ADJUST_DISTANCE);
    }
}
