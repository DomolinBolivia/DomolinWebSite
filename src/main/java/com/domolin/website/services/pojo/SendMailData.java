/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domolin.website.services.pojo;

import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Fuentes
 */
@Getter
@Setter
public class SendMailData {

    private String email;
    private String phone;
    private String detail;
    private List <FileItem> archivo = new LinkedList<>();
    
      
    

}
