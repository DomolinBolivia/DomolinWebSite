/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domolin.website.querys;

import com.domolin.database.annot.JpqlQuery;
import com.domolin.database.annot.QueryRepository;
import com.domolin.database.annot.SqlParam;
import com.domolin.database.entities.types.FileColumn;
import java.util.List;

/**
 *
 * @author Fuentes
 */
@QueryRepository
public interface DeviceFileQuerys {
    
    //@JpqlQuery(queryName = DeviceFile.Q_DEVICEFILE_FROM_ID)
    //public FileColumn getDeviceFile(@SqlParam("id") Long id);
    
//    @JpqlQuery(queryName = DeviceFile.Q_DEVICEFILE_FROM_ID)
//    public List<DeviceFilePojo> getDeviceFile(@SqlParam("id") Long id);
    
    @JpqlQuery(query = "SELECT d.file FROM ProductFile d WHERE d.fidProduct =:id")
    public List<FileColumn> getDeviceFile(@SqlParam("id") Long idDevice);
    
}
