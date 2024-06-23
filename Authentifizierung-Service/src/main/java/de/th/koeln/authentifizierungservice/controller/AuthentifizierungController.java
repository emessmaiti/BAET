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
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/auth")
public class AuthentifizierungController {

    @Autowired
    private BenutzerClient benutzerClient;

    @Autowired
    private KontoClients kontoClient;


    @GetMapping("/info")
    public Authentication authenticate(Authentication authentication) {
        return authentication;
    }

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

        if (benutzer != null) {
            benutzerClient.updateLetzteAnmeldung(sub);
        } else {
            BenutzerDaten benutzerDaten = new BenutzerDaten(vorname, nachname, email, geschlecht, sub, letzteAnmeldung);
            benutzerClient.saveBenutzer(benutzerDaten);
            KontoDaten kontoDaten = new KontoDaten(sub, BigDecimal.ZERO);
            this.kontoClient.save(kontoDaten);
        }

        // Redirect to Angular frontend with access token
        response.sendRedirect("http://localhost:4200/login?access_token=" + accessToken);
    }


    @GetMapping("/token")
    public String getToken(@AuthenticationPrincipal OidcUser principal) {
        return principal.getIdToken().getTokenValue();
    }

    @GetMapping("/sub")
    public String getSub(Authentication authentication) {
        return authentication.getName();
    }


}
