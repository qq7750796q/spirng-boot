package com.zyl.vo;

import java.math.BigDecimal;

/** 出租车抽象类
 * Created by z1761 on 2018/10/11.
 */
public abstract class  Cab {

    public abstract BigDecimal computePrice(BigDecimal coutKM,String date,String CabType);
}
