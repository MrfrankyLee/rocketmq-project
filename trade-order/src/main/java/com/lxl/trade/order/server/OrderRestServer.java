package com.lxl.trade.order.server;

import com.lxl.trade.common.Constants.TradeEnums;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
/**
 * @author lixiaole
 * @date 2018/9/16
 * @Description
 */
public class OrderRestServer {
    public static void main(String[] args) throws Exception{
        Server server = new Server(TradeEnums.RestServerEnum.ORDER.getServerPort());
        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath("/"+TradeEnums.RestServerEnum.ORDER.getContextPath());

        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setConfigLocation("classpath:xml/spring-web-order.xml");
        handler.addEventListener(new ContextLoaderListener(context));

        handler.addServlet(new ServletHolder(new DispatcherServlet(context)),"/*");
        server.setHandler(handler);
        server.start();
        server.join();
    }
}
