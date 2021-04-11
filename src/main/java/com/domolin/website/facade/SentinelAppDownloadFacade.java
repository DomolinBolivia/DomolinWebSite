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
import com.domolin.util.util.ConfigParam;
import com.domolin.website.querys.SentinelAppQuerys;
import com.domolin.website.util.MavenUtilities;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.maven.shared.invoker.MavenInvocationException;

@Stateless
public class SentinelAppDownloadFacade implements Serializable {

    @Inject
    @ConfigParam("sentinel.installer.proyect")
    private String pathInstallerProyect;

    @Inject
    @ConfigParam("sentinel.app.proyect")
    public String pathAppProyect;

    @Inject
    @ConfigParam("sentinel.app.compiled_name")
    private String appSentinelCompiledName;

    @Inject
    @ConfigParam("server.domolin.address")
    private String domolinAddress;

    @Inject
    @ConfigParam("server.domolin.address.iot")
    private String domolinAddressIot;

    @Inject
    @ConfigParam("server.domolin.port.http")
    private String domolinPortHttp;

    @Inject
    @ConfigParam("server.domolin.port.rmi")
    private String domolinPortRmi;

    @Inject
    @ConfigParam("server.sentinel.address")
    private String sentinelAddress;

    @Inject
    @ConfigParam("server.sentinel.port.rmi")
    private String sentinelPortRmi;

    @Inject
    @ConfigParam("server.domolin.ssl.enabled")
    private String domolinSslEnabled;

    public String generateCode() throws NoFountRepoException {
        SentinelAppQuerys appQuerys = DBConnector.getQueryRepository(SentinelAppQuerys.class);
        BigInteger secuencie = appQuerys.generateCode();
        String code = SentinelApp.encodeCode(secuencie);
        return code;
    }

    public void generateSentinelApp(String code, String os,OutputStream outputStream) throws IOException, MavenInvocationException, NoFountRepoException {
        // Compilamos el proyecto sentinela
        Path tempApp = executeMavenCompilationSentinel();

        System.out.println("Obteniendo HASH de la apliaccion Centinela");
        // Obteniendo HASH del archivo generado
        Path pathSentinelApp = tempApp.resolve(appSentinelCompiledName);
        byte[] byteJarSentinel = Files.readAllBytes(pathSentinelApp);
        String hashSha512 = getSha512(byteJarSentinel);

        // Generamos el Zip ejecutable
        // Creamos una carpeta temporal
        Path pathDirInstaller = executeMavenCompilationInstaller(code,os);

        // Copiamos el sentinela a la compilacion
        Files.move(pathSentinelApp, pathDirInstaller.resolve(appSentinelCompiledName), StandardCopyOption.REPLACE_EXISTING);
        zipDirectory(outputStream, pathDirInstaller);

        // Registrando la aplicacion generada
        SentinelApp sentinelApp = new SentinelApp();
        sentinelApp.setGenerationDate(new Date());
        sentinelApp.setHash_app(hashSha512);
        sentinelApp.setCode(code);
        sentinelApp.setVersion(1);
        sentinelApp.setVersionName("1.0.0v");
        
        SentinelAppQuerys appQuerys = DBConnector.getQueryRepository(SentinelAppQuerys.class);
        appQuerys.register(sentinelApp);
    }

    private Path executeMavenCompilationSentinel() throws MavenInvocationException, IOException {
        Path tempApp = Files.createTempDirectory("sentinel_app_");
        Properties properties = new Properties();
        properties.put("directory", tempApp.toFile().getAbsolutePath());
        MavenUtilities.executeMavenCommand(new File(pathAppProyect), properties);
        return tempApp;
    }

    private Path executeMavenCompilationInstaller(String code,String os) throws MavenInvocationException, IOException {
        Path pathDirInstaller = Files.createTempDirectory("sentinel_installer_");
        Path pathDirTargetInstaller = Files.createTempDirectory("sentinel_target_installer_");
        Properties properties = new Properties();
        properties.put("code", code);
        properties.put("directory", pathDirInstaller.toFile().getAbsolutePath());
        properties.put("directoryTarget", pathDirTargetInstaller.toFile().getAbsolutePath());
        properties.put("os",os);
        
        properties.put("server.domolin.address",domolinAddress);
        properties.put("server.domolin.address.iot",domolinAddressIot);
        properties.put("server.domolin.port.http",domolinPortHttp);
        properties.put("server.domolin.port.rmi",domolinPortRmi);
        properties.put("server.domolin.ssl.enabled",domolinSslEnabled);
        properties.put("server.sentinel.port.rmi",sentinelPortRmi);
        properties.put("server.sentinel.address",sentinelAddress);
        
        MavenUtilities.executeMavenCommand(new File(pathInstallerProyect), properties);
        return pathDirInstaller;
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