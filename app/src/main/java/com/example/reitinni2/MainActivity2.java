package com.example.reitinni2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView responsePseudo1 = findViewById(R.id.response_pseudo_1);
        TextView responsePseudo2 = findViewById(R.id.response_pseudo_2);

        Intent intent = getIntent();
        String pseudo1 = intent.getStringExtra("PSEUDO_1"); // récupère le pseudo1 du MainActivity
        String pseudo2 = intent.getStringExtra("PSEUDO_2"); // récupère le pseudo2 du MainActivity

        responsePseudo1.setText(pseudo1);
        responsePseudo2.setText(pseudo2);

    }
}