package de.th.koeln.transaktionenservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hauptklasse für die Transaktionen-Service-Anwendung.
 *
 * <p>Diese Klasse startet die Spring Boot-Anwendung und aktiviert die Service-Discovery sowie Feign-Clients
 * für die Kommunikation mit anderen Services.</p>
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class TransaktionenServiceApplication {

    /**
     * Der Einstiegspunkt der Transaktionen-Service-Anwendung.
     *
     * @param args Die Befehlszeilenargumente.
     */
    public static void main(String[] args) {
        SpringApplication.run(TransaktionenServiceApplication.class, args);
    }

}
