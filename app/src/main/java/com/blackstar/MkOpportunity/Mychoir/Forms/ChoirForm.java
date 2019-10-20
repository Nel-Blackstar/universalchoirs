package com.blackstar.MkOpportunity.Mychoir.Forms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.blackstar.MkOpportunity.Mychoir.Repositories.ChantRepository;
import com.blackstar.MkOpportunity.Mychoir.Repositories.ChoristeRepository;
import com.blackstar.MkOpportunity.Mychoir.Repositories.EvenementRepository;
import com.blackstar.MkOpportunity.Mychoir.Repositories.FinanceRepository;
import com.blackstar.MkOpportunity.Mychoir.LireChantActivity;
import com.blackstar.MkOpportunity.Mychoir.R;
import com.blackstar.MkOpportunity.Mychoir.models.Chant;
import com.blackstar.MkOpportunity.Mychoir.models.Choriste;
import com.blackstar.MkOpportunity.Mychoir.models.Evenement;
import com.blackstar.MkOpportunity.Mychoir.models.Finance;

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

    /* gestion de choriste */
    Choriste choriste;
    public  EditText nomData;
    public  EditText prenomData;
    public  EditText contactData;
    public DatePicker naissanceData;
    public  EditText professionData;
    public Spinner sexeData;
    public Spinner pupitreData;
    public Spinner roleData;
    public ChoristeRepository choristeRepository;
    /*fin  gestion de choriste */

    /* gestion d'evenement */
Evenement evenement;
    public DatePicker dateEventData;
    public  EditText raisonData;
    public  EditText lieuData;
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
        choristeRepository=new ChoristeRepository(getApplicationContext());
        choristeRepository.open();

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
                        String jour=null;
                        String mois=null;
                        Integer moisInt=0;
                        if (dateData.getDayOfMonth()<10){
                            jour="0"+dateData.getDayOfMonth();
                        }else{
                            jour=String.valueOf(dateData.getDayOfMonth());
                        }
                        moisInt=dateData.getMonth()+1;
                        if (moisInt<10){
                            mois="0"+moisInt;
                        }else{
                            mois=moisInt.toString();
                        }
                        date=jour+"/"+mois+"/"+dateData.getYear();
                        finance.setDate(date);
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
                evenement=new Evenement();
                setContentView(R.layout.activity_evenement_form);
                save=findViewById(R.id.save);
                reset=findViewById(R.id.reset);
                raisonData=findViewById(R.id.raisondata);
                lieuData=findViewById(R.id.lieu);
                dateEventData=findViewById(R.id.date);
                concernerData=findViewById(R.id.concerner);
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
                        evenement.setConcerne(concernerData.getText().toString());
                        evenement.setLieu(lieuData.getText().toString());
                        evenement.setRaison(raisonData.getText().toString());
                        String date;
                        String jour=null;
                        String mois=null;
                        Integer moisInt=0;
                        if (dateEventData.getDayOfMonth()<10){
                            jour="0"+dateEventData.getDayOfMonth();
                        }else{
                            jour=String.valueOf(dateEventData.getDayOfMonth());
                        }
                        moisInt=dateEventData.getMonth()+1;
                        if (moisInt<10){
                            mois="0"+moisInt;
                        }else{
                            mois=moisInt.toString();
                        }
                        date=jour+"/"+mois+"/"+dateEventData.getYear();
                        evenement.setDate(date);
                        evenementRepository.save(evenement);
                        Toast.makeText(getApplicationContext(),getString(R.string.save_success),Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
                break;
            case  "Choriste":
                final  String[] sexes={"Masculin","Feminin"};
                final  String[] pupitres={"Soprane","Alto","Tenor","Bass"};
                final  String[] roles={"Membre","President(e)","Censeur","Secretaire","Maitre de Coeur","Directeur Technique"};
                choriste=new Choriste();
                    setContentView(R.layout.activity_choriste_form);
                    ArrayAdapter adapter;
                    nomData=findViewById(R.id.nomData);
                    prenomData=findViewById(R.id.prenomData);
                    contactData=findViewById(R.id.contactData);
                    professionData=findViewById(R.id.professionData);
                    sexeData=findViewById(R.id.sexeData);
                    adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,sexes);
                    sexeData.setAdapter(adapter);
                    pupitreData=findViewById(R.id.pupitreData);
                    adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,pupitres);
                    pupitreData.setAdapter(adapter);
                    roleData=findViewById(R.id.roleData);
                    adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,roles);
                    roleData.setAdapter(adapter);
                    save=findViewById(R.id.save);
                    reset=findViewById(R.id.reset);
                    naissanceData=findViewById(R.id.naissanceData);
                    reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),getString(R.string.save_reset),Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                    save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        choriste.setNom(nomData.getText().toString());
                        choriste.setPrenom(prenomData.getText().toString());
                        choriste.setProfession(professionData.getText().toString());
                        choriste.setContact(contactData.getText().toString());
                        choriste.setPupiptre(pupitres[pupitreData.getSelectedItemPosition()]);
                        choriste.setRole(roles[roleData.getSelectedItemPosition()]);
                        choriste.setSexe(sexes[sexeData.getSelectedItemPosition()]);
                            String date;
                            String jour=null;
                            String mois=null;
                            Integer moisInt=0;
                            if (naissanceData.getDayOfMonth()<10){
                                jour="0"+naissanceData.getDayOfMonth();
                            }else{
                                jour=String.valueOf(naissanceData.getDayOfMonth());
                            }
                            moisInt=naissanceData.getMonth()+1;
                            if (moisInt<10){
                                mois="0"+moisInt;
                            }else{
                                mois=moisInt.toString();
                            }
                            date=jour+"/"+mois+"/"+naissanceData.getYear();
                            choriste.setDateNaissance(date);
                            choristeRepository.save(choriste);
                            Toast.makeText(getApplicationContext(),getString(R.string.save_success),Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                break;
        }
    }
}
