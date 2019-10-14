package com.black.chorale;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.black.chorale.Controleurs.ChantRepository;
import com.black.chorale.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    public TabLayout tabLayout=null;
    Boolean mCountdown=false;
    public  ChantRepository chantRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        chantRepository = new ChantRepository(getApplicationContext());
        chantRepository.open();
         super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        Toolbar toolbar = (Toolbar)findViewById(R.id.appbarlayout_tool_bar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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