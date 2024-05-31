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

import com.example.reitinni2.databinding.ActivityMain2Binding;
import com.example.reitinni2.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    // declaration des variables dont on aura besoin
    public int mysteryNumber;
    private int currentPlayer = 1;
    private String pseudo1;
    private String pseudo2;
    private int player1Attempts = 0; //
    private int player2Attempts = 0;

    private ActivityMain2Binding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) { //fonction create de base
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // recupere les elements via leurs id et stocke dans une variable
        TextView responsePseudo1 = binding.responsePseudo1;
        TextView responsePseudo2 = binding.responsePseudo2;
        TextView currentTurn = binding.currentTurn;
        EditText inputNumber = binding.inputNumber;
        Button submitButton = binding.submitButton;

        // recupere les intentions envoyés par le MainActivity precedent
        Intent intent = getIntent();
        // récupère le pseudo1 du MainActivity
        pseudo1 = intent.getStringExtra("PSEUDO_1");
        // récupère le pseudo2 du MainActivity
        pseudo2 = intent.getStringExtra("PSEUDO_2");

        // ecrit le pseudo en Versus de presentation des 2 joueurs
        responsePseudo1.setText(pseudo1);
        // VS
        responsePseudo2.setText(pseudo2);

        // genere un nombre aleatoire de 0 à 1000 qui est le nombre qui sera a trouver par les joueurs
        Random random = new Random();
        mysteryNumber = random.nextInt(1000);

        // on ajoute un random pour savoir qui va jouer en 1er : joueur 1 ou 2
        Integer CurrentplayerStart = random.nextInt(2) + 1; // choix du nombre entre 1 et 2
        currentPlayer = CurrentplayerStart;
        Log.i("playerSelected", String.valueOf(currentPlayer));

        // on verifie qui est le joueur qui joue et donc son pseudo, si 1 = joueur 1, sinon = joueur 2
        String currentPlayerName = currentPlayer == 1 ? pseudo1 : pseudo2;
        currentTurn.setText("Tour de joueur "+ currentPlayer + " : " + currentPlayerName);

        //on ecoute le submit du bouton envoyer la proposition du currentPlayer
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //recupere la valeur de l'input de la proposition du player
                String NumberPropoInput = inputNumber.getText().toString();
                //on verifie que l'input n'est pas vide sinon on averti le joueur qu'il n'a rien mis
                if (!NumberPropoInput.isEmpty()) {
                    // try pour test et catch pouyr ressortir une erreur si probleme
                    try {
                        // on transforme l'input en integer
                        int proposedNumber = Integer.parseInt(NumberPropoInput);
                        // utilise la fonction checkNumber qui verifie si le nombre propose est le numero mystere
                        checkNumber(proposedNumber);
                        // efface le champ input pour le clean
                        inputNumber.setText("");
                        // utilise la fonction updateAttempts pour incrementer le nombre de coup pour chaque utlisateur apres chaque proposition
                        updateAttempts();
                        // change de player
                        switchTurn();
                        // affiche le nouveau nom du nouveau joueur a qui c'est le tour
                        String currentPlayerName = (currentPlayer == 1) ? pseudo1 : pseudo2;
                        currentTurn.setText("Tour de joueur "+ currentPlayer + " : " + currentPlayerName);
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity2.this, "Veuillez entrer un nombre valide", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity2.this, "Veuillez entrer un nombre", Toast.LENGTH_SHORT).show();
                }
            }

            //fonction pour compter le nombre de coup de chaque utilisateur
            private void updateAttempts() {
                if (currentPlayer == 1) {
                    // incremente de 1 le compteur de coups
                    player1Attempts++;
                } else {
                    player2Attempts++;
                }
            }
        });
    }

    // fonction pour verifier si le nombre propose est bon, plus grand ou plus petit
    private void checkNumber(int proposedNumber) {
        // si le joueur a trouve le nombre mystere
        if (proposedNumber == mysteryNumber) {
            // on recupere le pseudo du joueur qui vient de proposer son nombre
            String winner = currentPlayer == 1 ? pseudo1 : pseudo2;
            // on recupere le nombre de coup de ce joueur
            Integer attemptNumber = currentPlayer == 1 ? player1Attempts : player2Attempts;
            // on affiche un toast pour lui dire qu'il a gagne et son nombre de coup
            Toast.makeText(this, winner + " a gagné en " + attemptNumber + " coups ! Le nombre mystère était " + mysteryNumber, Toast.LENGTH_LONG).show();
            // Vous pouvez aussi démarrer une nouvelle activité ou retourner à l'écran principal
        } else if (proposedNumber < mysteryNumber) {
            //si nombre propose plus petit
            Toast.makeText(this, "Le nombre mystère est plus grand", Toast.LENGTH_SHORT).show();
        } else {
            //si nombre propose plus grand
            Toast.makeText(this, "Le nombre mystère est plus petit", Toast.LENGTH_SHORT).show();
        }
    }

    // fonction pour changer de joueur
    private void switchTurn() {
        currentPlayer = currentPlayer == 1 ? 2 : 1;
    }
}