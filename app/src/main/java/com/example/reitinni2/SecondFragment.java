package com.example.reitinni2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        binding.buttonSecondPlayer.setOnClickListener(v ->{
            String pseudoSecondPlayer  = pseudoSecondPlayerLayout.getText().toString();
            Log.i("pseudo", "Pseudo Player 2: " + pseudoSecondPlayer);
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.pseudo2 = pseudoSecondPlayer;
            Log.i("test", mainActivity.pseudo1);

            TextView pseudo2 = mainActivity.findViewById(R.id.response_pseudo_2);
            TextView titrePseudo2 = mainActivity.findViewById(R.id.pseudo_player_2);
            pseudo2.setText(pseudoSecondPlayer);

            if(!pseudoSecondPlayer.isEmpty()) {
                // pseudo2.setVisibility(View.VISIBLE); //repasse le texte en visible
                // titrePseudo2.setVisibility(View.VISIBLE); //repasse le texte en visible

                Intent intent = new Intent(requireActivity(), MainActivity2.class);
                intent.putExtra("PSEUDO_1", mainActivity.pseudo1);
                intent.putExtra("PSEUDO_2", mainActivity.pseudo2);
                startActivity(intent);

            }
        });
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}