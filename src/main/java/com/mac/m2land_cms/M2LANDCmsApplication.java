package com.mac.m2land_cms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
//@OpenAPIDefinition(
//        servers = {
//                @Server(url = "http://13.212.143.136:8182/api/v1", description = "CMS BE API")
//        }
//)

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
@EnableScheduling
    public class M2LANDCmsApplication {
    private static final Logger logger = LogManager.getLogger(M2LANDCmsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(M2LANDCmsApplication.class, args);
    }

}
