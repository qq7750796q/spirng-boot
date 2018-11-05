package com.zyl.controller;


import com.zyl.service.SampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by z1761 on 2018/10/9.
 */
@Controller
public class SampleController {
    @Autowired
    private SampleService _sampleService;

    private final Logger log = LoggerFactory.getLogger(SampleController.class);

    @RequestMapping("/tst")
    @ResponseBody
    public String test(){

        String str = _sampleService.sampleTestString();
        log.info(str);
        log.error("error");
        log.warn("warn");
        log.debug("debug");
        return str;
    }
}
