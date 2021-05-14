package com.bowl.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "PARTIE")
public class Partie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_partie", nullable = false)
    private Long id;

    @Column(name="date_partie", nullable = false)
    private Date datePartie;

    @ManyToMany
    @JoinTable(name = "joueur_partie",
            joinColumns = {@JoinColumn(name = "id_partie")},
            inverseJoinColumns = {@JoinColumn(name = "id_joueur")})
    private Set<Joueur> participants;

    public Partie() {
    }

    public Partie(Date datePartie, Set<Joueur> participants) {
        this.datePartie = datePartie;
        this.participants = participants;
    }

    public Long getId() {
        return id;
    }

    public Date getDatePartie() {
        return datePartie;
    }

    public void setDatePartie(Date datePartie) {
        this.datePartie = datePartie;
    }

    public Set<Joueur> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Joueur> participants) {
        this.participants = participants;
    }
}
