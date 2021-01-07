package com.domolin.website;

import com.domolin.util.util.config.Config;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.ejb.Startup;
import javax.ejb.Stateful;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Stateful
@Startup
@Singleton
public class WarConfiguration{
    @Produces
    public Config getConfig() {
        Properties properties = new Properties();
        final InputStream stream = WarConfiguration.class.getResourceAsStream("/META-INF/config.properties");
        if(stream == null){
            throw new RuntimeException("No se encuentra el archivo de propiedades [config.properties]");
        }
        
        try{
            properties.load(stream);
            System.out.println("PROPR");
            System.out.println(properties);
        }catch(IOException ip){
            throw new RuntimeException("No se puede cargar las propiedades de [config.properties]");
        }
        return new ConfigWar(properties);
    }
    
    public class ConfigWar implements Config{
        private final Properties properties;

        public ConfigWar(Properties properties) {
            this.properties = properties;
        }
        
        @Override
        public Properties getProperties() {
            return this.properties;
        }
    }
}