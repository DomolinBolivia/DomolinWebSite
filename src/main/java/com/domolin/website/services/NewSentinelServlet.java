package com.domolin.website.services;

import com.domolin.website.AppConfig;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registro/*")
public class NewSentinelServlet extends HttpServlet {
    @Inject
    private AppConfig appConfig;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo(); // /{value}/test
        String[] pathParts = pathInfo.split("/");
        String code = pathParts[1];
        resp.sendRedirect(req.getContextPath() +'/'+appConfig.getServerDomolinIotAddress() +"/registro?code="+code);
    }
}
