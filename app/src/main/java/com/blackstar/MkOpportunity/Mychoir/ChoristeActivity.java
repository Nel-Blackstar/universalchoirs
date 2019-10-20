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

import com.blackstar.MkOpportunity.Mychoir.Repositories.ChantRepository;
import com.blackstar.MkOpportunity.Mychoir.Forms.ChoirForm;
import com.blackstar.MkOpportunity.Mychoir.Repositories.ChoristeRepository;
import com.blackstar.MkOpportunity.Mychoir.adapters.ChoristeAdapter;
import com.blackstar.MkOpportunity.Mychoir.models.Choriste;

import java.util.List;

public class ChoristeActivity extends AppCompatActivity {

    public ListView listView;
    public List<Choriste> choristes;
    public ChoristeRepository choristeRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        choristeRepository = new ChoristeRepository(getApplicationContext());
        choristeRepository.open();
        setTitle(R.string.les_choriste);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choriste);
        listView=findViewById(R.id.listChoriste);
    }

    @Override
    protected void onResume() {
        choristes=choristeRepository.findAll();
        ChoristeAdapter choristeAdapter =new ChoristeAdapter(this,R.layout.adapter,choristes);
        listView=findViewById(R.id.listChoriste);
        listView.setAdapter(choristeAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent lire=new Intent(ChoristeActivity.this,ShowActivity.class);
                lire.putExtra("id",choristes.get(position).getId());
                lire.putExtra("type","choriste");
                startActivity(lire);
            }
        });
        super.onResume();
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
            i.putExtra("type","Choriste");
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
