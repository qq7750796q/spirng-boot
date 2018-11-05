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

/** 大于三公里 小于10公里
 * Created by z1761 on 2018/10/11.
 */
@Service
public class BeforeAdjustHandler extends AbstractDistanceHandler {

    @Autowired
    private AbroadTimeService abroadTimeService;

    @Override
    public BigDecimal getUnitPrice(Date abroadTime, Taxi taxi) {
        //计算时间是白天还是晚上
        AbroadTimeTypeEnum abroadTimeType = this.abroadTimeService.determineAbroadTimeType(abroadTime);

        if (AbroadTimeTypeEnum.DAY.equals(abroadTimeType)) {
            return Price.DAY_UNIT_PRICE_BEFORE_ADJUST;
        }

        if (AbroadTimeTypeEnum.NIGHT.equals(abroadTimeType)) {
            return Price.NIGHT_UNIT_PRICE_BEFORE_ADJUST;
        }

        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal calculateDistance(BigDecimal distance) {
        if (distance.compareTo(BigDecimal.TEN)>0) {
            return new BigDecimal(BigDecimal.ROUND_UNNECESSARY);
        }
        return distance.subtract(Distance.STARTING_DISTANCE);
    }
}