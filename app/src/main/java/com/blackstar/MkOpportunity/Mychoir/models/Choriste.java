package com.blackstar.MkOpportunity.Mychoir.models;


public class Choriste {
    public  Long id;
    public  String nom;
    public  String prenom;
    public  String contact;
    public String sexe;
    public String dateNaissance;
    public  String pupiptre;
    public  String role;
    public  String profession;

    public Choriste() {
    }

    public Choriste(Long id, String nom, String prenom, String contact, String dateNaissance, String pupiptre, String role, String profession) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.contact = contact;
        this.dateNaissance = dateNaissance;
        this.pupiptre = pupiptre;
        this.role = role;
        this.profession = profession;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPupiptre() {
        return pupiptre;
    }

    public void setPupiptre(String pupiptre) {
        this.pupiptre = pupiptre;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
