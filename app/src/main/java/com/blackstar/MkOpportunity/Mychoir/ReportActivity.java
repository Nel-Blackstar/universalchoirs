package com.blackstar.MkOpportunity.Mychoir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.blackstar.MkOpportunity.Mychoir.Repositories.EvenementRepository;
import com.blackstar.MkOpportunity.Mychoir.Repositories.FinanceRepository;
import com.blackstar.MkOpportunity.Mychoir.adapters.EvenementAdapter;
import com.blackstar.MkOpportunity.Mychoir.adapters.FinanceAdapter;
import com.blackstar.MkOpportunity.Mychoir.models.Evenement;
import com.blackstar.MkOpportunity.Mychoir.models.Finance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportActivity extends AppCompatActivity {

    String type;
    DatePicker dateDebut;
    DatePicker dateFin;
    Button valider;
    ScrollView donnees;
    ListView rapport;
    LinearLayout resultats;
    TextView entrees;
    TextView solde;
    TextView sorties;
    CardView cardView;
    EvenementRepository evenementRepository;
    FinanceRepository financeRepository;
    List<Evenement> evenements=new ArrayList<Evenement>();
    List<Finance> finances=new ArrayList<Finance>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        type=getIntent().getExtras().get("type").toString();
        cardView=findViewById(R.id.card_view);
        dateDebut=findViewById(R.id.dateDebut);
        dateFin=findViewById(R.id.dateFin);
        entrees=findViewById(R.id.entree);
        sorties=findViewById(R.id.sortie);
        solde=findViewById(R.id.ratio);
        valider=findViewById(R.id.startReport);
        donnees=findViewById(R.id.donnees);
        rapport=findViewById(R.id.rapport);
        resultats=findViewById(R.id.resultats);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donnees.setVisibility(View.GONE);
                // date de debut
                String dateD;
                String jour=null;
                String mois=null;
                Integer moisInt=0;
                if (dateDebut.getDayOfMonth()<10){
                    jour="0"+dateDebut.getDayOfMonth();
                }else{
                    jour=String.valueOf(dateDebut.getDayOfMonth());
                }
                moisInt=dateDebut.getMonth()+1;
                if (moisInt<10){
                    mois="0"+moisInt;
                }else{
                    mois=moisInt.toString();
                }
                dateD=jour+"/"+mois+"/"+dateDebut.getYear();
                //date de fin
                String dateF;
                if (dateFin.getDayOfMonth()<10){
                    jour="0"+dateFin.getDayOfMonth();
                }else{
                    jour=String.valueOf(dateFin.getDayOfMonth());
                }
                moisInt=dateFin.getMonth()+1;
                if (moisInt<10){
                    mois="0"+moisInt;
                }else{
                    mois=moisInt.toString();
                }
                dateF=jour+"/"+mois+"/"+dateFin.getYear();
                //convertion de la date debut
                Date Debut=new Date();
                Date Fin=new Date();
                try {
                    Debut=new SimpleDateFormat("dd/MM/yyyy").parse(dateD);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    Fin=new SimpleDateFormat("dd/MM/yyyy").parse(dateF);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                switch (type){
                    case "Evenements":
                    evenementRepository=new EvenementRepository(ReportActivity.this);
                    evenementRepository.open();
                        for (Evenement evenement : evenementRepository.findAll()){
                            Date dateEvents=new Date();
                            try {
                                dateEvents=new SimpleDateFormat("dd/MM/yyyy").parse(evenement.date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            if (dateEvents.before(Fin) && dateEvents.after(Debut) ){
                                evenements.add(evenement);
                            }
                            if (dateEvents.equals(Fin) || dateEvents.equals(Debut) ){
                                evenements.add(evenement);
                            }
                        }
                        EvenementAdapter evenementAdapter=new EvenementAdapter(getApplicationContext(),R.layout.adapter3,evenements);
                        rapport.setAdapter(evenementAdapter);
                        setTitle(getString(R.string.result_period));
                        resultats.setVisibility(View.VISIBLE);
                        rapport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent=new Intent(ReportActivity.this,ShowActivity.class);
                                intent.putExtra("type","evenement");
                                intent.putExtra("id",evenements.get(position).getId());
                                startActivity(intent);
                            }
                        });
                        break;
                    case "Finances":
                    financeRepository=new FinanceRepository(ReportActivity.this);
                    financeRepository.open();
                        for (Finance finance : financeRepository.findAll()){
                            Date dateEvents=new Date();
                            try {
                                dateEvents=new SimpleDateFormat("dd/MM/yyyy").parse(finance.date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            if (dateEvents.before(Fin) && dateEvents.after(Debut) ){
                                finances.add(finance);
                            }
                            if (dateEvents.equals(Fin) || dateEvents.equals(Debut) ){
                                finances.add(finance);
                            }
                        }
                        FinanceAdapter financeAdapter=new FinanceAdapter(getApplicationContext(),R.layout.adapter,finances);
                        rapport.setAdapter(financeAdapter);
                        setTitle(getString(R.string.result_period));
                        cardView.setVisibility(View.VISIBLE);
                        resultats.setVisibility(View.VISIBLE);
                        rapport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent=new Intent(ReportActivity.this,ShowActivity.class);
                                intent.putExtra("type","finance");
                                intent.putExtra("id",finances.get(position).getId());
                                startActivity(intent);
                            }
                        });
                        Double totalEntrees=0.0;
                        Double totalSorties=0.0;
                        for (Finance finance: finances){
                            if (finance.getType().equals("Entree")){
                                totalEntrees+=finance.getMontant();
                            }else{
                                totalSorties+=finance.getMontant();
                            }
                        }
                        entrees.setText(totalEntrees.toString());
                        sorties.setText(totalSorties.toString());
                        Double ratio=totalEntrees-totalSorties;

                        solde.setText(ratio.toString());
                        break;
                }
            }
        });
    }
}
