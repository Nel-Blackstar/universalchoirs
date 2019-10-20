package com.blackstar.MkOpportunity.Mychoir.models;

import java.util.Date;

public class Finance {
    public Long id;
    public String date;
    public String libeller;
    public String type;
    public  Double montant;

    public Finance() {
    }

    public Finance(String date, String libeller, String type, Double montant) {
        this.date = date;
        this.libeller = libeller;
        this.type = type;
        this.montant = montant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLibeller() {
        return libeller;
    }

    public void setLibeller(String libeller) {
        this.libeller = libeller;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }
}
