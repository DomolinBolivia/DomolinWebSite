package com.domolin.website.services;

import com.domolin.database.error.NoFountRepoException;
import com.domolin.util.filter.data.ServiceAccess;
import com.domolin.util.filter.data.TypeAccess;
import com.domolin.util.filter.exception.RequiredAuthException;
import com.domolin.util.services.BaseRest;
import com.domolin.website.facade.SendMailFacade;
import com.domolin.website.services.pojo.SendMailData;
import java.io.IOException;
import java.util.HashMap;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Fuentes
 */
@Path("/message")
public class SendMailRest extends BaseRest{
    @Inject
    private SendMailFacade sendMailFacade;
    
    @POST
    @Path("/sendMail")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    
    public HashMap<String,Object> sendMail(SendMailData sendMailData) throws IOException, RequiredAuthException, NoFountRepoException {
        //sendMailFacade.enviarCorreoConsulta(sendMailData.getMail(), sendMailData.getPhone(), sendMailData.getDetail());
        HashMap<String,Object> m  = new  HashMap() ;
        m.put("message", "correo de consulta enviada correctamente.");
        
        return m;
        
        
    }
}
