package com.zc.original_test;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.security.ProtectionDomain;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

import org.springframework.boot.SpringApplication;

public class Test {


    public static void main(String[] args) throws IOException, URISyntaxException {
        //jar文件读取
        final ProtectionDomain protectionDomain =
                SpringApplication.class.getProtectionDomain();
        System.out.println(protectionDomain.getCodeSource().getLocation().getPath());
        URLConnection connection
                = new URL("jar:http://10.32.36.11:8081/nexus/service/local/repositories/central/content/org/springframework/boot/spring-boot/2.6.2/spring-boot-2.6.2.jar!/").openConnection();

        System.out.println(connection instanceof JarURLConnection);
        JarFile jarFile = new JarFile(
                new File(protectionDomain.getCodeSource().getLocation().toURI()));
        System.out.println(jarFile.getManifest().getMainAttributes().getValue(Attributes.Name.IMPLEMENTATION_VERSION));

    }


}
