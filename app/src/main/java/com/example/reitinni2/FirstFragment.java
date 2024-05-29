package com.example.reitinni2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.reitinni2.databinding.FragmentFirstBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class FirstFragment extends Fragment {

private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

      binding = FragmentFirstBinding.inflate(inflater, container, false);
      return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //récupère l'id de l'input
        TextInputEditText pseudoFirstPlayerLayout = binding.inputFirstPlayer;

        binding.buttonFirstPlayer.setOnClickListener(v -> {
            // recupere le pseudo de l'input
            String pseudoFirstPlayer = pseudoFirstPlayerLayout.getText().toString();
            Log.i("pseudo", "Pseudo Player 1: " + pseudoFirstPlayer);
            // recupere dans la page mainActivity les infos de MainActivity (variable pseudo)
            MainActivity mainActivity = (MainActivity) getActivity();
            // donne la valeur de l'input au pseudo 1 de MainActivity
            mainActivity.pseudo1 = pseudoFirstPlayer;

            /*
            TextView pseudo1 = mainActivity.findViewById(R.id.response_pseudo_1);
            TextView titrePseudo1 = mainActivity.findViewById(R.id.pseudo_player_1);
            pseudo1.setText(pseudoFirstPlayer);
            */

            // on verifie que le champ input est bien rempli et on accede au fragment 2 du 2eme joueur
            if(!pseudoFirstPlayer.isEmpty()) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}