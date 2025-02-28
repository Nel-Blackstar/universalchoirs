package com.blackstar.MkOpportunity.Mychoir.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.blackstar.MkOpportunity.Mychoir.Config.DBControleur;
import com.blackstar.MkOpportunity.Mychoir.models.Finance;

import java.util.ArrayList;
import java.util.List;

public class FinanceRepository extends DBControleur {
    public static final String TABLE_NAME = "Finances";
    public static final String KEY = "id";
    public static final String DATE = "date";
    public static final String LIBELLER = "libeller";
    public static final String TYPE = "type";
    public static final String MONTANT = "montant";
    public static final String TABLE_CREATE = "CREATE TABLE " +
            TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DATE + " TEXT, " +
            LIBELLER + " TEXT,"+
            TYPE + " TEXT,"+
            MONTANT + " REAL);";
    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " +
            TABLE_NAME + ";";

    public FinanceRepository(Context pContext) {
        super(pContext);
    }

    public void save(Finance finance){
        ContentValues value = new ContentValues();
        value.put(FinanceRepository.DATE,finance.getDate());
        value.put(FinanceRepository.LIBELLER,finance.getLibeller());
        value.put(FinanceRepository.TYPE,finance.getType());
        value.put(FinanceRepository.MONTANT,finance.getMontant());
        mDb.insert(FinanceRepository.TABLE_NAME, null, value);
    }
    public void delete(long id) {
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[]{String.valueOf(id)});
    }
    public void clean() {
        mDb.delete(TABLE_NAME, KEY + " > ?", new String[]{String.valueOf(0)});
    }
    public Finance find(long id) {
        Cursor c= mDb.rawQuery("select "+KEY+" as _id, * from " + TABLE_NAME + " where "+KEY+" = ?", new String[]{String.valueOf(id)});
        Finance finance = new Finance();
        while (c.moveToNext()) {
            finance.setId(c.getLong(c.getColumnIndex(KEY)));
            finance.setDate(c.getString(c.getColumnIndex(DATE)));
            finance.setLibeller(c.getString(c.getColumnIndex(LIBELLER)));
            finance.setMontant(c.getDouble(c.getColumnIndex(MONTANT)));
            finance.setType(c.getString(c.getColumnIndex(TYPE)));
        }
        return  finance;
    }

    public void  update(Finance finance){
        ContentValues value = new ContentValues();
        value.put(FinanceRepository.DATE,finance.getDate());
        value.put(FinanceRepository.LIBELLER,finance.getLibeller());
        value.put(FinanceRepository.MONTANT,finance.getMontant());
        value.put(FinanceRepository.TYPE,finance.getType());
        mDb.update(TABLE_NAME, value, KEY + " = ?", new String[]{String.valueOf(finance.getId())});
    }

    public List<Finance> findAll(){
        List<Finance> finances=new ArrayList<Finance>();
        Cursor c= mDb.rawQuery("select "+KEY+" as _id, * from " + TABLE_NAME + " where id > ? ORDER BY "+KEY+" DESC ", new String[]{"0"});
        while (c.moveToNext()){
            Finance finance=new Finance();
            finance.setId(c.getLong(c.getColumnIndex(KEY)));
            finance.setDate(c.getString(c.getColumnIndex(DATE)));
            finance.setLibeller(c.getString(c.getColumnIndex(LIBELLER)));
            finance.setMontant(c.getDouble(c.getColumnIndex(MONTANT)));
            finance.setType(c.getString(c.getColumnIndex(TYPE)));
            finances.add(finance);
        }
        c.close();
        return finances;
    }

    public List<Finance> findByDate(String cat) {
        List<Finance> finances=new ArrayList<Finance>();
        Cursor c= mDb.rawQuery("select "+KEY+" as _id, * from " + TABLE_NAME + " where "+DATE+" >= ? ORDER BY "+DATE+" ASC", new String[]{cat});
        while (c.moveToNext()){
            Finance finance=new Finance();
            finance.setId(c.getLong(c.getColumnIndex(KEY)));
            finance.setDate(c.getString(c.getColumnIndex(DATE)));
            finance.setLibeller(c.getString(c.getColumnIndex(LIBELLER)));
            finance.setMontant(c.getDouble(c.getColumnIndex(MONTANT)));
            finance.setType(c.getString(c.getColumnIndex(TYPE)));
            finances.add(finance);
        }
        c.close();
        return finances;
    }

    public Finance last() {
        Cursor c= mDb.rawQuery("select "+KEY+" as _id, * from " + TABLE_NAME + " ORDER BY "+KEY+" DESC LIMIT ?", new String[]{});
        Finance finance = new Finance();
        while (c.moveToNext()) {
            finance.setId(c.getLong(c.getColumnIndex(KEY)));
            finance.setDate(c.getString(c.getColumnIndex(DATE)));
            finance.setLibeller(c.getString(c.getColumnIndex(LIBELLER)));
            finance.setMontant(c.getDouble(c.getColumnIndex(MONTANT)));
            finance.setType(c.getString(c.getColumnIndex(TYPE)));
        }
        return  finance;
    }

    public List<Finance> findAllSorties(){
        List<Finance> finances=new ArrayList<Finance>();
            Cursor c= mDb.rawQuery("select "+KEY+" as _id, * from " + TABLE_NAME + " where type = ? ORDER BY "+KEY+" DESC ", new String[]{"Sortie"});while (c.moveToNext()){
            Finance finance=new Finance();
            finance.setId(c.getLong(c.getColumnIndex(KEY)));
            finance.setDate(c.getString(c.getColumnIndex(DATE)));
            finance.setLibeller(c.getString(c.getColumnIndex(LIBELLER)));
            finance.setMontant(c.getDouble(c.getColumnIndex(MONTANT)));
            finance.setType(c.getString(c.getColumnIndex(TYPE)));
            finances.add(finance);
        }
        c.close();
        return finances;
    }

    public List<Finance> findAllEntrees(){
        List<Finance> finances=new ArrayList<Finance>();
            Cursor c= mDb.rawQuery("select "+KEY+" as _id, * from " + TABLE_NAME + " where type = ? ORDER BY "+KEY+" DESC ", new String[]{"Entree"}); while (c.moveToNext()){
            Finance finance=new Finance();
            finance.setId(c.getLong(c.getColumnIndex(KEY)));
            finance.setDate(c.getString(c.getColumnIndex(DATE)));
            finance.setLibeller(c.getString(c.getColumnIndex(LIBELLER)));
            finance.setMontant(c.getDouble(c.getColumnIndex(MONTANT)));
            finance.setType(c.getString(c.getColumnIndex(TYPE)));
            finances.add(finance);
        }
        c.close();
        return finances;
    }
}
