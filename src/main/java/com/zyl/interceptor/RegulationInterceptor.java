package com.zyl.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by z1761 on 2018/10/26.
 */
@Configuration
public class RegulationInterceptor implements WebMvcConfigurer {
   // private RelaxedPropertyResolver propertyResolver;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new webInterceptor()).addPathPatterns("/admin/**");

      //  super.addInterceptors(registry);
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/tologin/test.htm").setViewName("/static/index.jsp");

    }

}
