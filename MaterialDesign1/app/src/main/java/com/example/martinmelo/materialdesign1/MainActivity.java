package com.example.martinmelo.materialdesign1;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    TextView contador;
    TextView tostada;
    TextView otra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        contador = (TextView) findViewById(R.id.contador);
        tostada = (TextView) findViewById(R.id.tostada);
        otra = (TextView) findViewById(R.id.activity_new);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cont = Integer.parseInt(contador.getText().toString());
                cont = cont + 1;
                contador.setText(Integer.toString(cont));
            }
        });

        tostada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Soy una Tostada",Toast.LENGTH_LONG).show();
                tostada.setText("Hola");
            }
        });

        otra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });





    }
}
