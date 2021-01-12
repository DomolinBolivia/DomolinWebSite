package com.domolin.website.services;

import com.domolin.website.facade.SentinelAppDownloadFacade;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import org.apache.maven.shared.invoker.MavenInvocationException;

@Path("sentinel_download")
public class SentinelAppDownloadRest {
    @Inject
    private SentinelAppDownloadFacade sentinelAppDownloadFacade;
    
    @GET
    @Path("windows")
    @Produces("application/zip")
    public void downloadAppForWindows(@Context HttpServletResponse response) throws IOException, MavenInvocationException{
        String code = sentinelAppDownloadFacade.generateCode();
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + "CentinelaDeDomolin_windows_"+code+"\"");
        response.setStatus(200);
        sentinelAppDownloadFacade.generateSentinelApp(code,"windows",response.getOutputStream());
        response.flushBuffer();
    }
    
    @GET
    @Path("linux")
    @Produces("application/zip")
    public void downloadAppForLinux(@Context HttpServletResponse response) throws IOException, MavenInvocationException{
        String code = sentinelAppDownloadFacade.generateCode();
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + "CentinelaDeDomolin_linux_"+code+"\"");
        response.setStatus(200);
        sentinelAppDownloadFacade.generateSentinelApp(code,"linux",response.getOutputStream());
        response.flushBuffer();
    }
}