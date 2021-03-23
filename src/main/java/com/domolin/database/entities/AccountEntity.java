package com.domolin.database.entities;

import com.domolin.database.entities.base.BaseAccountEntity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@javax.persistence.Entity
@Getter @Setter
@Table(name = "account_entity")
public class AccountEntity extends BaseAccountEntity {
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "fid_account",nullable = false, updatable = false)
    protected Account account;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "fid_entity",nullable = false, updatable = false)
    protected Entity entity;
}