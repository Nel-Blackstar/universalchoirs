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
import com.blackstar.MkOpportunity.Mychoir.models.Chant;
import com.blackstar.MkOpportunity.Mychoir.models.Finance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FinanceAdapter extends ArrayAdapter<Finance> {
    List<Finance> finances=null;
    public FinanceAdapter(Context context, int resource, List<Finance> objects) {
        super(context, resource, objects);
        this.finances=objects;
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
        if (finances.get(position).getType().equals("Entree")){
            image.setImageResource(R.drawable.ic_vertical_align_bottom_black_24dp);
        }else {
            image.setImageResource(R.drawable.ic_vertical_align_top_black_24dp);
        }
        Date dateF=null;
        try {
           dateF=new SimpleDateFormat("dd/MM/yyyy").parse(finances.get(position).date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        titre.setText(dateF.toString());
        libeller.setText(finances.get(position).libeller);
        libeller.setTextColor(getContext().getResources().getColor(R.color.colorPrimaryDark));
        sous_titre.setText(finances.get(position).montant.toString());
        sous_titre.setTextSize(18);
        sous_titre.setTextColor(getContext().getResources().getColor(R.color.colorPrimaryDark));
	//}
        return ligne;
    }
    public void  update(ArrayList<Finance> results){
        finances=new ArrayList<Finance>();
        finances.addAll(results);
        notifyDataSetChanged();
    }
}
