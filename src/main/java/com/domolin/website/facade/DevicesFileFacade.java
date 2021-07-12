package com.domolin.website.facade;

import com.domolin.database.DBConnector;
import com.domolin.database.entities.types.FileColumn;
import com.domolin.database.error.NoFountRepoException;
import com.domolin.website.querys.DeviceFileQuerys;
import java.io.IOException;
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
