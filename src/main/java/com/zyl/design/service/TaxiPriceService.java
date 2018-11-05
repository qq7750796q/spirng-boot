package com.zyl.design.service;

import com.zyl.design.domain.Taxi;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by z1761 on 2018/10/11.
 */
public interface TaxiPriceService {
    BigDecimal calculatePrice(Date abroadTime, BigDecimal distance, Taxi taxi);
}
