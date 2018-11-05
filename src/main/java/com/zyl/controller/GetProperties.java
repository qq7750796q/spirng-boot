package com.zyl.controller;

import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by z1761 on 2018/10/26.
 */
@Configuration
public class GetProperties {

    public static void main(String[] args) {
        sessionPro();
    }

    public static void sessionPro(){

        InputStream resourceAsStream = GetProperties.class.getResourceAsStream("/conf/centerServer-pro.properties");
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null != properties) {
            String s = properties.get("download.path").toString();
            System.out.println("取到的"+s);
        }


    }

}
