package com.zyl.design.service.impl;

import com.zyl.design.composite.AbstractDistanceHandler;
import com.zyl.design.composite.impl.AfterAdjustHandler;
import com.zyl.design.composite.impl.BeforeAdjustHandler;
import com.zyl.design.composite.impl.StartingDistanceHandler;
import com.zyl.design.constant.Distance;
import com.zyl.design.domain.Taxi;
import com.zyl.design.service.TaxiPriceService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by z1761 on 2018/10/11.
 */
@Service
public class TaxiPriceServiceImpl implements TaxiPriceService, ApplicationContextAware {
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public BigDecimal calculatePrice(Date abroadTime, BigDecimal distance, Taxi taxi) {
        List<AbstractDistanceHandler> distanceHandlerList = new ArrayList<>();

        if (distance.compareTo(Distance.STARTING_DISTANCE) <= BigDecimal.ZERO.intValue()) { //小于三公里的金钱
            distanceHandlerList.add(applicationContext.getBean(StartingDistanceHandler.class));
        } else if (distance.compareTo(Distance.ADJUST_DISTANCE) <= BigDecimal.ZERO.intValue()) { //小于10公里的金钱
            distanceHandlerList.add(applicationContext.getBean(StartingDistanceHandler.class));
            distanceHandlerList.add(applicationContext.getBean(BeforeAdjustHandler.class));
        } else {   //大于10公里的金钱
            distanceHandlerList.add(applicationContext.getBean(StartingDistanceHandler.class));
            distanceHandlerList.add(applicationContext.getBean(BeforeAdjustHandler.class));
            distanceHandlerList.add(applicationContext.getBean(AfterAdjustHandler.class));
        }
        //取到金钱list 开始相加
        BigDecimal totalPrice = BigDecimal.ZERO;
        if (!CollectionUtils.isEmpty(distanceHandlerList)) {
            for (AbstractDistanceHandler distanceHandler : distanceHandlerList) {
                totalPrice = totalPrice.add(distanceHandler.calculatePrice(abroadTime, distance, taxi));
                System.out.println("总和"+totalPrice);
            }
        }

        return totalPrice;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
