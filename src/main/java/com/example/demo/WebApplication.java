package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//https://www.agilegroup.co.jp/technote/springboot-fileupload-error-handling.html
@SpringBootApplication
public class WebApplication {

    private int maxUploadSizeInMb = 100 * 1024 * 1024; // 100 MB

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebApplication.class, args);
    }


}