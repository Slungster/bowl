package com.bowl.controller;

import com.bowl.entities.Partie;
import com.bowl.service.PartieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PartieController {

    private PartieService partieService;

    @Autowired
    public PartieController(PartieService partieService) {
        this.partieService = partieService;
    }

    @PostMapping(value = "/partie", consumes = "application/json", produces = "application/json")
    public Long creerPartie (@RequestBody Partie partie) {
        return partieService.creerPartie(partie);
    }
}
