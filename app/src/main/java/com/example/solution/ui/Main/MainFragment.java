package com.example.solution.ui.Main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.solution.R;
import com.example.solution.ui.home.HomeViewModel;
import com.example.solution.ui.information.inforViewModel;

public class MainFragment extends Fragment {
    private MainViewModel MainViewModel;
    ImageView imageView,imageView2,imageView3,ra,rabbit;;
    int count=0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MainViewModel =
                new ViewModelProvider(this).get(MainViewModel.class);
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textView1 = root.findViewById(R.id.textView4);

        MainViewModel.getname().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView1.setText(s);
            }
        });
        Button btn = (Button) root.findViewById(R.id.btn);
        count++;

        imageView = root.findViewById(R.id.star);
        imageView2 = root.findViewById(R.id.star2);
        imageView3 = root.findViewById(R.id.star3);
        ra = root.findViewById(R.id.gif_image);
        rabbit = root.findViewById(R.id.gif_image3);

        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(ra);
        Glide.with(getActivity()).load(R.drawable.ra).into(gifImage);

        //rabbit = (ImageView) findViewById(R.id.gif_image2);
        GlideDrawableImageViewTarget gifImage2 = new GlideDrawableImageViewTarget(rabbit);
        Glide.with(getActivity()).load(R.drawable.rabbit).into(gifImage2);

        Animation animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rotate);
        //imageView.startAnimation(animation);
        Animation animation2 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rot);
        //imageView2.startAnimation(animation2);
        Animation animation3 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.ro);

        View.OnClickListener listener = new View.OnClickListener() {
            ImageView star = (ImageView) root.findViewById(R.id.star);
            ImageView star2 = (ImageView) root.findViewById(R.id.star2);
            ImageView star3 = (ImageView) root.findViewById(R.id.star3);

            @Override
            public void onClick(View view) {
                if (count == 1) {
                    //  imageView.setImageResource(R.drawable.dream02);
                    // imageView.setImageResource(android.R.drawable.btn_star_big_on);
                    star.setVisibility(View.VISIBLE);
                    imageView.startAnimation(animation);
                    count++;
                    //level = true;
                } else if (count == 2) {
                    // imageView.setImageResource(R.drawable.dream01);
                    //imageView.setImageResource(android.R.drawable.star_big_on);
                    star2.setVisibility(View.VISIBLE);
                    imageView2.startAnimation(animation2);
                    count++;
                    // level = false;
                } else if (count == 3) {
                    // imageView.setImageResource(R.drawable.dream01);
                    //imageView.setImageResource(android.R.drawable.star_big_on);
                    star3.setVisibility(View.VISIBLE);
                    imageView3.startAnimation(animation3);
                    count++;
                    // level = false;
                }
            }


        };
        btn.setOnClickListener(listener);


        return root;
    }

}