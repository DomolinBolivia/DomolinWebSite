/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domolin.website.services;

import com.domolin.database.error.NoFountRepoException;
import com.domolin.util.services.BaseRest;
import com.domolin.website.facade.DevicesFacade;
import com.domolin.website.services.pojo.DevicePojo;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
@Path("/device")
public class DevicesRest extends BaseRest{
    @Inject
    private DevicesFacade ImagsFacade;
    
    @GET
    @Path("/listDevice")
    @Produces(MediaType.APPLICATION_JSON)        
    public List<DevicePojo>listDevice()  throws NoFountRepoException, IOException{
    return ImagsFacade.getDevice();
    }
    
    @GET
    @Path("/getIconDevice")
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<String,Object> getIconDevice(@QueryParam("code") String code) throws NoFountRepoException, IOException{
        return ImagsFacade.getIconDevice(code);
    }
    
    @GET
    @Path("/getDeviceFotoReal")
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<String,Object> getDeviceFotoReal(@QueryParam("id") Long id) throws NoFountRepoException, IOException{
        return ImagsFacade.getDeviceFotoReal(id);
    }
}
