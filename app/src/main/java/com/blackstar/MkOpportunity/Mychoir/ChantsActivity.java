package com.blackstar.MkOpportunity.Mychoir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.black.chorale.R;
import com.blackstar.MkOpportunity.Mychoir.Controleurs.ChantRepository;
import com.blackstar.MkOpportunity.Mychoir.Forms.ChoirForm;
import com.blackstar.MkOpportunity.Mychoir.adapters.BsAdapter;
import com.blackstar.MkOpportunity.Mychoir.models.Chant;

import java.util.List;

public class ChantsActivity extends AppCompatActivity {

    private TextView label=null;
    List<Chant> chants=null;
    ListView listView=null;
    int categorie;
    String categorieDB=null;
    public  ChantRepository chantRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        chantRepository = new ChantRepository(getApplicationContext());
        chantRepository.open();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chants);
        final Intent intent=getIntent();
        Bundle db= intent.getExtras();
        String categorie=db.get("categorie").toString();
        categorieDB=categorie;
        int cat =(int) db.get("idcategorie");
        this.categorie=cat;
        setTitle(getString(cat));
        chants=chantRepository.findByCategorie(categorie);
        BsAdapter bsAdapter=new BsAdapter(this,R.layout.adapter,chants);
        listView=findViewById(R.id.list_chant);
        listView.setAdapter(bsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent lire=new Intent(ChantsActivity.this,LireChantActivity.class);
                lire.putExtra("titre",chants.get(position).getTitre());
                lire.putExtra("refrain",chants.get(position).getRefrain());
                lire.putExtra("id",chants.get(position).getId());
                lire.putExtra("categorie",chants.get(position).getCategorie());
                lire.putExtra("contenue",chants.get(position).getContenue());
                startActivity(lire);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.add,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        if (item.getItemId()==R.id.add){
                i=new Intent(getApplicationContext(), ChoirForm.class);
                i.putExtra("type","Chant");
                i.putExtra("categorie",categorieDB);
                startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
