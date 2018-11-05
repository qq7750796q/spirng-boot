package com.zyl.service.impl;

import com.zyl.service.SampleService;
import org.springframework.stereotype.Service;

/**
 * Created by z1761 on 2018/10/9.
 */
@Service
public class SampleServiceImpl implements SampleService {
    @Override
    public String sampleTestString() {
        return "调用成功";
    }
}
