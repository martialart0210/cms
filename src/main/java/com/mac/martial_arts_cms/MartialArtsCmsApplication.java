package com.mac.martial_arts_cms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
public class MartialArtsCmsApplication {
    private static final Logger logger = LogManager.getLogger(MartialArtsCmsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MartialArtsCmsApplication.class, args);
    }

}
