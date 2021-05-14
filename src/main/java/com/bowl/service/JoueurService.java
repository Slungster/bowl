package com.bowl.service;

import com.bowl.entities.Joueur;
import com.bowl.repository.JoueurRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class JoueurService {

    private JoueurRepository joueurRepository;

    @Autowired
    public JoueurService(JoueurRepository joueurRepository) {
        this.joueurRepository = joueurRepository;
    }

    //@Transactional
    public Joueur creerJoueur (Joueur joueur) {
        if (joueur.getId() != null) {
            throw new IllegalArgumentException("Le Joueur en entrée possède un id, voulez-vous créer un joueur ou modifier un joueur existant ?");
        }
        return joueurRepository.saveAndFlush(joueur);
    }

    public ResponseEntity getJoueurParId(Long id) throws NotFoundException {
        //ResponseEntity response;
        Optional<Joueur> joueurBdd = joueurRepository.findById(id);
        if (joueurBdd.isPresent()) {
            return ResponseEntity.ok(joueurBdd.get());
        } else {
            return new ResponseEntity("Le Joueur avec l'id " + id + " n'existe pas, consultation impossible",HttpStatus.NOT_FOUND);
            //throw new NotFoundException("Le Joueur avec l'id " + id + " n'existe pas, consultation impossible");
        }
    }

    public Iterable<Joueur> getTousLesJoueurs () { return joueurRepository.findAll();}

    public void modifierJoueur(Joueur joueur, Long id) throws NotFoundException {
        Optional<Joueur> joueurBdd = joueurRepository.findById(joueur.getId());
        if (!joueurBdd.isPresent()) {
            throw new NotFoundException("Le Joueur avec l'id " + joueur.getId() + " n'existe pas, modification impossible");
        }
        Joueur joueurAModifier = joueurBdd.get();
        joueurAModifier.setSurnom(joueur.getSurnom());
        joueurRepository.save(joueurAModifier);
    }

    public void supprimerJoueur(Long id) throws NotFoundException {
        Optional<Joueur> joueurBdd = joueurRepository.findById(id);
        if (!joueurBdd.isPresent()) {
            throw new NotFoundException("Le Joueur avec l'id " + id + " n'existe pas, suppression impossible");
        }
        joueurRepository.deleteById(id);
    }

    public boolean existeEnBase (Joueur joueur) {
        boolean result = true;
        Optional<Joueur> joueurControle = joueurRepository.findById(joueur.getId());
        if (!joueurControle.isPresent()) {result = false;}
        return result;
    }


    @PostConstruct
    public void afterService () {
        System.out.println("JoueurService classe générée");
    }


}
