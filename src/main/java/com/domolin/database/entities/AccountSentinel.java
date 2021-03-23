package com.domolin.database.entities;

import com.domolin.database.entities.base.BaseAccountSentinel;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@javax.persistence.Entity
@Table(name = "account_sentinel")
@Getter @Setter
public class AccountSentinel extends BaseAccountSentinel{    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "fid_account",nullable = false, updatable = false)
    protected Account account;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "fid_sentinel",nullable = false, updatable = false)
    protected Sentinel sentinel;
}
