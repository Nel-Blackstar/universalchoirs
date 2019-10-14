package com.blackstar.MkOpportunity.Mychoir;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.card.MaterialCardView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.black.chorale.R;
import com.blackstar.MkOpportunity.Mychoir.Forms.ChoirForm;

public class TabEvenements extends Fragment {
    MaterialCardView list;
    MaterialCardView add;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView=inflater.inflate(R.layout.tabevenements,container,false);
        list=rootView.findViewById(R.id.list);
        add=rootView.findViewById(R.id.add);
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
        return  rootView;
    }
}
