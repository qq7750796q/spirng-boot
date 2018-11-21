package com.zyl.webSocket;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.zyl.listening.GetHttpSession;
import com.zyl.model.UserDomain;
import javassist.tools.web.Webserver;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/** web socket
 * Created by z1761 on 2018/11/2.
 */
@ServerEndpoint(value="/websocket/{username}",configurator = GetHttpSession.class)
@Component
public class WebSocket {
    private static  int onlineCount = 0; //做统计使用
    private static  Map<String,WebSocket> socket = new ConcurrentHashMap<>();
    //private static CopyOnWriteArraySet<WebSocket> socket = new CopyOnWriteArraySet<>();
    private Session session;
    private String username;
    private HttpSession httpSession;
    private static int count =0;

    @OnOpen
    public void onOpen(Session session, EndpointConfig config){
         HttpSession sess= (HttpSession)config.getUserProperties().get("org.apache.catalina.session.StandardSessionFacade");
        UserDomain attribute = (UserDomain) sess.getAttribute("_user");
        if (null != attribute) {
            this.username = attribute.getUserName();
            this.session = session;
            this.httpSession = sess;
            socket.put(attribute.getUserName(),this);
        } else { //用户未登录
            try {
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

       /* count++;
        socket.put(count+"",this);*/


        addOnlineCount();
        System.out.println("已连接");
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    @OnClose
    public void onClone(@PathParam("username")String username,Session session){
        socket.remove(username);
        removeOnlineCount();
        System.out.println("断开连接");
    }

    @OnMessage
    public void onMessage(String message){
        System.out.println("来自客户端的消息:" + message);
        /*for(Map<String,WebSocket> item: socket){
            try {
               // item.onMessage();
               // item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }*/

        //sendMessageAll("给所有人");

        JSONObject json = JSONObject.parseObject(message);
        if (!json.get("TO").equals("ALL")) {
            sendMessageTo(json.get("message").toString(),json.get("TO").toString());
        } else {
            sendMessageAll("给所有人");
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    //添加连接数
    public static synchronized void addOnlineCount(){
        WebSocket.onlineCount++;
    }

    // 删除连接数
    public static synchronized void removeOnlineCount(){
        WebSocket.onlineCount--;
    }

    //给你一个发消息
    public static void sendMessageTo(String message,String to){
        for (WebSocket item : socket.values()) {
            if (item.username.equals(to)) {
                item.session.getAsyncRemote().sendText(message);
            }
        }
    }

    //群发消息
    public static void sendMessageAll(String message){
        for (WebSocket item :socket.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    public static synchronized Map<String,WebSocket> getClients(){
        return socket;
    }
}
