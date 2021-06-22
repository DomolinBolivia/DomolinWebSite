package com.domolin.website.services.pojo;


import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Fuentes
 */
@Getter
@Setter
public class DevicePojo {
    private long id;
    private String name;    
    private String description;
    private String code;    
    private String linkinstalation; 
    private String linkpromotion; 
}
