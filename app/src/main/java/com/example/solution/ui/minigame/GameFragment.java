package com.example.solution.ui.minigame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.solution.R;

public class GameFragment extends Fragment {

    private GameViewModel gameViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        gameViewModel =
                new ViewModelProvider(this).get(GameViewModel.class);
        View root = inflater.inflate(R.layout.fragment_game, container, false);

        Button btnEasy = (Button) root.findViewById(R.id.btnEasy);

        btnEasy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               Navigation.findNavController(view).navigate(R.id.action_nav_game_to_nav_easy);
            }
        });
        return root;
    }
}