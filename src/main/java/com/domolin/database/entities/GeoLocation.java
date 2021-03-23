package com.domolin.database.entities;

import com.domolin.database.entities.base.BaseGeoLocation;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@javax.persistence.Entity
@Getter @Setter
@Table(name = "geo_location")
public class GeoLocation extends BaseGeoLocation{
}
