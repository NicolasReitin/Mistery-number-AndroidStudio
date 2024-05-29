package com.example.reitinni2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.reitinni2.databinding.FragmentSecondBinding;
import com.google.android.material.textfield.TextInputEditText;

public class SecondFragment extends Fragment {

private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

      binding = FragmentSecondBinding.inflate(inflater, container, false);
      return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // recupère l'input du 2eme joueur via l'id
        TextInputEditText pseudoSecondPlayerLayout = binding.inputSecondPlayer;

        //ecoute l'evenement du boutton
        binding.buttonSecondPlayer.setOnClickListener(v ->{
            // recupere l'input du player 2
            String pseudoSecondPlayer  = pseudoSecondPlayerLayout.getText().toString();
            Log.i("pseudo", "Pseudo Player 2: " + pseudoSecondPlayer);
            // recupere dans la page mainActivity les infos de MainActivity (variable pseudo)
            MainActivity mainActivity = (MainActivity) getActivity();
            // donne la valeur de l'input au pseudo 1 de MainActivity
            mainActivity.pseudo2 = pseudoSecondPlayer;
            Log.i("test", mainActivity.pseudo1);

            /*
            TextView pseudo2 = mainActivity.findViewById(R.id.response_pseudo_2);
            TextView titrePseudo2 = mainActivity.findViewById(R.id.pseudo_player_2);
            pseudo2.setText(pseudoSecondPlayer);
             */

            // verifie que l'input soit bien rempli
            if(!pseudoSecondPlayer.isEmpty()) {
                // creer une intention pour l'envoyer sur MainActivity2
                Intent intent = new Intent(requireActivity(), MainActivity2.class);
                // je selectionne les infos a envoyer
                intent.putExtra("PSEUDO_1", mainActivity.pseudo1);
                intent.putExtra("PSEUDO_2", mainActivity.pseudo2);
                // je demarre la nouvelle activité et quitte celle actuelle tout en envoyant les données de l'intent
                startActivity(intent);

            }
        });

        // fonction permettant de valider l'input avec la touche entree
        pseudoSecondPlayerLayout.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                    (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {
                // Trigger the button click programmatically
                binding.buttonSecondPlayer.performClick();
                return true;
            }
            return false;
        });
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}