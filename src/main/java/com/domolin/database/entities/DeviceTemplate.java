package com.domolin.database.entities;

import com.domolin.database.entities.base.BaseDeviceTemplate;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@javax.persistence.Entity
@Getter @Setter
@Table(name = "device_template")
public class DeviceTemplate extends BaseDeviceTemplate{

}
