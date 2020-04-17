package com.blackstar.MkOpportunity.Mychoir;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blackstar.MkOpportunity.Mychoir.Forms.ChoirFormUpdate;
import com.blackstar.MkOpportunity.Mychoir.Repositories.ChoristeRepository;
import com.blackstar.MkOpportunity.Mychoir.Repositories.EvenementRepository;
import com.blackstar.MkOpportunity.Mychoir.Repositories.FinanceRepository;
import com.blackstar.MkOpportunity.Mychoir.models.Choriste;
import com.blackstar.MkOpportunity.Mychoir.models.Evenement;
import com.blackstar.MkOpportunity.Mychoir.models.Finance;

public class ShowActivity extends AppCompatActivity {

    private static final int MY_SMS_PERMISSION = 1234;
    EvenementRepository evenementRepository;
        FinanceRepository financeRepository;
        ChoristeRepository choristeRepository;
        Long id;
        String type;
        TableLayout evenementView;
        TableLayout financeView;
        TableLayout choristeView;
        Button notify;
    /* gestion de finances */
    Finance finance;
    public TextView dateData;
    public  TextView libellerData;
    public TextView typeData;
    public TextView montantData;
    /*fin  gestion de finances */
    public ImageView imageView;
    /* gestion d'evenement */
    Evenement evenement;
    public TextView dateEventData;
    public  TextView raisonData;
    public  TextView lieuData;
    public TextView concernerData;
    /*fin  gestion d"evenement */

