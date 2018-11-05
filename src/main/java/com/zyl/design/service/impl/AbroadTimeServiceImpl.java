package com.zyl.design.service.impl;

import com.zyl.design.constant.AbroadTime;
import com.zyl.design.enums.AbroadTimeTypeEnum;
import com.zyl.design.service.AbroadTimeService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/** 计算白天还是黑夜
 * Created by z1761 on 2018/10/12.
 */
@Service
public class AbroadTimeServiceImpl implements AbroadTimeService {
    @Override
    public AbroadTimeTypeEnum determineAbroadTimeType(Date abroadTime) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
        String format = simpleDateFormat.format(date);
        Integer hour = Integer.valueOf(format);
        if (hour > AbroadTime.DAY_START || hour < AbroadTime.DAY) {
            return null;
        }
        //大于六点 小于23点 是在白天
        if (hour> AbroadTime.DAY_START&&hour<=AbroadTime.NIGHT_START) {
            return AbroadTimeTypeEnum.DAY;
        } else if (hour<=AbroadTime.DAY_START||hour<AbroadTime.NIGHT_START) {
            return AbroadTimeTypeEnum.NIGHT;
        }
        return null;
    }

    @Override
    public boolean isPeak(Date abroadTime) {
        return false;
    }
}
