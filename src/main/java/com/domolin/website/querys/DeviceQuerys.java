package com.domolin.website.querys;

import com.domolin.database.annot.JpqlQuery;
import com.domolin.database.annot.QueryRepository;
import com.domolin.database.annot.SqlNativeQuery;
import com.domolin.database.annot.SqlParam;
import com.domolin.database.entities.types.FileColumn;
import com.domolin.website.services.pojo.DevicePojo;
import java.util.List;

/**
 *
 * @author Fuentes
 */
@QueryRepository
public interface DeviceQuerys {
    @SqlNativeQuery(sql = "SELECT id,name,code,description,link_instalation as linkinstalation ,link_promotion as linkpromotion FROM device limit 5", type = DevicePojo.class)
    public List<DevicePojo> getDevice();
    
    @JpqlQuery(query = "SELECT d.icon FROM Device d WHERE d.id=:id")
    public FileColumn getIconDevice(@SqlParam("id") Long id);
    
    // SELECT d.realPhoto FROM Device d WHERE d.id=:id            
            
    @JpqlQuery(query = "SELECT d.realPhoto FROM Device d WHERE d.id=:id")
    public FileColumn getFotoRealDevice(@SqlParam("id") Long id );
    
    @JpqlQuery(query = "SELECT d.dataShet FROM Device d WHERE d.id=:id")
    public FileColumn getDataShet(@SqlParam("id") Long id );
    
    @SqlNativeQuery(sql = "SELECT id,name,code,description,link_instalation as linkinstalation ,link_promotion as linkpromotion FROM device d WHERE d.id=:id", type = DevicePojo.class)
    public List<DevicePojo> getDeviceInf();
}
