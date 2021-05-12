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
    @NamedQuery(name = Device.Q_DEVTEMPLATE_FIND_BY_ID, query = "SELECT dt FROM Device dt WHERE dt.id=:id")
})
@Getter @Setter
@Table(name = "device")
public class Device extends BaseDevice{
    public static final String G_DEVTEMPLATE_ONLY_ROW = "G_DEVTEMPLATE_ONLY_ROW";
    public static final String Q_DEVTEMPLATE_FIND_ENABLED = "Q_DEVTEMPLATE_FIND_ENABLED";
    public static final String Q_DEVTEMPLATE_FIND_BY_ID = "Q_DEVTEMPLATE_FIND_BY_ID";
}
