package com.domolin.database.entities;

import com.domolin.database.entities.base.BaseDeviceFile;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Fuentes
 */
@javax.persistence.Entity 
@NamedQueries({    
    @NamedQuery(name = DeviceFile.Q_DEVICEFILE_FROM_ID,
    query = "SELECT d.file FROM DeviceFile d JOIN d.device c WHERE c.id =:id")
})

@Getter @Setter
@Table(name = "device_file")
public class DeviceFile extends BaseDeviceFile {
    
    public static final String G_DEVTEMPLATE_ONLY_ROW = "G_DEVTEMPLATE_ONLY_ROW";
    public static final String Q_DEVICEFILE_FROM_ID = "Q_DEVICEFILE_FROM_ID";
    
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "fid_device",nullable = true, updatable = false)
    protected Device device;
    
}
