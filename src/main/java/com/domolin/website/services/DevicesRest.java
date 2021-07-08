package com.domolin.website.services;

import com.domolin.database.error.NoFountRepoException;
import com.domolin.util.services.BaseRest;
import com.domolin.website.facade.DevicesFacade;
import com.domolin.website.services.pojo.DevicePojo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Fuentes
 */
@Path("/device")
public class DevicesRest extends BaseRest {

    @Inject
    private DevicesFacade ImagsFacade;

    @GET
    @Path("/listDevice")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DevicePojo> listDevice() throws NoFountRepoException, IOException {
        return ImagsFacade.getDevice();
    }

    @GET
    @Path("/getIconDevice")
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<String, Object> getIconDevice(@QueryParam("code") String code) throws NoFountRepoException, IOException {
        return ImagsFacade.getIconDevice(code);
    }

    @GET
    @Path("/getDeviceFotoReal")
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<String, Object> getDeviceFotoReal(@QueryParam("id") Long id) throws NoFountRepoException, IOException {
        return ImagsFacade.getDeviceFotoReal(id);
    }

    @GET
    @Path("/getDataShet")
    @Produces(MediaType.APPLICATION_JSON)
    public void getDataShet(@Context HttpServletResponse resp, @QueryParam("id") Long id) throws NoFountRepoException, IOException {
        OutputStream salida = resp.getOutputStream();        
        File archivo = ImagsFacade.getDataShet(id);
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + "Ficha de datos_"+id+".pdf\"");
        resp.setContentType("application/pdf");
        //resp.setContentLength(int.class.cast(archivo.length()));
        resp.setContentLength((int)(archivo.length()));
        FileInputStream entrada= new FileInputStream(archivo);
        entrada.transferTo(salida);        
        salida.flush();
                                      
    }

}
