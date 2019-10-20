package com.blackstar.MkOpportunity.Mychoir.Forms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.blackstar.MkOpportunity.Mychoir.Repositories.ChantRepository;
import com.blackstar.MkOpportunity.Mychoir.R;
import com.blackstar.MkOpportunity.Mychoir.Repositories.ChoristeRepository;
import com.blackstar.MkOpportunity.Mychoir.Repositories.EvenementRepository;
import com.blackstar.MkOpportunity.Mychoir.Repositories.FinanceRepository;
import com.blackstar.MkOpportunity.Mychoir.models.Chant;
import com.blackstar.MkOpportunity.Mychoir.models.Choriste;
import com.blackstar.MkOpportunity.Mychoir.models.Evenement;
import com.blackstar.MkOpportunity.Mychoir.models.Finance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ChoirFormUpdate extends AppCompatActivity {
public Button save;
public  Button reset;
public EditText titreData;
public  EditText refrainData;
public  EditText contenueData;
public ChantRepository chantRepository;

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
    public  EditText lieuData;
    public EditText concernerData;
    public EvenementRepository evenementRepository;
    public  Long id;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chantRepository=new ChantRepository(getApplicationContext());
        chantRepository.open();
        Intent intent=getIntent();
        Bundle bd=intent.getExtras();
        String type=bd.get("type").toString();
        id=Long.parseLong(bd.get("id").toString());
        switch (type){
            case "Chant":
                final Chant chant=chantRepository.find(id);
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
                       finish();
                    }
                });
            break;
            case "finance" :
                financeRepository=new FinanceRepository(getApplicationContext());
                financeRepository.open();
                finance=financeRepository.find(id);
                setContentView(R.layout.activity_finance_form);
                dateData=findViewById(R.id.date);
                libellerData=findViewById(R.id.informationdata);
                typeData=findViewById(R.id.typefinance);
                montantData=findViewById(R.id.montant);
                libellerData.setText(finance.getLibeller());
                montantData.setText(finance.getMontant().toString());
                switch (finance.getType()) {
                    case "Entree":
                        typeData.setSelection(0);
                        break;
                    case "Sortie":
                        typeData.setSelection(1);
                        break;
                }
                Date date=new Date();
                try{
                    date=new SimpleDateFormat("dd/MM/yyyy").parse(finance.getDate());
                }catch (Exception e){
                    e.printStackTrace();
                }
                Calendar calendar=Calendar.getInstance();
                dateData.updateDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
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
                        financeRepository.update(finance);
                        Toast.makeText(getApplicationContext(),getString(R.string.save_success),Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
                break;
            case "evenement" :
                evenementRepository=new EvenementRepository(getApplicationContext());
                evenementRepository.open();
                /*
                ActionBar actionBar=getSupportActionBar();
                actionBar.setIcon(R.drawable.ic_event_black_24dp);
                actionBar.setDisplayUseLogoEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
                */
                evenement=evenementRepository.find(id);
                setContentView(R.layout.activity_evenement_form);
                save=findViewById(R.id.save);
                reset=findViewById(R.id.reset);
                raisonData=findViewById(R.id.raisondata);
                raisonData.setText(evenement.getRaison());
                lieuData=findViewById(R.id.lieu);
                lieuData.setText(evenement.getLieu());
                dateEventData=findViewById(R.id.date);
                concernerData=findViewById(R.id.concerner);
                concernerData.setText(evenement.getConcerne());
                Calendar calendier=Calendar.getInstance();
                dateEventData.updateDate(calendier.get(Calendar.YEAR),calendier.get(Calendar.MONTH),calendier.get(Calendar.DAY_OF_MONTH));
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
                        evenementRepository.update(evenement);
                        Toast.makeText(getApplicationContext(),getString(R.string.save_success),Toast.LENGTH_LONG).show();
                       // evenement.setDate(date.toString());
                        finish();
                    }
                });
                break;

            case  "choriste":
                choristeRepository=new ChoristeRepository(this);
                choristeRepository.open();
                final  List<String> sexes=Arrays.asList(new String[]{"Masculin","Feminin"});
                final List<String> pupitres= Arrays.asList(new String[]{"Soprane","Alto","Tenor","Bass"});
                final  List<String> roles=Arrays.asList(new String[]{"Membre","President(e)","Censeur","Secretaire","Maitre de Coeur","Directeur Technique"});
                choriste=choristeRepository.find(id);
                setContentView(R.layout.activity_choriste_form);
                ArrayAdapter adapter;
                nomData=findViewById(R.id.nomData);
                nomData.setText(choriste.getNom());
                prenomData=findViewById(R.id.prenomData);
                prenomData.setText(choriste.getPrenom());
                contactData=findViewById(R.id.contactData);
                contactData.setText(choriste.getContact());
                professionData=findViewById(R.id.professionData);
                professionData.setText(choriste.getProfession());
                sexeData=findViewById(R.id.sexeData);
                adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,sexes);
                sexeData.setAdapter(adapter);
                sexeData.setSelection(sexes.indexOf(choriste.getSexe()));
                pupitreData=findViewById(R.id.pupitreData);
                adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,pupitres);
                pupitreData.setAdapter(adapter);
                pupitreData.setSelection(pupitres.indexOf(choriste.getPupiptre()));
                roleData=findViewById(R.id.roleData);
                adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,roles);
                roleData.setAdapter(adapter);
                roleData.setSelection(roles.indexOf(choriste.getRole()));
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
                        choriste.setPupiptre(pupitres.get(pupitreData.getSelectedItemPosition()));
                        choriste.setRole(roles.get(roleData.getSelectedItemPosition()));
                        choriste.setSexe(sexes.get(sexeData.getSelectedItemPosition()));
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
                        choristeRepository.update(choriste);
                        Toast.makeText(getApplicationContext(),getString(R.string.save_success),Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                break;
            default:
                Log.v("erreur","Erreur de type");
                break;
        }
    }
}
