package com.zyl.controller;


import com.zyl.design.domain.Taxi;
import com.zyl.design.enums.TravelScopeEnum;
import com.zyl.design.service.AbroadTimeService;
import com.zyl.design.service.TaxiPriceService;
import com.zyl.model.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by z1761 on 2018/10/12.
 */
@Controller
public class AbroadTimeController {
/*
    public void i(int a){

    }
    public int i (int a) {

        return 0;
    }*/

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private AbroadTimeService abroadTimeService;
    @Autowired
    private TaxiPriceService taxiPriceService;

    public static void main(String[] args) {
        System.out.println();
        Integer a = 130;
        Integer b = 130;
        HashMap<String,Object> map = new HashMap<>();
        map.put("","");
        String str = "啊啊";
        System.out.println(str=="啊啊");

      //  System.out.println(a.equals(b));
        /*AbroadTimeController abroadTimeController = new AbroadTimeController();
        BigDecimal bigDecimal = abroadTimeController.abroadPrice();
        System.out.println("价格是"+bigDecimal);*/
       /* Config config = new Config();

        config.useSingleServer().setAddress("redis://47.93.240.189:6379");
        config.useSingleServer().setPassword("Kangsf1919");
        config.useSingleServer().setDatabase(4);
        RedissonClient redisson = Redisson.create(config);

        //首先获取redis中的key-value对象，key不存在没关系
        RBucket<String> keyObject = redisson.getBucket("key");
        //如果key存在，就设置key的值为新值value
        //如果key不存在，就设置key的值为value
       // keyObject.set("value");

        //最后关闭RedissonClient
        redisson.shutdown();*/

/*

        Jedis jedis = new Jedis("47.93.240.189",6379);
        jedis.auth("Kangsf1919");
        jedis.select(4);
        Boolean fals = jedis.exists("_value_code_map");
        System.out.println("是否存在此值"+fals);

        String test= "_value_code_map_test";
        byte[] bytes = test.getBytes();
        System.out.println(bytes);
*/

    }

    @RequestMapping("/admin/test.htm")
    @ResponseBody
    public BigDecimal abroadPrice(UserDomain user){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        String format = simpleDateFormat.format(date);
        System.out.println("时间"+format);
        //    abroadTimeService.determineAbroadTimeType();
        BigDecimal bigDecimal = new BigDecimal("100");
        Taxi taxi = new Taxi();
        taxi.setTravelScope(TravelScopeEnum.INNER_LOOP);
        BigDecimal bigDecimal1 = taxiPriceService.calculatePrice(date, bigDecimal, taxi);
        System.out.println(bigDecimal1);
        return bigDecimal1;
    }

    @RequestMapping("/tologin/test.htm")
    public String abroadPriceaa(){
        System.out.println("进入方法");
      /*  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        String format = simpleDateFormat.format(date);
        System.out.println("时间"+format);
        //    abroadTimeService.determineAbroadTimeType();
        BigDecimal bigDecimal = new BigDecimal("100");
        Taxi taxi = new Taxi();
        taxi.setTravelScope(TravelScopeEnum.INNER_LOOP);
        BigDecimal bigDecimal1 = taxiPriceService.calculatePrice(date, bigDecimal, taxi);
        System.out.println(bigDecimal1);

        return bigDecinullmal1;*/
        ModelAndView modelAndView = new ModelAndView("index");

        return "hello";
    }


}
