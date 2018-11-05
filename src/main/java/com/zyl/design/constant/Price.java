package com.zyl.design.constant;

import java.math.BigDecimal;

/** 价格
 * Created by z1761 on 2018/10/11.
 */
public class Price {

    public static final BigDecimal DAY_STARTING_PRICE = new BigDecimal(14);
    public static final BigDecimal DAY_UNIT_PRICE_BEFORE_ADJUST = new BigDecimal(2.5);
    public static final BigDecimal DAY_UNIT_PRICE_AFTER_ADJUST = new BigDecimal(3.5);

    public static final BigDecimal NIGHT_STARTING_PRICE = new BigDecimal(18);
    public static final BigDecimal NIGHT_UNIT_PRICE_BEFORE_ADJUST = new BigDecimal(3);
    public static final BigDecimal NIGHT_UNIT_PRICE_AFTER_ADJUST = new BigDecimal(4.7);
}
