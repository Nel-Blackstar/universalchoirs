package com.blackstar.MkOpportunity.Mychoir;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.card.MaterialCardView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.black.chorale.R;
import com.blackstar.MkOpportunity.Mychoir.Controleurs.FinanceRepository;
import com.blackstar.MkOpportunity.Mychoir.Forms.ChoirForm;

public class TabFinances extends Fragment {
    TextView entrees;
    TextView solde;
    TextView sorties;
    MaterialCardView list;
    MaterialCardView add;
    FinanceRepository financeRepository;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        financeRepository=new FinanceRepository(getContext());
        View rootView=inflater.inflate(R.layout.tabfinances,container,false);
            entrees=rootView.findViewById(R.id.entree);
            sorties=rootView.findViewById(R.id.sortie);
            solde=rootView.findViewById(R.id.ratio);
            list=rootView.findViewById(R.id.list);
            add=rootView.findViewById(R.id.add);
            entrees.setText(financeRepository.findAllEntrees().toString());
            sorties.setText(financeRepository.findAllSorties().toString());
            Double ratio=financeRepository.findAllEntrees()-financeRepository.findAllSorties();
            solde.setText(ratio.toString());
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
        return  rootView;
    }
}
