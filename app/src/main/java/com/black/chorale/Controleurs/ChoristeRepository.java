package com.black.chorale.Controleurs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.black.chorale.Config.DBControleur;
import com.black.chorale.models.Choriste;
import com.black.chorale.models.Finance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public static final String PROFESSION = "profession";
    public static final String TABLE_CREATE = "CREATE TABLE " +
            TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NOM + " TEXT, " +
            PRENOM + " TEXT,"+
            CONTACT + " TEXT,"+
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
        value.put(ChoristeRepository.NOM,choriste.getNom().toString());
        value.put(ChoristeRepository.PRENOM,choriste.getPrenom());
        value.put(ChoristeRepository.CONTACT,choriste.getContact());
        value.put(ChoristeRepository.DATENAISSANCE,choriste.getDateNaissance().toString());
        value.put(ChoristeRepository.PUPITRE,choriste.getPupiptre());
        value.put(ChoristeRepository.ROLE,choriste.getRole());
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
            try {
                choriste.setDateNaissance(new SimpleDateFormat("dd/MM/yyyy").parse(c.getString(c.getColumnIndex(DATENAISSANCE))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            choriste.setRole(c.getString(c.getColumnIndex(ROLE)));
            choriste.setContact(c.getString(c.getColumnIndex(CONTACT)));
            choriste.setPupiptre(c.getString(c.getColumnIndex(PUPITRE)));
            choriste.setProfession(c.getString(c.getColumnIndex(PROFESSION)));
        }
        return  choriste;
    }

    public void  update(Choriste choriste){
        ContentValues value = new ContentValues();
        value.put(ChoristeRepository.NOM,choriste.getNom().toString());
        value.put(ChoristeRepository.PRENOM,choriste.getPrenom());
        value.put(ChoristeRepository.CONTACT,choriste.getContact());
        value.put(ChoristeRepository.DATENAISSANCE,choriste.getDateNaissance().toString());
        value.put(ChoristeRepository.PUPITRE,choriste.getPupiptre());
        value.put(ChoristeRepository.ROLE,choriste.getRole());
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
            try {
                choriste.setDateNaissance(new SimpleDateFormat("dd/MM/yyyy").parse(c.getString(c.getColumnIndex(DATENAISSANCE))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            choriste.setRole(c.getString(c.getColumnIndex(ROLE)));
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
            try {
                choriste.setDateNaissance(new SimpleDateFormat("dd/MM/yyyy").parse(c.getString(c.getColumnIndex(DATENAISSANCE))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            choriste.setRole(c.getString(c.getColumnIndex(ROLE)));
            choriste.setContact(c.getString(c.getColumnIndex(CONTACT)));
            choriste.setPupiptre(c.getString(c.getColumnIndex(PUPITRE)));
            choriste.setProfession(c.getString(c.getColumnIndex(PROFESSION)));
        }
        return  choriste;
    }
}
