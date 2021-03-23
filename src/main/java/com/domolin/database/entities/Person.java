package com.domolin.database.entities;

import com.domolin.database.entities.base.BasePerson;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@javax.persistence.Entity
@Getter @Setter
@Table(name = "person")
public class Person extends BasePerson{

}