package de.th.koeln.finanzdatenservices.controller;

import de.th.koeln.finanzdatenservices.entities.AbstraktEntitaet;
import de.th.koeln.finanzdatenservices.service.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;


public abstract class BaseController<T extends AbstraktEntitaet> {

    protected final BaseService<T> baseService;

    protected BaseController(BaseService<T> baseService) {
        this.baseService = baseService;
    }

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T entitaet, @AuthenticationPrincipal Jwt jwt){
      try {
          String benutzerId = jwt.getSubject();
          entitaet.setBenutzerID(benutzerId);
          T createdEntity = baseService.save(entitaet);
          return ResponseEntity.ok(createdEntity);
      } catch (IllegalArgumentException e) {
          return ResponseEntity.badRequest().body(null);
      }
    }

    @PutMapping("/{id}")
    public T update(@PathVariable Long id, @RequestBody T entitaet) {
        entitaet.setId(id);
        return baseService.save(entitaet);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        baseService.delete(id);
    }

    @GetMapping("/{id}")
    public Optional<T> findById(@PathVariable Long id) {
        return baseService.findById(id);
    }

    @GetMapping("/alle")
    public Iterable<T> findAll(@AuthenticationPrincipal Jwt jwt) {
        String sub = jwt.getSubject();
        return baseService.findAllByBenutzerId(sub);
    }

    @GetMapping("/all/{sub}")
    public Iterable<T> findAll(@PathVariable String sub) {
        return baseService.findAllByBenutzerId(sub);
    }

    @GetMapping("/all/konto/{kontoId}")
    public Set<T> findAllByKontoId(@PathVariable Long kontoId) {
        return baseService.findAllByKontoId(kontoId);
    }


}
