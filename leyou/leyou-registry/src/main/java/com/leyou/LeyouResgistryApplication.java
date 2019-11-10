package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author hewei
 * @date 2019/9/7 - 19:24
 */
@SpringBootApplication
@EnableEurekaServer
public class LeyouResgistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeyouResgistryApplication.class);
    }
}
