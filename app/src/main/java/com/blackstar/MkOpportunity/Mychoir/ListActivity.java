package com.blackstar.MkOpportunity.Mychoir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.black.chorale.R;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        Bundle bd=intent.getExtras();
        String type=bd.get("type").toString();
        setContentView(R.layout.activity_list);
        switch (type) {
            case "Finances":
                setTitle(R.string.Finances);
                break;
            case "Evenements":
                setTitle(R.string.Evenements);
                break;
        }
    }
}
