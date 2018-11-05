package com.zyl.design.service;

import com.zyl.design.enums.AbroadTimeTypeEnum;

import java.util.Date;


/** 计算时间
 * Created by z1761 on 2018/10/11.
 */
public interface AbroadTimeService {

    AbroadTimeTypeEnum determineAbroadTimeType(Date abroadTime);

    boolean isPeak(Date abroadTime);
}
