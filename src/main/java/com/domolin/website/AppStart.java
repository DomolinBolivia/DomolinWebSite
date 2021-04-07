package com.domolin.website;

import com.domolin.database.DBConnector;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppStart implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DBConnector.setAppName("DomolinWebSite");
        DBConnector.loadQueryRepository();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}