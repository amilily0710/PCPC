package com.example.solution.ui.information;

import android.content.Intent;
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
import com.example.solution.compliment;
import com.example.solution.ui.gallery.GalleryViewModel;

public class inforFragment extends Fragment implements View.OnClickListener {

    private inforViewModel inforViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        inforViewModel =
                new ViewModelProvider(this).get(inforViewModel.class);
        View root = inflater.inflate(R.layout.fragment_information, container, false);
        final TextView textView = root.findViewById(R.id.text_email);
        final TextView textView1 = root.findViewById(R.id.text_name);


        inforViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        inforViewModel.getname().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView1.setText(s);
            }
        });
        Button onButtonClickedbutton_compliment = (Button) root.findViewById(R.id.button_compliment);
        onButtonClickedbutton_compliment.setOnClickListener(this);


        return root;
    }



    @Override
    public void onClick(View v) {
        Button b = (Button) v;

        //id에 따라서 다른 구현을 한다.
        if (b.getId() == R.id.button_compliment) {//버튼 클릭시 아래 구현이 실행된다.
            System.out.println("InputRecord clicked");
            getActivity().startActivity(new Intent(getActivity(), compliment.class));
        }
    }
}