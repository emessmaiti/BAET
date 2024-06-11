package de.th.koeln.authentifizierungservice.controller;

import de.th.koeln.authentifizierungservice.dto.BenutzerDaten;
import de.th.koeln.authentifizierungservice.service.BenutzerServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
public class AuthentifizierungController {

    @Autowired
    private BenutzerServiceClient benutzerClient;


    @GetMapping("/auth")
    public Authentication authenticate(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal OAuth2User principal) {
        String email = principal.getAttribute("email");
        String vorname = principal.getAttribute("given_name");
        String nachname = principal.getAttribute("family_name");
        String geschlecht = principal.getAttribute("gender"); // Ensure to map correctly
        String sub = principal.getAttribute("sub");
        LocalDateTime letzteAnmeldung = LocalDateTime.now();

        BenutzerDaten benutzer = benutzerClient.getBenutzerBySub(sub);

        if (benutzer != null) {
            benutzerClient.updateLetzteAnmeldung(sub);
        } else {
            BenutzerDaten benutzerDaten = new BenutzerDaten(vorname, nachname, email, geschlecht, sub, letzteAnmeldung);
            benutzerClient.saveBenutzer(benutzerDaten);
        }
        return "Hallo " + vorname;

    }

    @GetMapping("/getToken")
    public String getToken(@AuthenticationPrincipal OidcUser principal) {
        return principal.getIdToken().getTokenValue();
    }

    @GetMapping("/getSub")
    public String getSub(Authentication authentication) {
        return authentication.getName();
    }


}
