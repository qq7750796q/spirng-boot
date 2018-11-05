package com.zyl.design.composite.impl;

import com.zyl.design.composite.AbstractDistanceHandler;
import com.zyl.design.constant.Price;
import com.zyl.design.domain.Taxi;
import com.zyl.design.enums.AbroadTimeTypeEnum;
import com.zyl.design.service.AbroadTimeService;
import com.zyl.design.service.impl.AbroadTimeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/** 起步价
 * Created by z1761 on 2018/10/11.
 */
@Service
public class StartingDistanceHandler extends AbstractDistanceHandler {

    @Autowired
     AbroadTimeService abroadTimeServicess;
    @Autowired
     ApplicationContext applicationContextss;

    @Override
    public BigDecimal getUnitPrice(Date abroadTime, Taxi taxi) {
        AbroadTimeService bean = applicationContextss.getBean(AbroadTimeService.class);

        AbroadTimeServiceImpl abroadTimeService = new AbroadTimeServiceImpl();
        //AbroadTimeTypeEnum abroadTimeType = abroadTimeService.determineAbroadTimeType(abroadTime);

        //计算时间是白天还是晚上
        AbroadTimeTypeEnum abroadTimeType = this.abroadTimeServicess.determineAbroadTimeType(abroadTime);

        if (AbroadTimeTypeEnum.DAY.equals(abroadTimeType)) {
            return Price.DAY_STARTING_PRICE;
        }

        if (AbroadTimeTypeEnum.NIGHT.equals(abroadTimeType)) {
            return Price.NIGHT_STARTING_PRICE;
        }

        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal calculateDistance(BigDecimal distance) {
        return BigDecimal.ONE;
    }
}
