package com.zyl.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by z1761 on 2018/10/26.
 */
@Configuration
public class RegulationInterceptor implements WebMvcConfigurer {
   // private RelaxedPropertyResolver propertyResolver;
    @Autowired
    private WebInterceptor webInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> objects = new ArrayList<>();
        //objects.add();
        registry.addInterceptor(webInterceptor).addPathPatterns("/**")
               .excludePathPatterns("/images/**","/js/**","/script/**","/templates/**","/test/**","/css/**","/login/**","/META-INF/*")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
        //  super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/tologin/test.htm").setViewName("/static/index.jsp");

    }

}