    /* gestion de choriste */
    Choriste choriste;
    public TextView nomData;
    public  TextView prenomData;
    public  TextView sexeData;
    public TextView pupitreData;
    public  TextView professionData;
    public TextView roleData;
    public  TextView contactData;
    public  TextView naissanceData;
    public  ImageView call;
    /*fin  gestion de choriste */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        financeRepository=new FinanceRepository(this);
        financeRepository.open();
        evenementRepository=new EvenementRepository(this);
        evenementRepository.open();
        choristeRepository=new ChoristeRepository(this);
        choristeRepository.open();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        type=getIntent().getExtras().get("type").toString();
        id=Long.parseLong(getIntent().getExtras().get("id").toString());
        imageView=findViewById(R.id.image);
        call=findViewById(R.id.call);
        evenementView=findViewById(R.id.evenement);
        financeView=findViewById(R.id.finance);
        notify=findViewById(R.id.notify);
        choristeView=findViewById(R.id.choristes);

    }

    @Override
    protected void onResume() {
        switch (type){
            case "evenement":
                imageView.setImageResource(R.drawable.ic_event_black_24dp);
                evenement=evenementRepository.find(id);
                setTitle(evenement.getDate());
                raisonData=findViewById(R.id.raison);
                lieuData=findViewById(R.id.lieu);
                dateEventData=findViewById(R.id.date);
                concernerData=findViewById(R.id.concerner);
                raisonData.setText(evenement.getRaison());
                lieuData.setText(evenement.getLieu());
                dateEventData.setText(evenement.getDate());
                concernerData.setText(evenement.getConcerne());
                notify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        msg();
                    }
                });
                evenementView.setVisibility(View.VISIBLE);
                break;
            case "finance":
                finance=financeRepository.find(id);
                setTitle(finance.getType()+" - "+finance.getDate());
                imageView.setImageResource(R.drawable.ic_monetization_on_black_24dp);
                dateData=findViewById(R.id.dateF);
                libellerData=findViewById(R.id.information);
                typeData=findViewById(R.id.typefinance);
                montantData=findViewById(R.id.montant);
                dateData.setText(finance.getDate());
                libellerData.setText(finance.getLibeller());
                typeData.setText(finance.getType());
                montantData.setText(finance.getMontant().toString());
                financeView.setVisibility(View.VISIBLE);
                break;
            case "choriste":
                choriste=choristeRepository.find(id);
                setTitle(choriste.getRole()+" - "+choriste.getPrenom());
                imageView.setImageResource(R.drawable.choirs);
                nomData=findViewById(R.id.nomData);
                prenomData=findViewById(R.id.prenomData);
                sexeData=findViewById(R.id.sexeData);
                professionData=findViewById(R.id.professionData);
                roleData=findViewById(R.id.roleData);
                contactData=findViewById(R.id.contactData);
                pupitreData=findViewById(R.id.pupitreData);
                naissanceData=findViewById(R.id.naissanceData);
                nomData.setText(choriste.getNom());
                prenomData.setText(choriste.getPrenom());
                sexeData.setText(choriste.getSexe());
                naissanceData.setText(choriste.getDateNaissance());
                professionData.setText(choriste.getProfession());
                roleData.setText(choriste.getRole());
                contactData.setText(choriste.getContact());
                pupitreData.setText(choriste.getPupiptre());
                choristeView.setVisibility(View.VISIBLE);
                call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ContextCompat.checkSelfPermission(ShowActivity.this, Manifest.permission.CALL_PHONE)
                                == PackageManager.PERMISSION_GRANTED) {
                            String telURI = "tel:" +choriste.getContact();
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(telURI));
                            startActivity(intent);
                        }else {
                            ActivityCompat.requestPermissions(ShowActivity.this,
                                    new String[]{Manifest.permission.CALL_PRIVILEGED,Manifest.permission.CALL_PHONE,Manifest.permission.ANSWER_PHONE_CALLS},
                                    1243);
                            Toast.makeText(getApplicationContext(),getString(R.string.reload),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
        super.onResume();
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
            switch (type) {
                case "evenement":
                    i.putExtra("type","evenement");
                    i.putExtra("id",evenement.getId());
                    break;
                case "finance":
                    i.putExtra("type","finance");
                    i.putExtra("id",finance.getId());
                    break;
                case "choriste":
                    i.putExtra("type","choriste");
                    i.putExtra("id",choriste.getId());
                    break;
            }
            startActivity(i);
        }
        if (item.getItemId()==R.id.delete){
            switch (type) {
                case "evenement":
                    delete(evenement.getId());
                    break;
                case "finance":
                    delete(finance.getId());
                    break;
                case "choriste":
                    delete(choriste.getId());
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public  void delete(final Long id){
        TextView title;
        TextView message;
        Button yes;
        Button no;
        final  AlertDialog.Builder alert=new AlertDialog.Builder(ShowActivity.this);
        View dialog=getLayoutInflater().inflate(R.layout.confirm_dialog,null);
        title=dialog.findViewById(R.id.title);
        message=dialog.findViewById(R.id.message);
        yes=dialog.findViewById(R.id.yes);
        no=dialog.findViewById(R.id.no);
        title.setText(R.string.delete_confirm);
        message.setText(R.string.delet_confirm_msg);
        alert.setView(dialog);
        final AlertDialog alertDialog=alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (type) {
                    case "evenement":
                evenementRepository.delete(id);
                        break;
                    case "finance":
                financeRepository.delete(id);
                        break;
                    case "choriste":
                choristeRepository.delete(id);
                        break;
                }
                Toast.makeText(getApplicationContext(),getString(R.string.delete_success),Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
                finish();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public  void msg(){
        TextView title;
        final EditText message;
        Button yes;
        Button no;
        final  AlertDialog.Builder alert=new AlertDialog.Builder(ShowActivity.this);
        View dialog=getLayoutInflater().inflate(R.layout.edit_dialog,null);
        title=dialog.findViewById(R.id.title);
        message=dialog.findViewById(R.id.message);
        yes=dialog.findViewById(R.id.yes);
        no=dialog.findViewById(R.id.no);
        yes.setText(R.string.send);
        no.setText(R.string.back);
        alert.setView(dialog);
        final AlertDialog alertDialog=alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        String numChoriste="";
        int i=0;
        for (Choriste cho : choristeRepository.findAll()){
            if (i==0){
                numChoriste=numChoriste.concat(cho.getContact());
            }else{
                numChoriste=numChoriste.concat(",");
                numChoriste=numChoriste.concat(cho.getContact());
            }
            i++;
        }
        final String notifEvent="Salut chers Choriste juste pour  rapel : le "+evenement.getDate()+" à "+
                evenement.getLieu()+" Soyons nombreux Pour "+evenement.getConcerne()+" \n NB: "+evenement.getRaison();
        title.setText("SMS "+evenement.getRaison());
        message.setText(notifEvent);
        final String finalNumChoriste = numChoriste;
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager manager = SmsManager.getDefault();
                if (finalNumChoriste.length()>2){
                    if (ContextCompat.checkSelfPermission(ShowActivity.this, Manifest.permission.SEND_SMS)
                            == PackageManager.PERMISSION_GRANTED) {
                        manager.sendTextMessage(finalNumChoriste, null, message.getText().toString(),null, null);
                        Toast.makeText(getApplicationContext(),getString(R.string.done),Toast.LENGTH_SHORT).show();
                    }else {
                        ActivityCompat.requestPermissions(ShowActivity.this,
                                new String[]{Manifest.permission.SEND_SMS},
                                MY_SMS_PERMISSION);
                        Toast.makeText(getApplicationContext(),getString(R.string.reload),Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),getString(R.string.no_choir),Toast.LENGTH_SHORT).show();
                }
                alertDialog.dismiss();
                finish();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
