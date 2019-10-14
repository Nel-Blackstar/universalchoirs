package com.black.chorale.models;

import java.util.Date;

public class Finance {
    public Long id;
    public Date date;
    public String libeller;
    public String type;
    public  Double montant;

    public Finance() {
    }

    public Finance(Date date, String libeller, String type, Double montant) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
