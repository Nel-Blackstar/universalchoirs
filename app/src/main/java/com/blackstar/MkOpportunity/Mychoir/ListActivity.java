package com.blackstar.MkOpportunity.Mychoir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.blackstar.MkOpportunity.Mychoir.Repositories.EvenementRepository;
import com.blackstar.MkOpportunity.Mychoir.Repositories.FinanceRepository;
import com.blackstar.MkOpportunity.Mychoir.adapters.EvenementAdapter;
import com.blackstar.MkOpportunity.Mychoir.adapters.FinanceAdapter;
import com.blackstar.MkOpportunity.Mychoir.models.Evenement;
import com.blackstar.MkOpportunity.Mychoir.models.Finance;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    ListView listView;
    FinanceRepository financeRepository;
    EvenementRepository evenementRepository;
    public  String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        financeRepository=new FinanceRepository(getApplicationContext());
        financeRepository.open();
        evenementRepository=new EvenementRepository(getApplicationContext());
        evenementRepository.open();
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        Bundle bd=intent.getExtras();
        this.type=bd.get("type").toString();
        setContentView(R.layout.activity_list);
        listView=findViewById(R.id.listes);
    }

    @Override
    protected void onResume() {
        switch (type) {
            case "Finances":
                setTitle(R.string.Finances);
                final List<Finance> finances=financeRepository.findAll();
                FinanceAdapter financeAdapter=new FinanceAdapter(getApplicationContext(),R.layout.adapter3,finances);
                listView.setAdapter(financeAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent=new Intent(ListActivity.this,ShowActivity.class);
                        intent.putExtra("type","finance");
                        intent.putExtra("id",finances.get(position).getId());
                        startActivity(intent);
                    }
                });
                break;
            case "Evenements":
                setTitle(R.string.Evenements);
                final List<Evenement> evenements=evenementRepository.findAll();
                EvenementAdapter evenementAdapter=new EvenementAdapter(getApplicationContext(),R.layout.adapter3,evenements);
                listView.setAdapter(evenementAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent=new Intent(ListActivity.this,ShowActivity.class);
                        intent.putExtra("type","evenement");
                        intent.putExtra("id",evenements.get(position).getId());
                        startActivity(intent);
                    }
                });
                break;
        }
        super.onResume();
    }
}
