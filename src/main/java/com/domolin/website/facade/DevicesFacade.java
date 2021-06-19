/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domolin.website.facade;

import com.domolin.database.DBConnector;
import com.domolin.database.entities.types.FileColumn;
import com.domolin.database.error.NoFountRepoException;
import com.domolin.website.querys.DeviceQuerys;
import com.domolin.website.services.pojo.DevicePojo;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Fuentes
 */

@Stateless
public class DevicesFacade {

    @Inject 
    private DBConnector dBConnector;    
    
    public List<DevicePojo> getDevice() throws NoFountRepoException, IOException{        
        DeviceQuerys deviceQuerys = dBConnector.getQueryRepository(DeviceQuerys.class);
        return deviceQuerys.getDevice();
    }
    
    public HashMap<String,Object> getIconDevice(String code) throws NoFountRepoException, IOException{
        DeviceQuerys deviceQuerys = dBConnector.getQueryRepository(DeviceQuerys.class);
        FileColumn fileColumn = deviceQuerys.getIconDevice(code);
        HashMap<String,Object> map = new HashMap<>();
        map.put("iconBase64", fileColumn.readDataInBase64());
        map.put("iconFormat", fileColumn.getFileType());
        return map ;
    }
    
    public HashMap<String,Object> getDeviceFotoReal(Long id) throws NoFountRepoException, IOException{
        DeviceQuerys deviceQuerys = dBConnector.getQueryRepository(DeviceQuerys.class);
        FileColumn fileColumn = deviceQuerys.getFotoRealDevice(id);
        HashMap<String,Object> map = new HashMap<>();
        map.put("iconBase64", fileColumn.readDataInBase64());
        map.put("iconFormat", fileColumn.getFileType());
        return map ;
    }
                
}
