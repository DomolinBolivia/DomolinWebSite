package com.domolin.website.facade;

import com.domolin.database.dao.SentinelAppDao;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import com.domolin.util.util.ConfigParam;
import com.domolin.util.util.OsUtilities;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.maven.shared.invoker.InvocationResult;

@Stateless
public class SentinelAppDownloadFacade implements Serializable {

    @Inject
    @ConfigParam("sentinel.installer.proyect")
    private String pathInstallerProyect;

    @Inject
    @ConfigParam("sentinel.app.proyect")
    private String pathAppProyect;

    @Inject
    @ConfigParam("sentinel.app.compiled_name")
    private String appSentinelCompiledName;

    @Inject
    public SentinelAppDao sentinelAppDao;

    public String generateCode() {
        BigInteger secuencie = sentinelAppDao.generateCode();
        String code = sentinelAppDao.encodeCode(secuencie);
        return code;
    }

    public void generateSentinelApp(String code, String os,OutputStream outputStream) throws IOException, MavenInvocationException {
        if(System.getenv("M2_HOME") == null)
            throw new MavenInvocationException("No se encuentra la variable de entorno [M2_HOME="+System.getenv("M2_HOME")+"]. Establezca la variable de entorno con la ruta absoluta de MAVEN");
        
        File mavenFile = new File(System.getenv("M2_HOME")+File.separator+"bin"+File.separator+(OsUtilities.getOS()==OsUtilities.OS.WINDOWS?"mvn.cmd":"mvn"));
        if(!mavenFile.exists())
            throw new MavenInvocationException("No existe el archivo ["+mavenFile.getAbsolutePath()+"]");
        // Compilamos el proyecto sentinela
        Path tempApp = Files.createTempDirectory("sentinel_app_");
        executeMavenCompilationSentinel(tempApp,mavenFile);

        System.out.println("Obteniendo HASH de la apliaccion Centinela");
        // Obteniendo HASH del archivo generado
        Path pathSentinelApp = tempApp.resolve(appSentinelCompiledName);
        byte[] byteJarSentinel = Files.readAllBytes(pathSentinelApp);
        String hashSha512 = getSha512(byteJarSentinel);
        System.out.println("");
        System.out.println("");
        System.out.println("HASH: " + hashSha512);
        System.out.println("");
        System.out.println("");

        // Generamos el Zip ejecutable
        // Creamos una carpeta temporal
        Path pathDirInstaller = Files.createTempDirectory("sentinel_installer_");
        executeMavenCompilationInstaller(code, pathDirInstaller,os,mavenFile);

        // Copiamos el sentinela a la compilacion
        Files.move(pathSentinelApp, pathDirInstaller.resolve(appSentinelCompiledName), StandardCopyOption.REPLACE_EXISTING);
        zipDirectory(outputStream, pathDirInstaller);

        // Registrando la aplicacion generada
        sentinelAppDao.registerSentinelApp(code, hashSha512, 1, "1.0.0v");
    }

    private synchronized void executeMavenCompilationSentinel(Path path,File mavenHome) throws MavenInvocationException {
        InvocationRequest request = new DefaultInvocationRequest();
//        request.setOffline(true);
        System.out.println("Ruta Proyecto centinela: " + pathAppProyect);
        request.setPomFile(new File(pathAppProyect + File.separator + "pom.xml"));
//        request.setGoals(Arrays.asList("clean", "package"));
        request.setGoals(Arrays.asList("package"));
        Properties properties = new Properties();
        properties.put("directory", path.toFile().getAbsolutePath());
        request.setProperties(properties);

        Invoker invoker = new DefaultInvoker();
        invoker.setMavenExecutable(mavenHome);
        invoker.setMavenHome(mavenHome.getParentFile());
        InvocationResult invocationResult = invoker.execute(request);
        if(invocationResult.getExitCode()==1)
            throw new MavenInvocationException("Ocurrio un error al ejecutar la compilación con MAVEN",invocationResult.getExecutionException());
    }

//    public static void main(String cors[]) throws MavenInvocationException, IOException {
//        
//        System.out.println("MAVEN_HOME: "+System.getenv("M3_HOME"));
//        
//        SentinelAppDownloadFacade appDownloadFacade = new SentinelAppDownloadFacade();
////         appDownloadFacade.pathAppProyect = "D:\\proyectos\\smart_hub\\source\\Sentinel";
////         appDownloadFacade.executeMavenCompilationSentinel(new File("C:\\Users\\GIGABYTE\\AppData\\Local\\Temp\\sentinel_app_6026810746652537848\\").toPath());
//
//        File fileFos = new File("C:\\Users\\GIGABYTE\\Downloads\\prueba.zip");
//        fileFos.createNewFile();
//        FileOutputStream fosZip = new FileOutputStream(fileFos);
//        Path pathFolder = new File("D:\\wilma\\rnt\\ants").toPath();
//        appDownloadFacade.zipDirectory(fosZip, pathFolder);
//    }

    private synchronized void executeMavenCompilationInstaller(String code, Path path,String os, File mavenHome) throws MavenInvocationException {
        InvocationRequest request = new DefaultInvocationRequest();
        request.setOffline(true);
        request.setPomFile(new File(pathInstallerProyect + File.separator + "pom.xml"));
//        request.setGoals(Arrays.asList("clean", "install"));
        request.setGoals(Arrays.asList("package"));
        
        Properties properties = new Properties();
        properties.put("code", code);
        properties.put("directory", path.toFile().getAbsolutePath());
        properties.put("os",os);
        request.setProperties(properties);

        Invoker invoker = new DefaultInvoker();
        invoker.setMavenExecutable(mavenHome);
        invoker.setMavenHome(mavenHome.getParentFile());
        InvocationResult invocationResult = invoker.execute(request);
        if(invocationResult.getExitCode()==1)
            throw new MavenInvocationException("Ocurrio un error al ejecutar la compilación con MAVEN",invocationResult.getExecutionException());
    }

    private void zipDirectory(OutputStream outputStream, Path directoryPath) throws IOException {
        try (ZipOutputStream zs = new ZipOutputStream(outputStream)) {
            Path pp = directoryPath;
            Files.walk(pp)
                .filter(path -> !Files.isDirectory(path))
                .forEach(path -> {
                    ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
                    try {
                        zs.putNextEntry(zipEntry);
                        Files.copy(path, zs);
                        zs.closeEntry();
                    } catch (IOException e) {
                        System.err.println(e);
                    }
                });
        }
    }

    private String getSha512(byte[] bytes) throws IOException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(bytes);
            String result = String.format("%0128x", new BigInteger(1, digest.digest()));
            return result;
        } catch (NoSuchAlgorithmException e) {
            throw new IOException(e);
        }
    }
}
