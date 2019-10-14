package com.blackstar.MkOpportunity.Mychoir.Forms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.blackstar.MkOpportunity.Mychoir.Controleurs.ChantRepository;
import com.blackstar.MkOpportunity.Mychoir.Controleurs.EvenementRepository;
import com.blackstar.MkOpportunity.Mychoir.Controleurs.FinanceRepository;
import com.blackstar.MkOpportunity.Mychoir.LireChantActivity;
import com.black.chorale.R;
import com.blackstar.MkOpportunity.Mychoir.models.Chant;
import com.blackstar.MkOpportunity.Mychoir.models.Evenement;
import com.blackstar.MkOpportunity.Mychoir.models.Finance;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ChoirForm extends AppCompatActivity {
public Button save;
public  Button reset;
/* gestion du chant */
public EditText titreData;
public  EditText refrainData;
public  EditText contenueData;
public ChantRepository chantRepository;
Chant chant;
/*fin gestion du chant */

    /* gestion de finances */
Finance finance;
    public DatePicker dateData;
    public  EditText libellerData;
    public Spinner typeData;
    public EditText montantData;
    public FinanceRepository financeRepository;
    /*fin  gestion de finances */

    /* gestion d'evenement */
Evenement evenement;
    public DatePicker dateEventData;
    public  EditText raisonData;
    public  EditText typeEventData;
    public EditText concernerData;
    public EvenementRepository evenementRepository;
    /*fin  gestion d"evenement */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chantRepository=new ChantRepository(getApplicationContext());
        chantRepository.open();
        financeRepository=new FinanceRepository(getApplicationContext());
        financeRepository.open();
        evenementRepository=new EvenementRepository(getApplicationContext());
        evenementRepository.open();

        Intent intent=getIntent();
        Bundle bd=intent.getExtras();
        String type=bd.get("type").toString();
        switch (type){
            case "Chant":
                chant=new Chant();
                chant.setProprietaire("Blackstar");
                chant.setCategorie(bd.get("categorie").toString());
                setContentView(R.layout.activity_chant_form);
                save=findViewById(R.id.save);
                reset=findViewById(R.id.reset);
                titreData=findViewById(R.id.titredata);
                refrainData=findViewById(R.id.refraindata);
                contenueData=findViewById(R.id.contenuedata);
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
                        chantRepository.save(chant);
                        chant=chantRepository.last();
                        Toast.makeText(getApplicationContext(),getString(R.string.save_success),Toast.LENGTH_LONG).show();
                        finish();
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
            case "Finances":
               finance=new Finance();
                setContentView(R.layout.activity_finance_form);
                dateData=findViewById(R.id.date);
                libellerData=findViewById(R.id.informationdata);
                typeData=findViewById(R.id.typefinance);
                montantData=findViewById(R.id.montant);
                save=findViewById(R.id.save);
                reset=findViewById(R.id.reset);
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
                        switch (typeData.getSelectedItemPosition()){
                            case 0:
                                finance.setType("Entree");
                                break;
                            case 1:
                                finance.setType("Sortie");
                                break;

                        }
                        finance.setLibeller(libellerData.getText().toString());
                        String date;
                        date=dateData.getDayOfMonth()+"/"+dateData.getMonth()+"/"+dateData.getYear();
                        try {
                            finance.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(date));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Double montant=null;
                        try {
                            montant=Double.parseDouble(montantData.getText().toString());
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        if (montant == null) {
                            montant=0.0;
                        }
                        finance.setMontant(montant);
                       financeRepository.save(finance);
                       Toast.makeText(getApplicationContext(),getString(R.string.save_success),Toast.LENGTH_LONG).show();
                       finish();
                    }
                });
                break;
            case  "Evenements":
                setContentView(R.layout.activity_evenement_form);
                save=findViewById(R.id.save);
                reset=findViewById(R.id.reset);
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
                        chantRepository.save(chant);
                        chant=chantRepository.last();
                        Toast.makeText(getApplicationContext(),getString(R.string.save_success),Toast.LENGTH_LONG).show();
                        finish();
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
