package com.domolin.website;

import com.domolin.util.config.Config;
import javax.ejb.Startup;
import javax.ejb.Stateful;
import javax.inject.Singleton;

@Stateful
@Startup
@Singleton
public class AppConfig extends Config{
    public AppConfig() {
        super();
    }
    
    public String getSentinelInstallerPath(){
        return properties.getProperty("sentinel.proyect.installer");
    }
    
    public String getSentinelPath(){
        return properties.getProperty("sentinel.proyect.app");
    }
    
    public String getSentinelJarName(){
        return properties.getProperty("sentinel.app.compiled_name");
    }
    
    public String getServerDomolinAddress(){
        return properties.getProperty("server.domolin.address");
    }
    
    public String getServerDomolinIotAddress(){
        return properties.getProperty("server.domolin.address.iot");
    }
    
    public String getServerDomolinSslEnabled(){
        return properties.getProperty("server.domolin.ssl.enabled");
    }
    
    public String getServerDomolinPort(){
        return properties.getProperty("server.domolin.port.http");
    }
    
    public String getServerDomolinPortRmi(){
        return properties.getProperty("server.domolin.port.rmi");
    }
    
    public String getSentinelPortRmi(){
        return properties.getProperty("server.sentinel.port.rmi");
    }
    
    public String getSentinelAddressRmi(){
        return properties.getProperty("server.sentinel.address");
    }
}