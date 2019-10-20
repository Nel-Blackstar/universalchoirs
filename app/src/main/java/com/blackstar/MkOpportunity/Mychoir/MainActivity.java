package com.blackstar.MkOpportunity.Mychoir;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.blackstar.MkOpportunity.Mychoir.Repositories.ChantRepository;
import com.blackstar.MkOpportunity.Mychoir.adapters.ChantAdapter;
import com.blackstar.MkOpportunity.Mychoir.main.SectionsPagerAdapter;
import com.blackstar.MkOpportunity.Mychoir.models.Chant;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public TabLayout tabLayout=null;
    Boolean mCountdown=false;
    public  ChantRepository chantRepository;
    ViewPager viewPager;
    ListView results;
    TextView emptySearch;
    List<Chant> chants;
    AppBarLayout appBarLayout;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        chantRepository = new ChantRepository(getApplicationContext());
        chantRepository.open();
         super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        appBarLayout=findViewById(R.id.appbar);
        viewPager.setAdapter(sectionsPagerAdapter);
        results=findViewById(R.id.results);
        results.setBackgroundColor(getResources().getColor(R.color.white));
        emptySearch=findViewById(R.id.emptySearch);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        toolbar = (Toolbar)findViewById(R.id.appbarlayout_tool_bar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
       SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString(PreferenceActivity.NAME, "");
        String subname = preferences.getString(PreferenceActivity.SUBNAME, "");
        boolean show=Boolean.parseBoolean(preferences.getString(PreferenceActivity.SHOW, "false"));
        if (show){
            if (name.length()>1 || subname.length()>1){
                toolbar.setSubtitle(name+" "+subname);
            }
        }else {
            toolbar.setSubtitle("");
        }
        chants=chantRepository.findAll();
        ChantAdapter chantAdapter =new ChantAdapter(this,R.layout.adapter,chants);
        results.setAdapter(chantAdapter);
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        MenuItem menuItem=menu.findItem(R.id.find);
        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                final ArrayList<Chant> resultats=new ArrayList<>();
                for (Chant chant: chants){
                    if (chant.titre.toLowerCase().contains(s.toLowerCase()) || chant.Contenue.toLowerCase().contains(s.toLowerCase()) || chant.refrain.toLowerCase().contains(s.toLowerCase())){
                        resultats.add(chant);
                    }
                    ChantAdapter chantAdapter =new ChantAdapter(MainActivity.this,R.layout.adapter,resultats);
                    results.setAdapter(chantAdapter);
                    results.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent lire=new Intent(MainActivity.this,LireChantActivity.class);
                            lire.putExtra("id",resultats.get(position).getId());
                            startActivity(lire);
                        }
                    });
                    if (resultats.isEmpty()){
                        results.setVisibility(View.GONE);
                        emptySearch.setVisibility(View.VISIBLE);
                    }else {
                        results.setVisibility(View.VISIBLE);
                        emptySearch.setVisibility(View.GONE);
                    }
                    if (s.length()<=0){
                        results.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.VISIBLE);
                        viewPager.setVisibility(View.VISIBLE);
                        appBarLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    }else {
                        results.setVisibility(View.VISIBLE);
                        tabLayout.setVisibility(View.GONE);
                        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        appBarLayout.setBackgroundColor(getResources().getColor(R.color.white));
                        viewPager.setVisibility(View.GONE);
                    }
                }
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()){
            case R.id.abouts:
            i=new Intent(this,StaticActivity.class);
            startActivity(i);
                break;
            case R.id.choristes:
                i=new Intent(this,ChoristeActivity.class);
                startActivity(i);
                break;

            case  R.id.params:
                i=new Intent(this,PreferenceActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        mCountdown=false;
        super.onPause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
	// Sinon, si c'est la premi�re fois qu'on fait un retour arri�re
            if (mCountdown != true) {
	// On indique � l'utilisateur qu'appuyer dessus une seconde fois le fera sortir
                Toast.makeText(this, getString(R.string.quitterApp), Toast.LENGTH_SHORT).show();
                mCountdown = true;
            } else {
                // Si c'est la seconde fois, on sort effectivement
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}