package com.domolin.database.entities;

import com.domolin.database.entities.base.BaseEntityPlane;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@javax.persistence.Entity
@Getter @Setter
@Table(name = "entity_plane")
public class EntityPlane extends BaseEntityPlane{
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "fid_entity",nullable = false, updatable = false)
    protected Entity entity;

}
