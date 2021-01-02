package com.domolin.website.services;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("sentinel_download")
public class SentinelAppDownloadRest {
    @Inject
    
    @GET
    @Produces("application/zip")
    public Response downloadApp() throws IOException{
        // Generamos el Zip ejecutable
        
        // Creamos una carpeta temporal 
        File file = File.createTempFile("sentilen_app",UUID.randomUUID().toString());
        System.out.println("RUTA: "+file.getAbsolutePath());
        
        // Escribiendo propiedades de la aplicacion 
        Properties properties = new Properties();
        return Response
                .ok("ping")
                .build();
    }
}
