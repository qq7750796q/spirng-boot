package com.zyl.webSocket;

import com.zyl.listening.RequestListening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.EventListener;

/** 这个bean 告诉springboot 要支持websocket
 *
 * Created by z1761 on 2018/11/5.
 */
@Component
public class WebSocketConfig {
    @Autowired
    private RequestListening requestListening;

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean
    public ServletListenerRegistrationBean servletListningRequestBean(){
        ServletListenerRegistrationBean<EventListener> bean = new ServletListenerRegistrationBean<>();
        bean.setListener(requestListening);
        return bean;
    }
}
