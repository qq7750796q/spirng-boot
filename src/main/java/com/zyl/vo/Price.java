package com.zyl.vo;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by z1761 on 2018/10/11.
 */
public class Price  {
   private  DateFormat dates = new SimpleDateFormat("HH");
    private Cab cab = null;

    public BigDecimal prices(BigDecimal coutKM, String date){
        try {
            Date parse = dates.parse(date);
            Integer integers = Integer.valueOf(parse.toString());
           if (6<integers&&integers<=23) {
               cab = new InnerLoopCab();

           } else if(23<integers||integers>6){

           }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  null;
    }

}
