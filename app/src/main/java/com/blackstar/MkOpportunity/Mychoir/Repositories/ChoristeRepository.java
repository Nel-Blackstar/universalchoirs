package com.blackstar.MkOpportunity.Mychoir.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.blackstar.MkOpportunity.Mychoir.Config.DBControleur;
import com.blackstar.MkOpportunity.Mychoir.models.Choriste;

import java.util.ArrayList;
import java.util.List;

public class ChoristeRepository extends DBControleur {
    public static final String TABLE_NAME = "Choristes";
    public static final String KEY = "id";
    public static final String NOM = "nom";
    public static final String PRENOM = "prenom";
    public static final String CONTACT = "contact";
    public static final String DATENAISSANCE = "dateNaissance";
    public static final String PUPITRE = "pupiptre";
    public static final String ROLE = "role";
    public static final String SEXE = "sexe";
    public static final String PROFESSION = "profession";
    public static final String TABLE_CREATE = "CREATE TABLE " +
            TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NOM + " TEXT, " +
            PRENOM + " TEXT,"+
            CONTACT + " TEXT,"+
            SEXE + " TEXT,"+
            DATENAISSANCE + " TEXT,"+
            PUPITRE + " TEXT,"+
            ROLE + " TEXT,"+
            PROFESSION + " TEXT);";
    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " +
            TABLE_NAME + ";";

    public ChoristeRepository(Context pContext) {
        super(pContext);
    }

    public void save(Choriste choriste){
        ContentValues value = new ContentValues();
        value.put(ChoristeRepository.NOM,choriste.getNom());
        value.put(ChoristeRepository.PRENOM,choriste.getPrenom());
        value.put(ChoristeRepository.CONTACT,choriste.getContact());
        value.put(ChoristeRepository.DATENAISSANCE,choriste.getDateNaissance().toString());
        value.put(ChoristeRepository.PUPITRE,choriste.getPupiptre());
        value.put(ChoristeRepository.ROLE,choriste.getRole());
        value.put(ChoristeRepository.SEXE,choriste.getSexe());
        value.put(ChoristeRepository.PROFESSION,choriste.getProfession());
        mDb.insert(ChoristeRepository.TABLE_NAME, null, value);
    }
    public void delete(long id) {
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[]{String.valueOf(id)});
    }
    public Choriste find(long id) {
        Cursor c= mDb.rawQuery("select "+KEY+" as _id, * from " + TABLE_NAME + " where "+KEY+" = ?", new String[]{String.valueOf(id)});
        Choriste choriste = new Choriste();
        while (c.moveToNext()) {
            choriste.setId(c.getLong(c.getColumnIndex(KEY)));
            choriste.setNom(c.getString(c.getColumnIndex(NOM)));
            choriste.setPrenom(c.getString(c.getColumnIndex(PRENOM)));
            choriste.setDateNaissance(c.getString(c.getColumnIndex(DATENAISSANCE)));
            choriste.setRole(c.getString(c.getColumnIndex(ROLE)));
            choriste.setSexe(c.getString(c.getColumnIndex(SEXE)));
            choriste.setContact(c.getString(c.getColumnIndex(CONTACT)));
            choriste.setPupiptre(c.getString(c.getColumnIndex(PUPITRE)));
            choriste.setProfession(c.getString(c.getColumnIndex(PROFESSION)));
        }
        return  choriste;
    }

    public void  update(Choriste choriste){
        ContentValues value = new ContentValues();
        value.put(ChoristeRepository.NOM,choriste.getNom());
        value.put(ChoristeRepository.PRENOM,choriste.getPrenom());
        value.put(ChoristeRepository.CONTACT,choriste.getContact());
        value.put(ChoristeRepository.DATENAISSANCE,choriste.getDateNaissance().toString());
        value.put(ChoristeRepository.PUPITRE,choriste.getPupiptre());
        value.put(ChoristeRepository.ROLE,choriste.getRole());
        value.put(ChoristeRepository.SEXE,choriste.getSexe());
        value.put(ChoristeRepository.PROFESSION,choriste.getProfession());
        mDb.update(TABLE_NAME, value, KEY + " = ?", new String[]{String.valueOf(choriste.getId())});
    }

    public List<Choriste> findAll(){
        List<Choriste> choristes=new ArrayList<Choriste>();
        Cursor c= mDb.rawQuery("select "+KEY+" as _id, * from " + TABLE_NAME + " where id > ?", new String[]{"0"});
        while (c.moveToNext()){
            Choriste choriste=new Choriste();
            choriste.setId(c.getLong(c.getColumnIndex(KEY)));
            choriste.setNom(c.getString(c.getColumnIndex(NOM)));
            choriste.setPrenom(c.getString(c.getColumnIndex(PRENOM)));
            choriste.setDateNaissance(c.getString(c.getColumnIndex(DATENAISSANCE)));
            choriste.setRole(c.getString(c.getColumnIndex(ROLE)));
            choriste.setSexe(c.getString(c.getColumnIndex(SEXE)));
            choriste.setContact(c.getString(c.getColumnIndex(CONTACT)));
            choriste.setPupiptre(c.getString(c.getColumnIndex(PUPITRE)));
            choriste.setProfession(c.getString(c.getColumnIndex(PROFESSION)));
            choristes.add(choriste);
        }
        c.close();
        return choristes;
    }

    public Choriste last() {
        Cursor c= mDb.rawQuery("select "+KEY+" as _id, * from " + TABLE_NAME + " ORDER BY "+KEY+" DESC LIMIT ?", new String[]{});
        Choriste choriste = new Choriste();
        while (c.moveToNext()) {
            choriste.setId(c.getLong(c.getColumnIndex(KEY)));
            choriste.setNom(c.getString(c.getColumnIndex(NOM)));
            choriste.setPrenom(c.getString(c.getColumnIndex(PRENOM)));
            choriste.setDateNaissance(c.getString(c.getColumnIndex(DATENAISSANCE)));
            choriste.setRole(c.getString(c.getColumnIndex(ROLE)));
            choriste.setSexe(c.getString(c.getColumnIndex(SEXE)));
            choriste.setContact(c.getString(c.getColumnIndex(CONTACT)));
            choriste.setPupiptre(c.getString(c.getColumnIndex(PUPITRE)));
            choriste.setProfession(c.getString(c.getColumnIndex(PROFESSION)));
        }
        return  choriste;
    }
}
