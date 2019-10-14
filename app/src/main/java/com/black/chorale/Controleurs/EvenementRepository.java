package com.black.chorale.Controleurs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.black.chorale.Config.DBControleur;
import com.black.chorale.models.Evenement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EvenementRepository extends DBControleur {
    public static final String TABLE_NAME = "Evenements";
    public static final String KEY = "id";
    public static final String DATE = "date";
    public static final String LIEU = "lieu";
    public static final String CONCERNE = "concerne";
    public static final String RAISON = "raison";
    public static final String TABLE_CREATE = "CREATE TABLE " +
            TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DATE + " TEXT, " +
            LIEU + " TEXT,"+
            CONCERNE + " TEXT,"+
            RAISON + " TEXT);";
    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " +
            TABLE_NAME + ";";

    public EvenementRepository(Context pContext) {
        super(pContext);
    }

    public void save(Evenement evenement){
        ContentValues value = new ContentValues();
        value.put(EvenementRepository.DATE,evenement.getDate().toString());
        value.put(EvenementRepository.LIEU,evenement.getLieu());
        value.put(EvenementRepository.CONCERNE,evenement.getConcerne());
        value.put(EvenementRepository.RAISON,evenement.getRaison());
        mDb.insert(EvenementRepository.TABLE_NAME, null, value);
    }
    public void delete(long id) {
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[]{String.valueOf(id)});
    }
    public Evenement find(long id) {
        Cursor c= mDb.rawQuery("select "+KEY+" as _id, * from " + TABLE_NAME + " where "+KEY+" = ?", new String[]{String.valueOf(id)});
        Evenement evenement = new Evenement();
        while (c.moveToNext()) {
            evenement.setId(c.getLong(c.getColumnIndex(KEY)));
            try {
                evenement.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(c.getString(c.getColumnIndex(DATE))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            evenement.setLieu(c.getString(c.getColumnIndex(LIEU)));
            evenement.setConcerne(c.getString(c.getColumnIndex(CONCERNE)));
            evenement.setRaison(c.getString(c.getColumnIndex(RAISON)));
        }
        return  evenement;
    }

    public void  update(Evenement evenement){
        ContentValues value = new ContentValues();
        value.put(EvenementRepository.DATE,evenement.getDate().toString());
        value.put(EvenementRepository.LIEU,evenement.getLieu());
        value.put(EvenementRepository.CONCERNE,evenement.getConcerne());
        value.put(EvenementRepository.RAISON,evenement.getRaison());
        mDb.update(TABLE_NAME, value, KEY + " = ?", new String[]{String.valueOf(evenement.getId())});
    }

    public List<Evenement> findAll(){
        List<Evenement> evenements=new ArrayList<Evenement>();
        Cursor c= mDb.rawQuery("select "+KEY+" as _id, * from " + TABLE_NAME + " where id > ?", new String[]{"0"});
        while (c.moveToNext()){
            Evenement evenement=new Evenement();
            evenement.setId(c.getLong(c.getColumnIndex(KEY)));
            try {
                evenement.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(c.getString(c.getColumnIndex(DATE))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            evenement.setLieu(c.getString(c.getColumnIndex(LIEU)));
            evenement.setConcerne(c.getString(c.getColumnIndex(CONCERNE)));
            evenement.setRaison(c.getString(c.getColumnIndex(RAISON)));
            evenements.add(evenement);
        }
        c.close();
        return evenements;
    }

    public List<Evenement> findByDate(String cat) {
        List<Evenement> evenements=new ArrayList<Evenement>();
        Cursor c= mDb.rawQuery("select "+KEY+" as _id, * from " + TABLE_NAME + " where "+DATE+" >= ? ORDER BY "+DATE+" ASC", new String[]{cat});
        while (c.moveToNext()){
            Evenement evenement=new Evenement();
            evenement.setId(c.getLong(c.getColumnIndex(KEY)));
            try {
                evenement.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(c.getString(c.getColumnIndex(DATE))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            evenement.setLieu(c.getString(c.getColumnIndex(LIEU)));
            evenement.setConcerne(c.getString(c.getColumnIndex(CONCERNE)));
            evenement.setRaison(c.getString(c.getColumnIndex(RAISON)));
            evenements.add(evenement);
        }
        c.close();
        return evenements;
    }

    public Evenement last() {
        Cursor c= mDb.rawQuery("select "+KEY+" as _id, * from " + TABLE_NAME + " ORDER BY "+KEY+" DESC LIMIT ?", new String[]{});
        Evenement evenement = new Evenement();
        while (c.moveToNext()) {
            evenement.setId(c.getLong(c.getColumnIndex(KEY)));
            try {
                evenement.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(c.getString(c.getColumnIndex(DATE))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            evenement.setLieu(c.getString(c.getColumnIndex(LIEU)));
            evenement.setConcerne(c.getString(c.getColumnIndex(CONCERNE)));
            evenement.setRaison(c.getString(c.getColumnIndex(RAISON)));
        }
        return  evenement;
    }
}
