package com.domolin.website.util;

import com.domolin.util.util.OsUtilities;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

public class MavenUtilities {    
    public static void executeMavenCommand(File proyectHome,Properties properties ) throws MavenInvocationException, IOException{
        if(!proyectHome.exists())
            throw new MavenInvocationException("El proyecto en la ["+proyectHome.getAbsolutePath()+"] no existe");
        if(System.getenv("M2_HOME") == null)
            throw new MavenInvocationException("No se encuentra la variable de entorno [M2_HOME="+System.getenv("M2_HOME")+"]. Establezca la variable de entorno con la ruta absoluta de MAVEN");
        
        File mavenFile = new File(System.getenv("M2_HOME")+File.separator+"bin"+File.separator+(OsUtilities.getOS()==OsUtilities.OS.WINDOWS?"mvn.cmd":"mvn"));
        if(!mavenFile.exists())
            throw new MavenInvocationException("No existe el archivo ["+mavenFile.getAbsolutePath()+"]");
        
        InvocationRequest request = new DefaultInvocationRequest();
        request.setOffline(true);
        request.setDebug(false);
        System.out.println("Ruta Proyecto: " + proyectHome.getAbsolutePath());
        request.setBaseDirectory(proyectHome);
//        request.setGoals(Arrays.asList("clean", "package"));
        request.setGoals(Arrays.asList("package"));
        request.setProperties(properties);

        Invoker invoker = new DefaultInvoker();
        invoker.setMavenExecutable(mavenFile);
        System.out.println("Ruta Maven: "+mavenFile.getParentFile().getAbsolutePath());
        InvocationResult invocationResult = invoker.execute(request);
        if(invocationResult.getExitCode()==1)
            throw new MavenInvocationException("Ocurrio un error al ejecutar la compilación con MAVEN",invocationResult.getExecutionException());
    }
}
