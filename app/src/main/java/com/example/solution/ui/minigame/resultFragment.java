package com.example.solution.ui.minigame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.solution.R;
import com.example.solution.ui.home.HomeViewModel;

public class resultFragment extends Fragment {
    private GameViewModel gameViewModel;
    private String time;
    private TextView result;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        gameViewModel =
                new ViewModelProvider(this).get(GameViewModel.class);
        View root = inflater.inflate(R.layout.fragment_result, container, false);

        result = root.findViewById(R.id.result);

      /*  Bundle bundle = getArguments();
        if (bundle != null)
        {
            time = getArguments().getString("time"); // 프래그먼트1에서 받아온 값 넣기
            result.setText(time);
        }
       */
        Button gohome = (Button) root.findViewById(R.id.gohome);

        gohome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_result_to_nav_main);
            }
        });
        return root;
    }
}
