package com.blackstar.MkOpportunity.Mychoir.Config;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Html;
import android.text.Spanned;
import android.widget.Toast;

import com.blackstar.MkOpportunity.Mychoir.Controleurs.ChantRepository;
import com.blackstar.MkOpportunity.Mychoir.Controleurs.ChoristeRepository;
import com.blackstar.MkOpportunity.Mychoir.Controleurs.EvenementRepository;
import com.blackstar.MkOpportunity.Mychoir.Controleurs.FinanceRepository;
import com.black.chorale.R;
import com.blackstar.MkOpportunity.Mychoir.models.Chant;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DatabaseHandler extends SQLiteOpenHelper {
        public static final String CHANT_TABLE_CREATE = ChantRepository.TABLE_CREATE;
        public static final String CHANT_TABLE_DROP = ChantRepository.TABLE_DROP;
        public static final String EVENEMENTS_TABLE_CREATE = EvenementRepository.TABLE_CREATE;
        public static final String EVENEMENTS_TABLE_DROP = EvenementRepository.TABLE_DROP;
        public static final String FINANCES_TABLE_CREATE = FinanceRepository.TABLE_CREATE;
        public static final String FINANCES_TABLE_DROP = FinanceRepository.TABLE_DROP;
        public static final String CHORISTES_TABLE_CREATE = ChoristeRepository.TABLE_CREATE;
        public static final String CHORISTES_TABLE_DROP = ChoristeRepository.TABLE_DROP;
        public  Context bcontext=null;
        public  SQLiteDatabase db=null;

        public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            bcontext=context;
            db=this.getWritableDatabase();
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CHANT_TABLE_CREATE);
            db.execSQL(EVENEMENTS_TABLE_CREATE);
            db.execSQL(FINANCES_TABLE_CREATE);
            db.execSQL(CHORISTES_TABLE_CREATE);
            try {
                String jsonData=readJsonData();
                JSONArray allChants=new JSONArray(jsonData);
                for (int i=0;i<allChants.length();i++){
                    JSONObject jsonObject=allChants.getJSONObject(i);
                        Chant chant= new Chant();
                        chant.setCategorie(jsonObject.getString("categorie"));
                        Spanned contenue = Html.fromHtml(jsonObject.getString("contenue"));
                        chant.setContenue(contenue.toString());
                        Spanned refrain = Html.fromHtml(jsonObject.getString("refrain"));
                        chant.setRefrain(refrain.toString());
                        chant.setTitre(jsonObject.getString("titre"));
                    ContentValues value = new ContentValues();
                    value.put(ChantRepository.TITRE,chant.getTitre());
                    value.put(ChantRepository.REFRAIN,chant.getRefrain());
                    value.put(ChantRepository.CATEGORIE,chant.getCategorie());
                    value.put(ChantRepository.CONTENUE,chant.getContenue());
                    value.put(ChantRepository.PROPRIETAIRE,"Blackstar");
                    db.insert(ChantRepository.TABLE_NAME, null, value);
                }
                Toast.makeText(bcontext,R.string.setup,Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    private String readJsonData()  throws Exception{
        InputStream inputStream=null;
        StringBuilder builder=new StringBuilder();
        try {
            String jsondata=null;
            inputStream=bcontext.getResources().openRawResource(R.raw.chants);
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            while ((jsondata=bufferedReader.readLine()) != null){
                builder.append(jsondata);
            }
        }catch  (Exception e) {
            e.printStackTrace();
        }finally {
            if (inputStream !=null){
                inputStream.close();
            }
        }
        return new String(builder);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CHANT_TABLE_DROP);
        db.execSQL(EVENEMENTS_TABLE_DROP);
        db.execSQL(FINANCES_TABLE_DROP);
        db.execSQL(CHORISTES_TABLE_DROP);
        onCreate(db);
    }
}
