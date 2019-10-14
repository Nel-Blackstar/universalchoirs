package com.black.chorale.Config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.black.chorale.Config.DatabaseHandler;

public abstract class DBControleur {
// Nous sommes à la première version de la base
// Si je décide de la mettre à jour, il faudra changer cet attribut
        protected final static int VERSION = 2;
        // Le nom du fichier qui représente ma base
        protected final static String NOM = "blackstar.mychoir.db";
        protected SQLiteDatabase mDb = null;
        protected DatabaseHandler mHandler = null;
        public DBControleur(Context pContext) {
            this.mHandler = new DatabaseHandler(pContext, NOM, null,VERSION);
        }
        public SQLiteDatabase open() {
        // Pas besoin de fermer la dernière base puisque getWritableDatabase s'en charge
            mDb = mHandler.getWritableDatabase();
            return mDb;
        }
        public void close() {
            mDb.close();
        }
        public SQLiteDatabase getDb() {
            return mDb;
        }
}
