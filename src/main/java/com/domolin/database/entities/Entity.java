package com.domolin.database.entities;

import com.domolin.database.entities.base.BaseEntity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@javax.persistence.Entity
@Getter @Setter
@Table(name = "entity")
public class Entity extends BaseEntity{    
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "fid_geo_location",nullable = true, updatable = false)
    protected GeoLocation geoLocation;
}
