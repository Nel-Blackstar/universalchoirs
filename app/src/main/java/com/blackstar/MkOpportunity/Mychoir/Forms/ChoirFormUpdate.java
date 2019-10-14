package com.blackstar.MkOpportunity.Mychoir.Forms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blackstar.MkOpportunity.Mychoir.Controleurs.ChantRepository;
import com.blackstar.MkOpportunity.Mychoir.LireChantActivity;
import com.black.chorale.R;
import com.blackstar.MkOpportunity.Mychoir.models.Chant;

public class ChoirFormUpdate extends AppCompatActivity {
public Button save;
public  Button reset;
public EditText titreData;
public  EditText refrainData;
public  EditText contenueData;
public ChantRepository chantRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chantRepository=new ChantRepository(getApplicationContext());
        chantRepository.open();
        Intent intent=getIntent();
        Bundle bd=intent.getExtras();
        String type=bd.get("type").toString();
        switch (type){
            case "Chant":
                final Chant chant=chantRepository.find(Long.parseLong(bd.get("id").toString()));
                setContentView(R.layout.activity_chant_form);
                save=findViewById(R.id.save);
                reset=findViewById(R.id.reset);
                titreData=findViewById(R.id.titredata);
                titreData.setText(chant.getTitre());
                refrainData=findViewById(R.id.refraindata);
                refrainData.setText(chant.getRefrain());
                contenueData=findViewById(R.id.contenuedata);
                contenueData.setText(chant.getContenue());
                reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),getString(R.string.save_reset),Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chant.setTitre(titreData.getText().toString().toUpperCase());
                        chant.setRefrain(refrainData.getText().toString());
                        chant.setContenue(contenueData.getText().toString());
                        chantRepository.update(chant);
                        Toast.makeText(getApplicationContext(),getString(R.string.save_success),Toast.LENGTH_LONG).show();
                        Intent lire=new Intent(getApplicationContext(), LireChantActivity.class);
                        lire.putExtra("titre",chant.getTitre());
                        lire.putExtra("refrain",chant.getRefrain());
                        lire.putExtra("id",chant.getId());
                        lire.putExtra("categorie",chant.getCategorie());
                        lire.putExtra("contenue",chant.getContenue());
                        startActivity(lire);
                    }
                });
            break;
            default:
                Log.v("erreur","Erreur de type");
                break;
        }
    }
}
