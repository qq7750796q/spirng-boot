package com.zyl;

import com.zyl.listening.GetHttpSession;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.websocket.server.ServerEndpoint;
@EnableSwagger2
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.zyl","com.zyl.design.service"})
@MapperScan("com.zyl.dao")
public class StringbootApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(StringbootApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(StringbootApplication.class, args);
	}
}
