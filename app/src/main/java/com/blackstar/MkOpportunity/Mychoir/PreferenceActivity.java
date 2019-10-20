package com.blackstar.MkOpportunity.Mychoir;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.Toast;

public class PreferenceActivity extends AppCompatActivity {

    public static final   String NAME="userName";
    public static final   String SUBNAME="prenom";
    public static final   String SHOW="show";
    public static final   String NOTE="note";
    public EditText nameData;
    public EditText subnameData;
    public Switch showData;
    public RatingBar noteData;
    SharedPreferences.Editor editor;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences);
        setTitle(R.string.parametres);
        nameData=findViewById(R.id.name);
        subnameData=findViewById(R.id.submane);
        showData=findViewById(R.id.shows);
        noteData=findViewById(R.id.note);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = preferences.edit();
        nameData.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    editor.putString(NAME, nameData.getText().toString());
                    editor.commit();
                }
            }
        });
        subnameData.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    editor.putString(SUBNAME, subnameData.getText().toString());
                    editor.commit();
                }
            }
        });
        showData.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    editor.putString(SHOW, String.valueOf(isChecked));
                    editor.commit();
            }
        });
        noteData.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                editor.putString(NOTE, String.valueOf(rating));
                editor.commit();
                Toast.makeText(PreferenceActivity.this,getString(R.string.thanks_eval),Toast.LENGTH_SHORT).show();
            }
        });
        }

    @Override
    protected void onResume() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString(PreferenceActivity.NAME, "");
        String subname = preferences.getString(PreferenceActivity.SUBNAME, "");
        Float rating =Float.parseFloat(preferences.getString(PreferenceActivity.NOTE, "3"));
        boolean show=Boolean.parseBoolean(preferences.getString(PreferenceActivity.SHOW, "false"));
        if (name.length()>1 || subname.length()>1){
                nameData.setText(name);
                subnameData.setText(subname);
        }
        noteData.setRating(rating);
        showData.setChecked(show);
        super.onResume();
    }
}
