package de.th.koeln.authentifizierungservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Die Hauptklasse der Authentifizierungsservice-Anwendung.
 * Diese Klasse ist der Einstiegspunkt für die Spring Boot-Anwendung.
 *
 * <p>{@link SpringBootApplication} markiert die Hauptkonfigurationsklasse
 * und aktiviert die automatische Konfiguration von Spring Boot.</p>
 *
 * <p>{@link EnableDiscoveryClient} aktiviert die Service Discovery-Funktionalität,
 * die es der Anwendung ermöglicht, sich bei Eureka Service-Discovery-Server zu registrieren
 * und andere registrierte Dienste zu entdecken.</p>
 *
 * <p>{@link EnableFeignClients} aktiviert die Verwendung von Feign Clients in der Anwendung.
 * Feign ist ein deklarativer HTTP-Client, der die Kommunikation mit anderen Microservices vereinfacht.</p>
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AuthentifizierungServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthentifizierungServiceApplication.class, args);
    }
}
