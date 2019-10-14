package com.blackstar.MkOpportunity.Mychoir.models;

import java.util.Date;

public class Evenement {
    public Long id;
    public Date date;
    public  String lieu;
    public  String concerne;
    public  String raison;

    public Evenement() {
    }

    public Evenement(Long id, Date date, String lieu, String concerne, String raison) {
        this.id = id;
        this.date = date;
        this.lieu = lieu;
        this.concerne = concerne;
        this.raison = raison;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getConcerne() {
        return concerne;
    }

    public void setConcerne(String concerne) {
        this.concerne = concerne;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }
}
