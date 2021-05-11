
import com.domolin.database.error.NoFountRepoException;
//import com.domolin.website.facade.SentinelAppDownloadFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.ws.rs.Path;
import org.apache.maven.shared.invoker.MavenInvocationException;

public class TestCompilarCentinela {

    public static void main(String cors[]) throws MavenInvocationException, IOException, NoFountRepoException {

        System.out.println("MAVEN_HOME: " + System.getenv("M2_HOME"));

        //SentinelAppDownloadFacade appDownloadFacade = new SentinelAppDownloadFacade();
       // appDownloadFacade.pathAppProyect = "D:\\proyectos\\centinela\\source\\Sentinel";
        File temp = new File("C:\\Users\\Ronald\\Downloads\\sentinel.jar");
        //appDownloadFacade.generateSentinelApp("assas","asdas", new FileOutputStream(temp));

//        File fileFos = new File("C:\\Users\\GIGABYTE\\Downloads\\prueba.zip");
//        fileFos.createNewFile();
//        FileOutputStream fosZip = new FileOutputStream(fileFos);
//        Path pathFolder = new File("D:\\wilma\\rnt\\ants").toPath();
//        appDownloadFacade.zipDirectory(fosZip, pathFolder);
    }
}
