package com.blackstar.MkOpportunity.Mychoir;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.black.chorale.R;

import java.util.ArrayList;
import java.util.List;

public class TabChants extends Fragment {
    private LinearLayout conteneur;
    private ListView listCategories;
    public List<String> categories;
    private final Integer[] categoriesId={ R.string.p_entree,R.string.p_kyries,R.string.p_glorias,
             R.string.p_processions,R.string.p_credos,R.string.p_acclamations,R.string.p_pu,R.string.p_offertoires,R.string.p_sanctus,
             R.string.p_paters,R.string.p_ad,R.string.p_communions,R.string.p_sorties,R.string.p_divers};
    private final String[] idCategories={"Entrees","Kyries","Glorias","Procession","Credos","Acclamations","Priere Universelles","Offertoires","Sanctus","Paters","Agneis deï","Communions","Sorties","Divers"};
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        categories=new ArrayList<String>();
        View rootView=inflater.inflate(R.layout.tabchants,container,false);
       conteneur = rootView.findViewById(R.id.conteneur);
       listCategories = rootView.findViewById(R.id.categories);
       for (Integer cat : categoriesId){
            categories.add(getString(cat));
       }
        ArrayAdapter adapter=new ArrayAdapter(getContext(),android.R.layout.simple_expandable_list_item_1,categories);
        listCategories.setAdapter(adapter);
        listCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent chants=new Intent(getContext(),ChantsActivity.class);
                chants.putExtra("categorie",idCategories[position]);
                chants.putExtra("idcategorie",categoriesId[position]);
                startActivity(chants);
            }
        });
        return  rootView;
    }
}
