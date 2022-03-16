package com.example.solution.ui.Main;

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


public class MainViewModel extends ViewModel {
    private FirebaseAuth firebaseAuth;
    private MutableLiveData<String> mText;
    private MutableLiveData<String> mtext_name;
    private DatabaseReference firebaseDatabase;
    private String gname;

    public MainViewModel() {
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseAuth =  FirebaseAuth.getInstance();
        String uid = firebaseAuth.getCurrentUser().getUid();
        mtext_name = new MutableLiveData<>();

        firebaseDatabase.child("Users").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users group = snapshot.getValue(Users.class);
                gname = group.getname();

                mtext_name.setValue(gname);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public LiveData<String> getname() {
        return mtext_name;
    }

}
