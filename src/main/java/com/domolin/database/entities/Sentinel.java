package com.domolin.database.entities;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@javax.persistence.Entity
@Getter @Setter
@Table(name = "sentinel")
public class Sentinel extends com.domolin.database.entities.base.BaseSentinel{    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "fid_sentinel_app",nullable = false, updatable = false)
    protected SentinelApp sentinelApp;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "fid_entity",nullable = false, updatable = false)
    protected Entity entity;
    
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "fid_geo_location",nullable = true, updatable = false)
    protected GeoLocation geoLocation;
}
