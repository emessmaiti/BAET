package de.th.koeln.authentifizierungservice.controller;

import de.th.koeln.authentifizierungservice.clients.KontoClients;
import de.th.koeln.authentifizierungservice.dto.BenutzerDaten;
import de.th.koeln.authentifizierungservice.clients.BenutzerClient;
import de.th.koeln.authentifizierungservice.dto.KontoDaten;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Der AuthentifizierungController bietet Endpunkte zur Handhabung der Authentifizierungsprozesse
 * innerhalb des Authentifizierungsservices. Er ermöglicht die Verarbeitung von Anmeldeinformationen,
 * das Abrufen von Authentifizierungsdetails und die Verwaltung von Benutzer- und Kontodaten.
 *
 * <p>Die Klasse verwendet die Feign Clients {@link BenutzerClient} und {@link KontoClients}, um mit externen
 * Services zur Verwaltung von Benutzer- und Kontodaten zu kommunizieren.</p>
 *
 * <p>Die folgenden Endpunkte sind definiert:</p>
 * <ul>
 *   <li>/api/auth/info: Gibt die Authentifizierungsdetails des aktuellen Benutzers zurück.</li>
 *   <li>/api/auth/loginSuccess: Handhabt die erfolgreiche Anmeldung eines Benutzers, aktualisiert die Benutzerdaten
 *       und erstellt bei Bedarf ein neues Konto.</li>
 *   <li>/api/auth/token: Gibt das ID-Token des aktuellen Benutzers zurück.</li>
 *   <li>/api/auth/sub: Gibt die Subject-ID (sub) des aktuellen Benutzers zurück.</li>
 * </ul>
 */
@RestController
@RequestMapping("/api/auth")
public class AuthentifizierungController {

    @Autowired
    private BenutzerClient benutzerClient;

    @Autowired
    private KontoClients kontoClient;

    /**
     * Gibt die Authentifizierungsdetails des aktuellen Benutzers zurück.
     *
     * @param authentication Die Authentifizierungsinformationen des aktuellen Benutzers.
     * @return Die Authentifizierungsinformationen.
     */
    @GetMapping("/info")
    public Authentication authenticate(Authentication authentication) {
        return authentication;
    }

    /**
     * Handhabt die erfolgreiche Anmeldung eines Benutzers.
     * Aktualisiert die Benutzerdaten und erstellt bei Bedarf ein neues Konto.
     * Leitet den Benutzer anschließend zur Angular-Frontend-Anwendung weiter.
     *
     * @param oidcUser Der authentifizierte OIDC-Benutzer.
     * @param response Die HTTP-Antwort, die für die Weiterleitung verwendet wird.
     * @throws IOException Wenn ein Fehler bei der Weiterleitung auftritt.
     */
    @GetMapping("/loginSuccess")
    public void loginSuccess(@AuthenticationPrincipal OidcUser oidcUser, HttpServletResponse response) throws IOException {
        String accessToken = oidcUser.getIdToken().getTokenValue();
        String email = oidcUser.getEmail();
        String vorname = oidcUser.getGivenName();
        String nachname = oidcUser.getFullName();
        String geschlecht = oidcUser.getGender();
        String sub = oidcUser.getSubject();
        LocalDateTime letzteAnmeldung = LocalDateTime.now();

        BenutzerDaten benutzer = benutzerClient.getBenutzerBySub(sub);
        KontoDaten konto = null;

        if (benutzer != null) {
            benutzerClient.updateLetzteAnmeldung(sub);
            konto = kontoClient.findByBenutzerId(sub).getBody();
            if (konto == null) {
                KontoDaten kontoDaten = new KontoDaten(sub, BigDecimal.ZERO);
                this.kontoClient.save(kontoDaten);
            }
        } else {
            BenutzerDaten benutzerDaten = new BenutzerDaten(vorname, nachname, email, geschlecht, sub, letzteAnmeldung);
            benutzerClient.saveBenutzer(benutzerDaten);
            KontoDaten kontoDaten = new KontoDaten(sub, BigDecimal.ZERO);
            this.kontoClient.save(kontoDaten);
        }

        String kontoId = konto != null ? konto.getId().toString() : "";

        // Weiterleitung zum Angular-Frontend mit dem Access-Token
        response.sendRedirect("http://localhost:4200/login?access_token=" + accessToken + "&kontoId=" + kontoId);
    }

    /**
     * Gibt das Access Token des aktuellen Benutzers zurück.
     *
     * @param principal Der authentifizierte OIDC-Benutzer.
     * @return Das ID-Token des aktuellen Benutzers.
     */
    @GetMapping("/token")
    public String getToken(@AuthenticationPrincipal OidcUser principal) {
        return principal.getIdToken().getTokenValue();
    }

    /**
     * Gibt die Subject-ID (Google subject) des aktuellen Benutzers zurück.
     *
     * @param authentication Die Authentifizierungsinformationen des aktuellen Benutzers.
     * @return Die Subject-ID des aktuellen Benutzers.
     */
    @GetMapping("/sub")
    public String getSub(Authentication authentication) {
        return authentication.getName();
    }
}
