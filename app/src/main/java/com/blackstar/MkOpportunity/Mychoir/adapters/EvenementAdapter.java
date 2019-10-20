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
import com.blackstar.MkOpportunity.Mychoir.models.Evenement;
import com.blackstar.MkOpportunity.Mychoir.models.Finance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EvenementAdapter extends ArrayAdapter<Evenement> {
    List<Evenement> evenements=null;
    public EvenementAdapter(Context context, int resource, List<Evenement> objects) {
        super(context, resource, objects);
        this.evenements=objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        LayoutInflater layoutInflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	View ligne=null;
	/* if(convertView != null){
	ligne=convertView;
	}else{*/
        ligne=layoutInflater.inflate(R.layout.adapter3,parent,false);
        ImageView image=ligne.findViewById(R.id.image);
        TextView titre=ligne.findViewById(R.id.titre);
        TextView sous_titre=ligne.findViewById(R.id.sous_titre);
        TextView libeller=ligne.findViewById(R.id.libeller);
            image.setImageResource(R.drawable.ic_event_black_24dp);
        Date dateF=null;
        try {
           dateF=new SimpleDateFormat("dd/MM/yyyy").parse(evenements.get(position).date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        titre.setText(dateF.toString());
        libeller.setText(getContext().getString(R.string.reason)+" : "+evenements.get(position).raison);
        libeller.setTextColor(getContext().getResources().getColor(R.color.colorPrimaryDark));
        sous_titre.setText(getContext().getString(R.string.place)+" : "+evenements.get(position).lieu.toString());
        sous_titre.setTextColor(getContext().getResources().getColor(R.color.colorPrimaryDark));
	//}
        return ligne;
    }
    public void  update(ArrayList<Evenement> results){
        evenements=new ArrayList<Evenement>();
        evenements.addAll(results);
        notifyDataSetChanged();
    }
}
