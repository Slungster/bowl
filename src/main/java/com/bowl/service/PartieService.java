package com.bowl.service;

import com.bowl.entities.Joueur;
import com.bowl.entities.Partie;
import com.bowl.repository.PartieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PartieService {

    private JoueurService joueurService;

    private PartieRepository partieRepository;

    @Autowired
    public PartieService(JoueurService joueurService, PartieRepository partieRepository) {
        this.joueurService = joueurService;
        this.partieRepository = partieRepository;
    }

    @Transactional
    public Long creerPartie (Partie partieToCreate) {
        for (Joueur joueur : partieToCreate.getParticipants()) {
            if (!joueurService.existeEnBase(joueur)) {
                throw new IllegalArgumentException("Le participant avec l'id " + joueur.getId() + " n'existe pas");
            }
        }
        Partie partieCree = partieRepository.saveAndFlush(partieToCreate);
        return partieCree.getId();
    }
}
