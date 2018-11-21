package com.zyl.listening;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextListener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by z1761 on 2018/11/20.
 */
@Component
public class RequestListening implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        ((HttpServletRequest)servletRequestEvent.getServletRequest()).getSession();
    }
}
