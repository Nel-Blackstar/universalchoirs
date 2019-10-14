package com.blackstar.MkOpportunity.Mychoir.models;

public class Chant {

    public Long id;
    public String categorie;
    public  String refrain;
    public String Contenue;
    public  String Proprietaire;
    public  String titre;

    public Chant() {
    }

    public Chant(String categorie, String refrain, String contenue, String proprietaire) {
        this.categorie = categorie;
        this.refrain = refrain;
        Contenue = contenue;
        Proprietaire = proprietaire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getRefrain() {
        return refrain;
    }

    public void setRefrain(String refrain) {
        this.refrain = refrain;
    }

    public String getContenue() {
        return Contenue;
    }

    public void setContenue(String contenue) {
        Contenue = contenue;
    }

    public String getProprietaire() {
        return Proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        Proprietaire = proprietaire;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
