package com.domolin.website.facade;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

public class SentinelAppDownloadFacade {
    public InputStream generateSentinelApp() throws IOException {
        // Generamos el Zip ejecutable
        
        // Creamos una carpeta temporal 
        File file = File.createTempFile("sentilen_app",UUID.randomUUID().toString());
        System.out.println("RUTA: "+file.getAbsolutePath());
        
        // Escribiendo propiedades de la aplicacion 
        Properties properties = new Properties();
        
        return null;
    }
}
