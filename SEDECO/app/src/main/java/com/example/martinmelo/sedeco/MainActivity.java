package com.example.martinmelo.sedeco;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.design.widget.TabItem;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TabItem proyectos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        proyectos = (TabItem) findViewById(R.id.proyectos);

    }

    public void proyectos(View view){
        FragmentManager fragmentmanager = getFragmentManager();
        FragmentTransaction transaction = fragmentmanager.beginTransaction();
        Proyectos proyecto = new Proyectos();
        transaction.add(R.id.activity_main, proyecto);
        transaction.commit();
    }

    public void prueba(View view){
        Toast.makeText(getApplicationContext(),"Si sirvo alv", Toast.LENGTH_LONG).show();
    }


}
