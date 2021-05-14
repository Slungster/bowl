package com.bowl.repository;

import com.bowl.entities.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoueurRepository extends JpaRepository<Joueur,Long> {
}
