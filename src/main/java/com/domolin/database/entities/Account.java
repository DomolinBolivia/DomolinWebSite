package com.domolin.database.entities;

import com.domolin.database.entities.base.BaseAccount;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@javax.persistence.Entity
@Table(name = "account")
@Getter @Setter
public class Account extends BaseAccount{   
    
    @OneToOne(optional = true,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "fid_person")
    protected Person person;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "fid_account")
    protected List<AccountAuthType> accountAuthTypes = new ArrayList<>(3);
}
