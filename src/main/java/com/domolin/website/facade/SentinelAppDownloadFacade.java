package com.domolin.website.facade;

import com.domolin.database.DBConnector;
import com.domolin.database.entities.SentinelApp;
import com.domolin.database.error.NoFountRepoException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.inject.Inject;
import com.domolin.website.AppConfig;
import com.domolin.website.querys.SentinelAppQuerys;
import com.domolin.website.util.FileParamReplacer;
import com.domolin.website.util.MavenUtilities;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.maven.shared.invoker.MavenInvocationException;

@Stateless
public class SentinelAppDownloadFacade implements Serializable {
    @Inject
    private AppConfig appConfig;
    
    @Inject  
    private DBConnector dBConnector;

    public String generateCode() throws NoFountRepoException {
        SentinelAppQuerys appQuerys = dBConnector.getQueryRepository(SentinelAppQuerys.class);
        BigInteger secuencie = appQuerys.generateCode();
        String code = SentinelApp.encodeCode(secuencie);
        return code;
    }

    public void generateSentinelApp(String code, String os,OutputStream outputStream) throws IOException, MavenInvocationException, NoFountRepoException {
        // Compilamos el proyecto sentinela
        Path tempApp = compileSentineApp();

        // Obteniendo HASH del archivo generado
        Path pathSentinelApp = tempApp.resolve(appConfig.getSentinelJarName());
        byte[] byteJarSentinel = Files.readAllBytes(pathSentinelApp);
        String hashSha512 = getSha512(byteJarSentinel);
        System.out.println("HASH SENTINEL: "+hashSha512);

        // Generamos el Zip ejecutable
        // Creamos una carpeta temporal
        Path pathDirInstaller = generateInstaller(code,os,hashSha512);

        // Copiamos el sentinela a la compilacion
        Files.move(pathSentinelApp, pathDirInstaller.resolve("sentinel_app"+File.separator+appConfig.getSentinelJarName()), StandardCopyOption.REPLACE_EXISTING);
        
        // Excribimos el archivo de propiedades
        HashMap<String,String> paramApp = new HashMap<>();
        File fileTemplateApp = pathDirInstaller.resolve("sentinel_installer"+File.separator+"template.application.properties").toFile();

        paramApp.put("server.domolin.address",appConfig.getServerDomolinAddress());
        paramApp.put("server.domolin.address.iot",appConfig.getServerDomolinIotAddress());
        paramApp.put("server.domolin.port.http",appConfig.getServerDomolinPort());
        paramApp.put("server.domolin.port.rmi",appConfig.getServerDomolinPortRmi());
        paramApp.put("server.domolin.ssl.enabled",appConfig.getServerDomolinSslEnabled());
        paramApp.put("server.sentinel.port.rmi",appConfig.getSentinelPortRmi());
        paramApp.put("server.sentinel.address",appConfig.getSentinelAddressRmi());
        File destination = pathDirInstaller.resolve("sentinel_app"+File.separator+"application.properties").toFile();
        FileParamReplacer.replaceParams(paramApp, fileTemplateApp, destination);
        

        // Comprimimos le instaldor
        zipDirectory(outputStream, pathDirInstaller);

        // Registrando la aplicacion generada
        SentinelApp sentinelApp = new SentinelApp();
        sentinelApp.setGenerationDate(new Date());
        sentinelApp.setHashApp(hashSha512);
        sentinelApp.setCode(code);
        sentinelApp.setVersion(1);
        sentinelApp.setVersionName("1.0.0v");
        
        SentinelAppQuerys appQuerys = dBConnector.getQueryRepository(SentinelAppQuerys.class);
        appQuerys.register(sentinelApp);
    }

    private Path compileSentineApp() throws MavenInvocationException, IOException {
        Path tempApp = Files.createTempDirectory("sentinel_app_");
        Properties properties = new Properties();
        properties.put("directory", tempApp.toFile().getAbsolutePath());
        MavenUtilities.executeMavenCommand(new File(appConfig.getSentinelPath()), properties,"package");
        return tempApp;
    }

    private Path generateInstaller(String code,String os,String hashApp) throws MavenInvocationException, IOException {
        Path pathDirInstaller = Files.createTempDirectory("sentinel_installer_");
        Properties properties = new Properties();
        properties.put("code", code);
        properties.put("directory", pathDirInstaller.toFile().getAbsolutePath());
        properties.put("os",os);
        properties.put("hashApp",hashApp);
        
        MavenUtilities.executeMavenCommand(new File(appConfig.getSentinelInstallerPath()), properties,"antrun:run@generate_installer");
        return pathDirInstaller;
    }

    private void zipDirectory(OutputStream outputStream, Path directoryPath) throws IOException {
        try (ZipOutputStream zs = new ZipOutputStream(outputStream)) {
            Path pp = directoryPath;
            Files.walk(pp)
                    .forEach(path -> {
                        try {
                            if (Files.isDirectory(path)) {
                                zs.putNextEntry(new ZipEntry(pp.relativize(path).toString() + "/"));
                            } else {
                                ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
                                zs.putNextEntry(zipEntry);
                                Files.copy(path, zs);
                                zs.closeEntry();
                            }
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