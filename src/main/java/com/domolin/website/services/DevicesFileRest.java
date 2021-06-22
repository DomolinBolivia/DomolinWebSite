/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domolin.website.services;

import com.domolin.database.error.NoFountRepoException;
import com.domolin.util.services.BaseRest;
import com.domolin.website.facade.DevicesFileFacade;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Fuentes
 */
@Path("/deviceFile")
public class DevicesFileRest extends BaseRest{
    @Inject
    private DevicesFileFacade devicesFileFacade;
    
    @GET
    @Path("/getDeviceFile")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map> getDeviceFile(@QueryParam("id") Long id) throws NoFountRepoException, IOException{
        return devicesFileFacade.getDeviceFile(id);
    }
}
