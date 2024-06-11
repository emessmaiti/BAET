package de.th.koeln.benutzerdatenservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BenutzerdatenserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BenutzerdatenserviceApplication.class, args);
    }

}
