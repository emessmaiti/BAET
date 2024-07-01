package de.th.koeln.finanzdatenservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hauptklasse für die Finanzdaten-Services-Anwendung.
 *
 * <p>Diese Klasse startet die Spring Boot-Anwendung und aktiviert die Service-Discovery sowie Feign-Clients
 * für die Kommunikation mit anderen Services.</p>
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FinanzdatenServicesApplication {

    /**
     * Der Einstiegspunkt der Finanzdaten-Services-Anwendung.
     *
     * @param args Die Befehlszeilenargumente.
     */
    public static void main(String[] args) {
        SpringApplication.run(FinanzdatenServicesApplication.class, args);
    }

}
