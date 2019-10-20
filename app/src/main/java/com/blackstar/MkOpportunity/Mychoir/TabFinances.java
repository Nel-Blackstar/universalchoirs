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

import com.blackstar.MkOpportunity.Mychoir.Repositories.FinanceRepository;
import com.blackstar.MkOpportunity.Mychoir.Forms.ChoirForm;
import com.blackstar.MkOpportunity.Mychoir.models.Finance;

public class TabFinances extends Fragment {
    TextView entrees;
    TextView solde;
    TextView sorties;
    MaterialCardView list;
    MaterialCardView add;
    MaterialCardView clean;
    MaterialCardView report;
    FinanceRepository financeRepository;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        financeRepository=new FinanceRepository(getContext());
        financeRepository.open();
        View rootView=inflater.inflate(R.layout.tabfinances,container,false);
            entrees=rootView.findViewById(R.id.entree);
            sorties=rootView.findViewById(R.id.sortie);
            solde=rootView.findViewById(R.id.ratio);
            list=rootView.findViewById(R.id.list);
            add=rootView.findViewById(R.id.add);
           report=rootView.findViewById(R.id.report);
            clean=rootView.findViewById(R.id.clean);
            list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent liste=new Intent(getContext(), ListActivity.class);
                    liste.putExtra("type","Finances");
                    startActivity(liste);
                }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent liste=new Intent(getContext(), ChoirForm.class);
                liste.putExtra("type","Finances");
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
                report.putExtra("type","Finances");
                startActivity(report);
            }
        });
        return  rootView;
    }

    @Override
    public void onResume() {
        Double totalEntrees=0.0;
        Double totalSorties=0.0;
        for (Finance finance: financeRepository.findAllEntrees()){
            totalEntrees+=finance.getMontant();
        }
        for (Finance finance: financeRepository.findAllSorties()){
            totalSorties+=finance.getMontant();
        }
        entrees.setText(totalEntrees.toString());
        sorties.setText(totalSorties.toString());
        Double ratio=totalEntrees-totalSorties;

        solde.setText(ratio.toString());
        super.onResume();
    }
    public void clean(){
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
                financeRepository.clean();
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
