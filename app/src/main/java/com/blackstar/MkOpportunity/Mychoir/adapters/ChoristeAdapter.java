package com.blackstar.MkOpportunity.Mychoir.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blackstar.MkOpportunity.Mychoir.R;
import com.blackstar.MkOpportunity.Mychoir.models.Choriste;

import java.util.ArrayList;
import java.util.List;

public class ChoristeAdapter extends ArrayAdapter<Choriste> {
    List<Choriste> choristes=null;
    public ChoristeAdapter(Context context, int resource, List<Choriste> objects) {
        super(context, resource, objects);
        this.choristes=objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        LayoutInflater layoutInflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	View ligne=null;
	/* if(convertView != null){
	ligne=convertView;
	}else{*/ligne=layoutInflater.inflate(R.layout.adapter3,parent,false);
        ImageView image=ligne.findViewById(R.id.image);
        TextView titre=ligne.findViewById(R.id.titre);
        TextView sous_titre=ligne.findViewById(R.id.sous_titre);
        TextView libeller=ligne.findViewById(R.id.libeller);
        image.setImageResource(R.drawable.choirs);
        titre.setText(choristes.get(position).nom+" "+choristes.get(position).prenom);
        libeller.setText(choristes.get(position).role);
        libeller.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
        sous_titre.setText(choristes.get(position).pupiptre);
	//}
        return ligne;
    }
}
