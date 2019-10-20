package com.blackstar.MkOpportunity.Mychoir;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.card.MaterialCardView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.blackstar.MkOpportunity.Mychoir.Repositories.EvenementRepository;
import com.blackstar.MkOpportunity.Mychoir.Forms.ChoirForm;
import com.blackstar.MkOpportunity.Mychoir.models.Evenement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TabEvenements extends Fragment {
    MaterialCardView list;
    MaterialCardView add;
    MaterialCardView clean;
    MaterialCardView report;
    TextView allEvents;
    TextView passedEvent;
    TextView futurEvents;
    EvenementRepository evenementRepository;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        evenementRepository=new EvenementRepository(getContext());
        evenementRepository.open();
        View rootView=inflater.inflate(R.layout.tabevenements,container,false);
        list=rootView.findViewById(R.id.list);
        add=rootView.findViewById(R.id.add);
        report=rootView.findViewById(R.id.report);
        clean=rootView.findViewById(R.id.clean);
        allEvents=rootView.findViewById(R.id.allEvents);
        passedEvent=rootView.findViewById(R.id.passedEvents);
        futurEvents=rootView.findViewById(R.id.futureEvents);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent liste=new Intent(getContext(), ListActivity.class);
                liste.putExtra("type","Evenements");
                startActivity(liste);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent liste=new Intent(getContext(), ChoirForm.class);
                liste.putExtra("type","Evenements");
                startActivity(liste);
            }
        });
        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clean();
            }
        });
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent report=new Intent(getContext(), ReportActivity.class);
                report.putExtra("type","Evenements");
                startActivity(report);
            }
        });
        return  rootView;
    }

    @Override
    public void onResume() {
        int passer=0;
        int future=0;
        int total=0;
        for (Evenement evenement : evenementRepository.findAll()){
            Date dateEvents=new Date();
            Date now=new Date();
            try {
                dateEvents=new SimpleDateFormat("dd/MM/yyyy").parse(evenement.date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (dateEvents.before(now)){
                passer++;
            }else{
                future++;
            }
            total++;
        }
        passedEvent.setText(String.valueOf(passer));
        futurEvents.setText(String.valueOf(future));
        allEvents.setText(String.valueOf(total));
        super.onResume();
    }

    public  void clean(){
        TextView title;
        TextView message;
        Button yes;
        Button no;
        final  AlertDialog.Builder alert=new AlertDialog.Builder(getContext());
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
                evenementRepository.clean();
                onResume();
                Toast.makeText(getContext(),R.string.done,Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),getString(R.string.canceled),Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
