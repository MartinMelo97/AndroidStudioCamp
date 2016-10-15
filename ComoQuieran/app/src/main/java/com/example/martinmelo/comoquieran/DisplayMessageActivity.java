package com.example.martinmelo.comoquieran;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.view.ViewGroup;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message2);

        Intent intent = getIntent();
        String mensaje = intent.getStringExtra(MainActivity.EXTRA);
        TextView textView = new TextView(this);
        textView.setText(mensaje);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message2);
        layout.addView(textView);
    }
}
