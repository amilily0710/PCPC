package com.example.solution.ui.graph;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GraphViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GraphViewModel() {

    }

    public LiveData<String> getText() {
        return mText;
    }
}
