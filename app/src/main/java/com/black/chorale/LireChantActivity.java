package com.black.chorale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.black.chorale.Controleurs.ChantRepository;
import com.black.chorale.Forms.ChoirForm;
import com.black.chorale.Forms.ChoirFormUpdate;
import com.black.chorale.models.Chant;

public class LireChantActivity extends AppCompatActivity {
        TextView titre=null;
        TextView refrain=null;
        TextView contenue=null;
         Chant chant;
         ChantRepository chantRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        chantRepository=new ChantRepository(getApplicationContext());
        chantRepository.open();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lire_chant);
        titre= findViewById(R.id.titreChant);
        refrain=findViewById(R.id.refrainChant);
        contenue=findViewById(R.id.contenueChant);
        Intent intent=getIntent();
        Bundle bd=intent.getExtras();
        chant=new Chant();
        if (bd!=null){
            chant.setId((Long) bd.get("id"));
            chant.setTitre(bd.get("titre").toString());
            chant.setRefrain(bd.get("refrain").toString());
            chant.setContenue(bd.get("contenue").toString());
            chant.setCategorie(bd.get("categorie").toString());
            this.setTitle(chant.getTitre());
            titre.setText(chant.getTitre());
            refrain.setText(chant.getRefrain());
            contenue.setText(chant.getContenue());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.update,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        if (item.getItemId()==R.id.update){
            i=new Intent(getApplicationContext(), ChoirFormUpdate.class);
            i.putExtra("type","Chant");
            i.putExtra("id",chant.getId());
            startActivity(i);
        }
        if (item.getItemId()==R.id.delete){
                chantRepository.delete(chant.getId());
            Toast.makeText(getApplicationContext(),getString(R.string.delete_success),Toast.LENGTH_LONG).show();
                finish();
            i=new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
