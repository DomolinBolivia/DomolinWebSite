package com.domolin.website.services;

import com.domolin.database.error.NoFountRepoException;
import com.domolin.website.facade.SentinelAppDownloadFacade;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.maven.shared.invoker.MavenInvocationException;

@WebServlet("/download/windows/centinela.zip")
public class DownloadWindowsSentinelServlet extends HttpServlet {
    @Inject
    private SentinelAppDownloadFacade sentinelAppDownloadFacade;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        try {
            String code = sentinelAppDownloadFacade.generateCode();
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + "CentinelaDeDomolin_windows_"+code+".zip\"");
            response.setStatus(200);
            sentinelAppDownloadFacade.generateSentinelApp(code,"windows",response.getOutputStream());
        } catch (NoFountRepoException | IOException | MavenInvocationException ex) {
            throw new IOException("Error al compilar el servicio", ex);
        }
        response.flushBuffer();
    }
}
