package com.domolin.website;

import com.domolin.util.config.ConfigWar;
import com.domolin.util.util.config.Config;
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
        return new ConfigWar();
    }
}