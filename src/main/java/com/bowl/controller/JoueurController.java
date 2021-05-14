package com.bowl.controller;

import com.bowl.entities.Joueur;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import com.bowl.service.JoueurService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JoueurController {

    private JoueurService joueurService;

    @Autowired
    public JoueurController(JoueurService joueurService) {
        this.joueurService = joueurService;
    }

    @GetMapping(value = "/joueurs", produces = "application/json")
    public List<Joueur> getTousLesJoueurs() {
        List<Joueur> joueurs = new ArrayList<Joueur>();
        joueurService.getTousLesJoueurs().forEach(joueurs::add);
        return joueurs;
    }

    @GetMapping(value = "/joueur/{id}", produces = "application/json")
    public ResponseEntity getJoueurParId(@PathVariable @NonNull Long id) throws NotFoundException {
        return joueurService.getJoueurParId(id);
    }

    @PostMapping(value = "/joueur", consumes = "application/json")
    public Joueur creerJoueur(@RequestBody Joueur nouveauJoueur) {
        return joueurService.creerJoueur(nouveauJoueur);
    }

    @PutMapping(value = "/joueur/{id}", consumes = "application/json")
    public void modifierJoueur(@RequestBody Joueur joueur, @PathVariable Long id) throws NotFoundException {
        joueurService.modifierJoueur(joueur, id);
    }

    @DeleteMapping(value = "/joueur/{id}")
    public void supprimerJoueur(@PathVariable Long id) throws NotFoundException {
        joueurService.supprimerJoueur(id);
    }

}
