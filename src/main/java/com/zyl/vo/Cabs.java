package com.zyl.vo;

import java.math.BigDecimal;

/**
 * Created by z1761 on 2018/10/11.
 */
public abstract class Cabs {
    abstract BigDecimal cabsPrice(BigDecimal coutKM,String ring);

}
//白天
class DayTime extends Cabs {

    private BigDecimal km10 = new BigDecimal("10");
    private BigDecimal km20 = new BigDecimal("20");
    @Override
    BigDecimal cabsPrice(BigDecimal coutKM,String ring) {
        BigDecimal bigDecimal = new BigDecimal(0);
        DayPrice dayPrice = new DayPrice();
        if ("内环".equals(ring)) {
            bigDecimal= dayPrice.km10(coutKM);
        } else if ("外环".equals(ring)){
            if (coutKM.compareTo(km10)<1) { //小于等于10公里
                bigDecimal= dayPrice.km10(coutKM);
            } else if (coutKM.compareTo(km20)<1) {
                bigDecimal= dayPrice.km20(coutKM);
            }
        }


        return bigDecimal;
    }
}
//黑夜
class Night extends Cabs {
    private BigDecimal km10 = new BigDecimal("10");
    private BigDecimal km20 = new BigDecimal("20");
    @Override
    BigDecimal cabsPrice(BigDecimal coutKM,String ring) {
        BigDecimal bigDecimal = new BigDecimal(0);
        NightPrice nightPrice = new NightPrice();
        if ("内环".equals(ring)) {
            bigDecimal= nightPrice.km10(coutKM);
        } else if ("外环".equals(ring)){
            if (coutKM.compareTo(km10)<1) { //小于等于10公里
                bigDecimal= nightPrice.km10(coutKM);
            } else if (coutKM.compareTo(km20)<1) {
                bigDecimal= nightPrice.km20(coutKM);
            }
        }
        return null;
    }
}

//白天计价
class DayPrice {
    private BigDecimal dayPriceStarting = new BigDecimal("14");
    private BigDecimal dayPrice10 = new BigDecimal("2.5");
    private BigDecimal dayPriceBig10 = new BigDecimal("3.5");
    //小于等于10公里
    public BigDecimal km10(BigDecimal coutKM){

        return coutKM.multiply(dayPrice10);
    }
    //大于10公里 小于20公里
    public BigDecimal km20(BigDecimal coutKM){
        BigDecimal bigDecimal = new BigDecimal(10);
        BigDecimal bigDecimal1 = this.km10(bigDecimal);

        BigDecimal subtract = coutKM.subtract(bigDecimal);
        BigDecimal multiply = subtract.multiply(dayPriceBig10);
        BigDecimal add = multiply.add(bigDecimal1);
        return add;
    }

}
//夜晚计价
class NightPrice{
    private BigDecimal nightPriceStarting = new BigDecimal("14");
    private BigDecimal nightPrice10 = new BigDecimal("2.5");
    private BigDecimal nightPriceBig10 = new BigDecimal("3.5");
    //小于等于10公里
    public BigDecimal km10(BigDecimal coutKM){

        return coutKM.multiply(nightPrice10);
    }
    //大于10公里 小于20公里
    public BigDecimal km20(BigDecimal coutKM){
        BigDecimal bigDecimal = new BigDecimal(10);
        BigDecimal bigDecimal1 = this.km10(bigDecimal);

        BigDecimal subtract = coutKM.subtract(bigDecimal);
        BigDecimal multiply = subtract.multiply(nightPriceBig10);
        BigDecimal add = multiply.add(bigDecimal1);
        return add;
    }
}

class CabFactory{


    public static Cabs cabfactory(String type){
        Cabs cabs = null;
        if ("白天".equals(type)) {
            cabs = new DayTime();
        } else if ("黑夜".equals(type)){
            cabs = new Night();
        } else {
            System.out.println("不存在的时间");
        }
        return cabs;
    }
}

class testcontro{
    public static void main(String[] args) {
        Cabs day = CabFactory.cabfactory("白天");
        BigDecimal bigDecimal = day.cabsPrice(new BigDecimal(19),"内环");
        System.out.println(bigDecimal);

    }
}