package com.blackstar.MkOpportunity.Mychoir;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.blackstar.MkOpportunity.Mychoir.Repositories.ChantRepository;
import com.blackstar.MkOpportunity.Mychoir.Forms.ChoirFormUpdate;
import com.blackstar.MkOpportunity.Mychoir.models.Chant;

public class LireChantActivity extends AppCompatActivity {
        TextView titre=null;
        TextView refrain=null;
        TextView contenue=null;
         Chant chant;
         Long chantId;
         ChantRepository chantRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        chantRepository=new ChantRepository(getApplicationContext());
        chantRepository.open();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lire_chant);
        titre= findViewById(R.id.titreChant);
        refrain=findViewById(R.id.refrainChant);
        contenue=findViewById(R.id.contenueChant);
        Intent intent=getIntent();
        Bundle bd=intent.getExtras();
        if (bd!=null){
            this.chantId=(Long) bd.get("id");
        }
    }

    @Override
    protected void onResume() {
        chant=chantRepository.find(chantId);
        this.setTitle(chant.getTitre());
        titre.setText(chant.getTitre());
        refrain.setText(chant.getRefrain());
        contenue.setText(chant.getContenue());
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.update,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        if (item.getItemId()==R.id.update){
            i=new Intent(getApplicationContext(), ChoirFormUpdate.class);
            i.putExtra("type","Chant");
            i.putExtra("id",chant.getId());
            startActivity(i);
        }
        if (item.getItemId()==R.id.delete){
            delete(chant.getId());
        }
        return super.onOptionsItemSelected(item);
    }

    public  void delete(final Long id){
        TextView title;
        TextView message;
        Button yes;
        Button no;
        final  AlertDialog.Builder alert=new AlertDialog.Builder(LireChantActivity.this);
        View dialog=getLayoutInflater().inflate(R.layout.confirm_dialog,null);
        title=dialog.findViewById(R.id.title);
        message=dialog.findViewById(R.id.message);
        yes=dialog.findViewById(R.id.yes);
        no=dialog.findViewById(R.id.no);
        title.setText(R.string.delete_confirm);
        message.setText(R.string.delet_confirm_msg);
        alert.setView(dialog);
        final AlertDialog alertDialog=alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chantRepository.delete(id);
                Toast.makeText(getApplicationContext(),getString(R.string.delete_success),Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
                finish();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
