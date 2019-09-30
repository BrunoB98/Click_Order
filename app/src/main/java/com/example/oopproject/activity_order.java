package com.example.oopproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class activity_order extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Bundle b = getIntent().getExtras();
        Button button = findViewById(R.id.ciao);
        if (b.getInt("categoria") == R.id.cat1) {
        button.setText("Nuovo ordine, la categoria è PRIMI"); }

    }
}
