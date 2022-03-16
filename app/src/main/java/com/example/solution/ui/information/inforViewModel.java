package com.example.solution.ui.information;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.solution.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class inforViewModel extends ViewModel {
    private FirebaseAuth firebaseAuth;
    private MutableLiveData<String> mtext_email;
    private MutableLiveData<String> mtext_name;
    private DatabaseReference firebaseDatabase;
    private String gemail;
    private String gname;
    TextView mtextview;



    public inforViewModel() {
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseAuth =  FirebaseAuth.getInstance();
        String uid = firebaseAuth.getCurrentUser().getUid();
        mtext_email = new MutableLiveData<>();
        mtext_name = new MutableLiveData<>();

        firebaseDatabase.child("Users").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users group = snapshot.getValue(Users.class);
                gemail = group.getemail();
                gname = group.getname();

                mtext_email.setValue(gemail);
                mtext_name.setValue(gname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public LiveData<String> getText() {
        return mtext_email;
    }
    public LiveData<String> getname() {
        return mtext_name;
    }
}