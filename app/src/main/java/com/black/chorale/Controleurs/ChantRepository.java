package com.black.chorale.Controleurs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.black.chorale.Config.DBControleur;
import com.black.chorale.models.Chant;

import java.util.ArrayList;
import java.util.List;

public class ChantRepository extends DBControleur {
    public static final String TABLE_NAME = "Chants";
    public static final String KEY = "id";
    public static final String CATEGORIE = "categorie";
    public static final String TITRE = "titre";
    public static final String REFRAIN = "refrain";
    public static final String CONTENUE = "contenue";
    public static final String PROPRIETAIRE = "proprietaire";
    public static final String TABLE_CREATE = "CREATE TABLE " +
            TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CATEGORIE + " TEXT, " +
            TITRE + " TEXT,"+
            REFRAIN + " TEXT,"+
            CONTENUE + " TEXT,"+
            PROPRIETAIRE + " TEXT);";
    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " +
            TABLE_NAME + ";";

    public ChantRepository(Context pContext) {
        super(pContext);
    }

    public void save(Chant chant){
        ContentValues value = new ContentValues();
        value.put(ChantRepository.TITRE,chant.getTitre());
        value.put(ChantRepository.REFRAIN,chant.getRefrain());
        value.put(ChantRepository.CATEGORIE,chant.getCategorie());
        value.put(ChantRepository.CONTENUE,chant.getContenue());
        value.put(ChantRepository.PROPRIETAIRE,chant.getProprietaire());
        mDb.insert(ChantRepository.TABLE_NAME, null, value);
    }
    public void delete(long id) {
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[]{String.valueOf(id)});
    }
    public Chant find(long id) {
        Cursor c= mDb.rawQuery("select "+KEY+" as _id, * from " + TABLE_NAME + " where id = ?", new String[]{String.valueOf(id)});
        Chant chant = new Chant();
        while (c.moveToNext()) {
            chant.setId(c.getLong(c.getColumnIndex(KEY)));
            chant.setCategorie(c.getString(c.getColumnIndex(CATEGORIE)));
            chant.setTitre(c.getString(c.getColumnIndex(TITRE)));
            chant.setRefrain(c.getString(c.getColumnIndex(REFRAIN)));
            chant.setContenue(c.getString(c.getColumnIndex(CONTENUE)));
            chant.setProprietaire(c.getString(c.getColumnIndex(PROPRIETAIRE)));
        }
        return  chant;
    }

    public void  update(Chant chant){
        ContentValues value = new ContentValues();
        value.put(ChantRepository.TITRE,chant.getTitre());
        value.put(ChantRepository.REFRAIN,chant.getRefrain());
        value.put(ChantRepository.CATEGORIE,chant.getCategorie());
        value.put(ChantRepository.CONTENUE,chant.getContenue());
        value.put(ChantRepository.PROPRIETAIRE,chant.getProprietaire());
        mDb.update(TABLE_NAME, value, KEY + " = ?", new String[]{String.valueOf(chant.getId())});
    }

    public List<Chant> findAll(){
        List<Chant> chants=new ArrayList<Chant>();
        Cursor c= mDb.rawQuery("select "+KEY+" as _id, * from " + TABLE_NAME + " where id > ?", new String[]{"0"});
        while (c.moveToNext()){
            Chant chant=new Chant();
            chant.setId(c.getLong(c.getColumnIndex(KEY)));
            chant.setCategorie(c.getString(c.getColumnIndex(CATEGORIE)));
            chant.setTitre(c.getString(c.getColumnIndex(TITRE)));
            chant.setRefrain(c.getString(c.getColumnIndex(REFRAIN)));
            chant.setContenue(c.getString(c.getColumnIndex(CONTENUE)));
            chant.setProprietaire(c.getString(c.getColumnIndex(PROPRIETAIRE)));
            chants.add(chant);
        }
        c.close();
        return chants;
    }

    public List<Chant> findByCategorie(String cat) {
        List<Chant> chants=new ArrayList<Chant>();
        Cursor c= mDb.rawQuery("select "+KEY+" as _id, * from " + TABLE_NAME + " where "+CATEGORIE+" = ? ORDER BY "+TITRE+" ASC", new String[]{cat});
        while (c.moveToNext()){
            Chant chant=new Chant();
            chant.setId(c.getLong(c.getColumnIndex(KEY)));
            chant.setCategorie(c.getString(c.getColumnIndex(CATEGORIE)));
            chant.setTitre(c.getString(c.getColumnIndex(TITRE)));
            chant.setRefrain(c.getString(c.getColumnIndex(REFRAIN)));
            chant.setContenue(c.getString(c.getColumnIndex(CONTENUE)));
            chant.setProprietaire(c.getString(c.getColumnIndex(PROPRIETAIRE)));
            chants.add(chant);
        }
        c.close();
        return chants;
    }

    public Chant last() {
        Cursor c= mDb.rawQuery("select "+KEY+" as _id, * from " + TABLE_NAME + " ORDER BY id DESC LIMIT ?", new String[]{String.valueOf("1")});
        Chant chant = new Chant();
        while (c.moveToNext()) {
            chant.setId(c.getLong(c.getColumnIndex(KEY)));
            chant.setCategorie(c.getString(c.getColumnIndex(CATEGORIE)));
            chant.setTitre(c.getString(c.getColumnIndex(TITRE)));
            chant.setRefrain(c.getString(c.getColumnIndex(REFRAIN)));
            chant.setContenue(c.getString(c.getColumnIndex(CONTENUE)));
            chant.setProprietaire(c.getString(c.getColumnIndex(PROPRIETAIRE)));
        }
        return  chant;
    }
}
