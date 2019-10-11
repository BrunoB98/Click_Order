package com.example.oopproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class activity_order extends AppCompatProject {


    TextView vo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Bundle bundle = getIntent().getExtras();
        Order o = (Order) bundle.get("ordine");
        vo = findViewById(R.id.view_order);
       // vo.setText(o.PrintOrder(o));
    }
}
