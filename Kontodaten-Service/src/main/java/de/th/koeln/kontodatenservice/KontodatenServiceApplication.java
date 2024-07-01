package de.th.koeln.kontodatenservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hauptklasse für die Kontodaten-Service-Anwendung.
 *
 * <p>Diese Klasse startet die Spring Boot-Anwendung und aktiviert die Service-Discovery sowie Feign-Clients
 * für die Kommunikation mit anderen Services.</p>
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class KontodatenServiceApplication {

    /**
     * Der Einstiegspunkt der Kontodaten-Service-Anwendung.
     *
     * @param args Die Befehlszeilenargumente.
     */
    public static void main(String[] args) {
        SpringApplication.run(KontodatenServiceApplication.class, args);
    }

}
