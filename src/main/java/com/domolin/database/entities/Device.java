package com.domolin.database.entities;

import com.domolin.database.entities.base.BaseDevice;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@javax.persistence.Entity
@NamedEntityGraphs({
    @NamedEntityGraph(includeAllAttributes = false,name = Device.G_DEVTEMPLATE_ONLY_ROW)
})
@NamedQueries({
    @NamedQuery(name = Device.Q_DEVTEMPLATE_FIND_ENABLED, query = "SELECT dt FROM Device dt"),
    @NamedQuery(name = Device.Q_DEVTEMPLATE_FIND_BY_ID, query = "SELECT dt FROM Device dt WHERE dt.id=:id"),
    @NamedQuery(name = Device.Q_DEVICE_FROM_CODE, query = "SELECT d.icon FROM Device d WHERE d.code=:code"),
    @NamedQuery(name = Device.Q_DEVICE_FROM_FOTO_REAL, query = "SELECT d.realPhoto FROM Device d WHERE d.id=:id"),
    @NamedQuery(name = Device.Q_DEVICE_FROM_DATASHET, query = "SELECT d.dataShet FROM Device d WHERE d.id=:id")
})
@Getter @Setter
@Table(name = "device")
public class Device extends BaseDevice{
    public static final String G_DEVTEMPLATE_ONLY_ROW = "G_DEVTEMPLATE_ONLY_ROW";
    public static final String Q_DEVTEMPLATE_FIND_ENABLED = "Q_DEVTEMPLATE_FIND_ENABLED";
    public static final String Q_DEVTEMPLATE_FIND_BY_ID = "Q_DEVTEMPLATE_FIND_BY_ID";
    public static final String Q_DEVICE_FROM_CODE = "Q_DEVICE_FROM_CODE";
    public static final String Q_DEVICE_FROM_FOTO_REAL = "Q_DEVICE_FROM_FOTO_REAL";
    public static final String Q_DEVICE_FROM_DATASHET = "Q_DEVICE_FROM_DATASHET";
}
