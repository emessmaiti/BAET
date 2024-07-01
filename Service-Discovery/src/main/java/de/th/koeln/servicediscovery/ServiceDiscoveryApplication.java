package de.th.koeln.servicediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Hauptklasse für die Service-Discovery-Anwendung.
 *
 * <p>Diese Klasse startet die Spring Boot-Anwendung und aktiviert den Eureka-Server für die Service-Discovery.
 * Die Konfiguration des Eureka-Servers erfolgt in der Datei <code>application.properties</code>.</p>
 */
@SpringBootApplication
@EnableEurekaServer
public class ServiceDiscoveryApplication {

    /**
     * Der Einstiegspunkt der Service-Discovery-Anwendung.
     *
     * @param args Die Befehlszeilenargumente.
     */
    public static void main(String[] args) {
        SpringApplication.run(ServiceDiscoveryApplication.class, args);
    }

}
