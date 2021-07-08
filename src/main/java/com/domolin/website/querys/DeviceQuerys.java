package com.domolin.website.querys;

import com.domolin.database.annot.JpqlQuery;
import com.domolin.database.annot.QueryRepository;
import com.domolin.database.annot.SqlNativeQuery;
import com.domolin.database.annot.SqlParam;
import com.domolin.database.entities.Device;
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
    
    @JpqlQuery(queryName = Device.Q_DEVICE_FROM_CODE)
    public FileColumn getIconDevice(@SqlParam("code") String code);
    
    @JpqlQuery(queryName = Device.Q_DEVICE_FROM_FOTO_REAL)
    public FileColumn getFotoRealDevice(@SqlParam("id") Long id );
    
    @JpqlQuery(queryName = Device.Q_DEVICE_FROM_DATASHET)
    public FileColumn getDataShet(@SqlParam("id") Long id );
}
