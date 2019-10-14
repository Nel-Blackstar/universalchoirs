package com.black.chorale.adapters;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.black.chorale.R;
import com.black.chorale.models.Chant;

import java.util.ArrayList;
import java.util.List;
public class BsAdapter extends ArrayAdapter<Chant> {
    List<Chant> chants=null;
    public BsAdapter(Context context, int resource, List<Chant> objects) {
        super(context, resource, objects);
        this.chants=objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        LayoutInflater layoutInflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	View ligne=null;
	/* if(convertView != null){
	ligne=convertView;
	}else{*/
        ligne=layoutInflater.inflate(R.layout.adapter,parent,false);
        ImageView image=ligne.findViewById(R.id.image);
        TextView titre=ligne.findViewById(R.id.titre);
        TextView sous_titre=ligne.findViewById(R.id.sous_titre);
        image.setImageResource(R.drawable.ic_queue_music_black_24dp);
        titre.setText(chants.get(position).titre);
        sous_titre.setText(chants.get(position).refrain);
	//}
        return ligne;
    }
}
