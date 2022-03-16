package com.example.solution.ui.minigame;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.solution.LoginActivity;
import com.example.solution.MainActivity;
import com.example.solution.R;
import com.example.solution.RegisterActivity;
import com.example.solution.Users;
import com.example.solution.ui.gallery.Fragment2;
import com.example.solution.ui.minigame.GameViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class easyFragment extends Fragment implements View.OnClickListener {
    private GameViewModel gameViewModel;
    private DatabaseReference firebaseDatabase;

    Button[] btn = new Button[8];
    boolean red[] = new boolean[8];
    int btnId[] = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8};
    Chronometer chronometer;
    int r[] = new int[4], t[] = new int[4], num[] = new int[8];
    double initTime;
    int check, num1 = -1, num2 = -1;
    public static String time = "";
    private FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        gameViewModel =
                new ViewModelProvider(this).get(GameViewModel.class);
        View root = inflater.inflate(R.layout.fragment_easy, container, false);

        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            r[i] = random.nextInt(90) + 10;
            for (int j = 0; j < i; j++) {
                if (r[i] == r[j]) {
                    i--;
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            while (true) {
                int n = random.nextInt(4);

                if (t[n] == 0) {
                    t[n] = 1;
                    num[i] = r[n];
                    break;
                } else if (t[n] == 1) {
                    t[n] = 2;
                    num[i] = r[n];
                    break;
                }
            }
        }

        chronometer = (Chronometer) root.findViewById(R.id.chronometer);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();

        for (int i = 0; i < 8; i++) {
            btn[i] = (Button) root.findViewById(btnId[i]);
            btn[i].setOnClickListener(this);
            red[i] = false;
        }
        return root;
    }


    public void onClick(View view) {
        for (int i = 0; i < 8; i++) {
            if (view == btn[i]) {
                btn[i].setText(String.valueOf(num[i]));

                if (num1 == -1 && num2 == -1) {
                    num1 = i;
                } else if (num1 != -1 && num2 == -1) {
                    if (num1 != i) {
                        num2 = i;
                    }
                }
            }
        }

        if (num1 != -1 && num2 != -1) {
            compare(num1, num2);
            num1 = -1;
            num2 = -1;
        }
    }

    public void compare(final int num1, final int num2) {
        if (btn[num1].getText().toString().equals(btn[num2].getText().toString())) {
            btn[num1].setBackgroundColor(Color.RED);
            btn[num1].setClickable(false);
            red[num1] = true;

            btn[num2].setBackgroundColor(Color.RED);
            btn[num2].setClickable(false);
            red[num2] = true;

            check++;
            if (check == 4) {
                chronometer.stop();

                time = chronometer.getText().toString();

             /*   Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("time",chronometer.getText().toString());//번들에 넘길 값 저장
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                resultFragment ResultFragment = new resultFragment();//프래그먼트2 선언
                ResultFragment.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.nav_easy, ResultFragment);
                transaction.commit();*/

                Navigation.findNavController(getView()).navigate(R.id.action_nav_easy_to_nav_result);

            }
        } else {
            for (int i = 0; i < 8; i++) {
                if (red[i] == false) {
                    btn[i].setClickable(false);
                }
            }

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    btn[num1].setText("");
                    btn[num2].setText("");

                    for (int i = 0; i < 8; i++) {
                        if (red[i] == false) {
                            btn[i].setClickable(true);
                        }
                    }
                }
            }, 2000);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - initTime > 3000) {//처음 누른 상태
                Toast.makeText(getContext(), "홈 화면으로 돌아가려면 한 번 더 눌러주세요", Toast.LENGTH_SHORT).show();

                initTime = System.currentTimeMillis();
            } else {
                Navigation.findNavController(getView()).navigate(R.id.action_nav_easy_to_nav_result);

            }
        }

        return false;
    }
    }





