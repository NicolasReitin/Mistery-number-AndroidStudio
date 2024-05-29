package com.example.reitinni2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    // declaration des variables dont on aura besoin
    public int mysteryNumber;
    private int currentPlayer = 1;
    private String pseudo1;
    private String pseudo2;
    private int player1Attempts = 0; //
    private int player2Attempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //fonction create de base
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2); //
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // recupere les elements via l'id
        TextView responsePseudo1 = findViewById(R.id.response_pseudo_1);
        TextView responsePseudo2 = findViewById(R.id.response_pseudo_2);
        TextView currentTurn = findViewById(R.id.current_turn);
        EditText inputNumber = findViewById(R.id.input_number);
        Button submitButton = findViewById(R.id.submit_button);

        // recupere les intentions envoyés par le MainActivity precedent
        Intent intent = getIntent();
        pseudo1 = intent.getStringExtra("PSEUDO_1"); // récupère le pseudo1 du MainActivity
        pseudo2 = intent.getStringExtra("PSEUDO_2"); // récupère le pseudo2 du MainActivity

        // ecrit le pseudo en Versus
        responsePseudo1.setText(pseudo1);
        responsePseudo2.setText(pseudo2);

        Random random = new Random(); // genere un nombre aleatoire de 0 à 1000
        mysteryNumber = random.nextInt(1000);

        //---------------------
        String currentPlayerName = currentPlayer == 1 ? pseudo1 : pseudo2;
        currentTurn.setText("Tour de joueur "+ currentPlayer + " : " + currentPlayerName);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputNumber.getText().toString();
                if (!input.isEmpty()) {
                    int proposedNumber = Integer.parseInt(input);
                    checkNumber(proposedNumber);
                    inputNumber.setText(""); // Clear the input field
                    updateAttempts();
                    switchTurn();
                    String currentPlayerName = (currentPlayer == 1) ? pseudo1 : pseudo2;
                    currentTurn.setText("Tour de joueur "+ currentPlayer + " : " + currentPlayerName);
                } else {
                    Toast.makeText(MainActivity2.this, "Veuillez entrer un nombre", Toast.LENGTH_SHORT).show();
                }
            }
            private void updateAttempts() {
                if (currentPlayer == 1) {
                    player1Attempts++;
                } else {
                    player2Attempts++;
                }
            }
        });
    }
    private void checkNumber(int proposedNumber) {
        if (proposedNumber == mysteryNumber) {
            String winner = currentPlayer == 1 ? pseudo1 : pseudo2;
            Integer attemptNumber = currentPlayer == 1 ? player1Attempts : player2Attempts;
            Toast.makeText(this, winner + " a gagné en " + attemptNumber + " coups ! Le nombre mystère était " + mysteryNumber, Toast.LENGTH_LONG).show();
            // Vous pouvez aussi démarrer une nouvelle activité ou retourner à l'écran principal
        } else if (proposedNumber < mysteryNumber) {
            Toast.makeText(this, "Le nombre mystère est plus grand", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Le nombre mystère est plus petit", Toast.LENGTH_SHORT).show();
        }
    }

    private void switchTurn() {
        currentPlayer = currentPlayer == 1 ? 2 : 1;
    }
}