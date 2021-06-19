/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domolin.website.facade;

import com.domolin.database.DBConnector;
import com.domolin.database.entities.types.FileColumn;
import com.domolin.database.error.NoFountRepoException;
import com.domolin.website.querys.DeviceFileQuerys;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Fuentes
 */
@Stateless
public class DevicesFileFacade {

    @Inject
    private DBConnector dBConnector;

//    public HashMap<String,Object> getDeviceFile(Long id) throws NoFountRepoException, IOException{
//        DeviceFileQuerys deviceFilesQuerys = dBConnector.getQueryRepository(DeviceFileQuerys.class);
//        FileColumn fileColumn = deviceFilesQuerys.getDeviceFile(id);
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("iconBase64", fileColumn.readDataInBase64());
//        map.put("iconFormat", fileColumn.getFileType());
//        return map ;
//    }
//    public List<DeviceFilePojo> getDeviceFile(Long id) throws NoFountRepoException, IOException{
//        DeviceFileQuerys deviceFilesQuerys = dBConnector.getQueryRepository(DeviceFileQuerys.class);
//        HashMap<String,Object> list = new HashMap<>();
//        for(DeviceFilePojo x: deviceFilesQuerys.getDeviceFile(id)){            
//            list.put("iconBase64", x.getFile().readDataInBase64());
//            list.put("iconFormat", x.getFile().getFileType());                   
//    }
//         return deviceFilesQuerys.getDeviceFile(id) ;
//   }
    public List<Map> getDeviceFile(Long id) throws NoFountRepoException, IOException {
        DeviceFileQuerys deviceFilesQuerys = dBConnector.getQueryRepository(DeviceFileQuerys.class);
        List<Map> listImages = new LinkedList<>();
        
        for (FileColumn x : deviceFilesQuerys.getDeviceFile(id)) {
            HashMap<String, Object> list = new HashMap<>();
            list.put("iconBase64", x.readDataInBase64());
            list.put("iconFormat", x.getFileType());
            listImages.add(list); 
        }
        return listImages;
    }
}
