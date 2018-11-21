package com.zyl.listening;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

/**
 * Created by z1761 on 2018/11/20.
 */

public class GetHttpSession extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        HttpSession httpSession =   (HttpSession)request.getHttpSession();
        sec.getUserProperties().put(httpSession.getClass().getName(),httpSession);
        //super.modifyHandshake(sec, request, response);
    }
}
