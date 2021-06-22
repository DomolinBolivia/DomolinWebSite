package com.domolin.website;

import com.domolin.database.DBConnector;
import com.domolin.util.filter.AAFilter;
import com.domolin.util.filter.SessionIdValidator;
import com.domolin.util.filter.data.UserInfo;
import com.domolin.util.filter.exception.RequiredAuthException;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppStart implements ServletContextListener{
    @Inject
    private DBConnector dBConnector;
    
    @Inject
    private AAFilter aaFilter;
        
    @Override
    @SuppressWarnings("Convert2Lambda")
    public void contextInitialized(ServletContextEvent sce) {
        try {
            dBConnector.loadQueryRepository();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            aaFilter.scanServiceToFilter(sce.getServletContext(),new SessionIdValidator() {
                @Override
                public UserInfo validate(String authHeader) throws RequiredAuthException {
                    return null;
                }
            });
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}