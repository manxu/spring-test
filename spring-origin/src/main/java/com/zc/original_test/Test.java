package com.zc.original_test;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.ProtectionDomain;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

import org.springframework.boot.SpringApplication;

public class Test {


    public static void main(String[] args) {
        String s = "中";
        System.out.println(s.getBytes(StandardCharsets.UTF_8).length);

    }


}
