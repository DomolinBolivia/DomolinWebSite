package com.domolin.website;

import com.domolin.database.DBConnector;
import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppStart implements ServletContextListener{
    @Inject
    private DBConnector dBConnector;
        
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            dBConnector.loadQueryRepository();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}