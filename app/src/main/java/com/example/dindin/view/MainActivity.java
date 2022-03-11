package com.example.dindin.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.NumberFormat;

import com.example.dindin.R;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.edittext = findViewById(R.id.editTextTextPersonName2);
        this.mViewHolder.btt = findViewById(R.id.Button);
        this.mViewHolder.btt2 = findViewById(R.id.Button2);
        this.mViewHolder.text = findViewById(R.id.textView);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

        Integer oldnum = sharedPref.getInt("value", 0);
        mViewHolder.text.setText(oldnum.toString());

        this.mViewHolder.btt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mViewHolder.edittext.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Coloque um valor!", Toast.LENGTH_SHORT).show();
                }else {
                    SharedPreferences.Editor editor = sharedPref.edit();

                    Integer newnum = sharedPref.getInt("value", 0);

                    Integer result = Baixa(oldnum, newnum);

                    editor.putInt("value", result);

                    editor.apply();

                    Integer valorfinal = sharedPref.getInt("value", 0);

                    if (valorfinal == 0) {
                        Toast.makeText(getApplicationContext(), "Você está pobre novamente!", Toast.LENGTH_LONG).show();
                    }

                    mViewHolder.text.setText(result.toString());
                }
            }
        });

        this.mViewHolder.btt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mViewHolder.edittext.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Coloque um valor!", Toast.LENGTH_SHORT).show();
                }else {
                    SharedPreferences.Editor editor = sharedPref.edit();

                    editor.putInt("value", Integer.parseInt(mViewHolder.edittext.getText().toString()));
                    editor.apply();


                    Integer newnum = sharedPref.getInt("value", 0);

                    Integer result = Ganho(oldnum, newnum);

                    editor.putInt("value", result);
                    editor.apply();

                    mViewHolder.text.setText(result.toString());
                }
            }
        });


    }

    private static Integer Baixa(Integer oldn, Integer newn) {
         Integer dif = oldn - newn;
         if (dif <= 0){
             dif = 0;
         }
         return dif;
    }

    private static Integer Ganho(Integer oldn, Integer newn){
        Integer dif = oldn + newn;

        return dif;
    }

    private static class ViewHolder{
        TextView text;
        EditText edittext;
        com.google.android.material.floatingactionbutton.FloatingActionButton btt;
        com.google.android.material.floatingactionbutton.FloatingActionButton btt2;
    }
}