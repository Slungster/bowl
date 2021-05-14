package com.bowl.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="JOUEUR", uniqueConstraints = @UniqueConstraint(name = "prenom_surnom_unique", columnNames = {"prenom","surnom"}))
public class Joueur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_joueur")
    private Long id;

    @Column(name="prenom")
    private String prenom;

    @Column(name="surnom")
    private String surnom;

    @Column(name="date_naissance")
    private Date dateNaissance;

    @Column(name="age")
    private int age;

    @ManyToMany(mappedBy = "participants")
    private Set<Partie> partiesJouees;

    public Joueur() {
    }

    public Joueur(Long id, String prenom, String surnom, Date dateNaissance, int age) {
        this.id = id;
        this.prenom = prenom;
        this.surnom = surnom;
        this.dateNaissance = dateNaissance;
        this.age = age;
    }

    public Long getId() {
        return id;
    }


    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSurnom() {
        return surnom;
    }

    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joueur joueur = (Joueur) o;
        return prenom.equals(joueur.prenom) &&
               dateNaissance.equals(joueur.dateNaissance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prenom, dateNaissance);
    }
}
