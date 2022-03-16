package com.example.solution.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.ListFragment;

import com.example.solution.R;

public class Fragment2 extends ListFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_gallery_2, container, false);

        // xml의 listview id를 반드시 "@android:id/list"로 해줘야 한다.
        String[] values = new String[] {"ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅗ","ㅛ", "ㅜ","ㅠ","ㅡ","ㅣ"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

        return rootView;
    }

    //아이템 클릭 이벤트
    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        String strText = (String) l.getItemAtPosition(position);
        Log.d("Fragment: ", position + ": " +strText);
        Toast.makeText(this.getContext(), "클릭: " + position +" " + strText, Toast.LENGTH_SHORT).show();
    }
}
