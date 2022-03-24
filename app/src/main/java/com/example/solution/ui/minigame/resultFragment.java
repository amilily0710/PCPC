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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;

public class resultFragment extends Fragment {
    private GameViewModel gameViewModel;
    private String time;
    private TextView result;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

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
        firebaseAuth =  FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Time");
        result = root.findViewById(R.id.result);
        time = easyFragment.time;
        result.setText(time);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        String email = user.getEmail();
        //       FirebaseDatabase database = FirebaseDatabase.getInstance();
        //       DatabaseReference reference = database.getReference("Users");
        databaseReference.child("email").setValue(email);
        databaseReference.child("time").setValue(time);
        Button gohome = (Button) root.findViewById(R.id.gohome);

        gohome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_result_to_nav_main);
            }
        });
        return root;
    }
}
