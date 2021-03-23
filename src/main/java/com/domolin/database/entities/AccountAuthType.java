package com.domolin.database.entities;

import com.domolin.database.entities.base.BaseAccountAuthType;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@javax.persistence.Entity
@Getter @Setter
@Table(name = "account_auth_type")
public class AccountAuthType extends BaseAccountAuthType{
    @Id
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fid_account",nullable = false, updatable = false)
    protected Account account;
}
