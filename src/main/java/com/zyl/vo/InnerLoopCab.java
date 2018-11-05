package com.zyl.vo;

import java.math.BigDecimal;

/** 判断是内环还是外面出租车
 * Created by z1761 on 2018/10/11.
 */
public class InnerLoopCab extends Cab {

    private Price price= new Price();
    private final String  nei= "内环";
    private final String  wai= "外环";

    @Override
    public BigDecimal computePrice(BigDecimal coutKM, String date, String CabType) {
        BigDecimal bigDecimal = new BigDecimal(0);

        if ("1".equals(CabType)) { //内环出租车
            BigDecimal prices = price.prices(coutKM, date);


        }else if ("2".equals(CabType)) { //外环出租车


        } else {
            // 不存在的环路出租车
        }



        return null;
    }
}
