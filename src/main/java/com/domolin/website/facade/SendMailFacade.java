/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domolin.website.facade;

import com.domolin.util.util.MailUtil;
import com.domolin.util.util.MailUtil.FileMail;
import com.domolin.website.services.pojo.FileItem;
import com.domolin.website.services.pojo.SendMailData;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.mail.MessagingException;

/**
 *
 * @author Fuentes
 */
@Stateless
public class SendMailFacade {

    public void enviarCorreoConsulta(SendMailData sendMailData) throws IOException, MessagingException {
        MailUtil mailUtil = new MailUtil();
        InputStream htmlStream = SendMailFacade.class.getResourceAsStream("/mails/respuestaConsultaMail.html");
        Map mapParams = new HashMap();
        mapParams.put("phone", sendMailData.getPhone());
        mapParams.put("detail", sendMailData.getDetail());

        mapParams.put("email", sendMailData.getEmail());
        MailUtil.HtmlMail htmlMail = mailUtil.createHtmlMail(htmlStream, mapParams) ;
        FileMail file[] = new FileMail[sendMailData.getArchivo().size()]; // por archivo adjunto
        
        for (int i = 0; i < file.length; i++) {
            FileItem fileItem = sendMailData.getArchivo().get(i);
            byte[] data = Base64.getDecoder().decode(fileItem.getArchivoBase64());
            file[i] = mailUtil.createFileMail(fileItem.getNombre(), data);
            
        }
        mailUtil.send("wilmafuentes2014@gmail.com", String.format("Correo consulta:%s,%s", sendMailData.getEmail(), sendMailData.getPhone()), htmlMail,file);

    }

}
