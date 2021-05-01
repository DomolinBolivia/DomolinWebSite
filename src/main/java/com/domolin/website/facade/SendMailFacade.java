/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domolin.website.facade;

import com.domolin.util.util.MailUtil;
import javax.ejb.Stateless;

/**
 *
 * @author Fuentes
 */
@Stateless
public class SendMailFacade {
    public void enviarCorreoConsulta(String mail, String phone, String detail){
        MailUtil mailUtil = new MailUtil();
        mailUtil.send("ronaldcoarite@gmail.com",String.format("Correo consulta:%s,%s", mail,phone),detail);

}
    
}
