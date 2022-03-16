package com.example.solution;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class AudioAdapter extends RecyclerView.Adapter{
 ArrayList<Uri> dataModels;
        Context context;

private OnIconClickListener listener = null;
public interface OnIconClickListener {
    void onItemClick(View view, int position);
}

    // 2. 리스너 객체를 전달하는 메서드와 전달된 객체를 저장할변수 추가
    public void setOnItemClickListener(OnIconClickListener listener) {
        this.listener = listener;
    }

    //생성자를 통하여 데이터 리스트 context를 받음
    public AudioAdapter(Context context, ArrayList<Uri> dataModels) {
        this.dataModels = dataModels;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        //데이터 리스트의 크기를 전달해주어야 함
        return dataModels.size();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //자신이 만든 itemview를 inflate한 다음 뷰홀더 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.compliment_audio, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        //생선된 뷰홀더를 리턴하여 onBindViewHolder에 전달한다.
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder)holder;

        String uriName = String.valueOf(dataModels.get(position));
        File file = new File(uriName);

        myViewHolder.audioTitle.setText(file.getName());
    }


public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageButton audioBtn;
    TextView audioTitle;
    CheckBox audiocheckBox;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        audioBtn = itemView.findViewById(R.id.playBtn_itemAudio);
        audioTitle = itemView.findViewById(R.id.audioTitle_itemAudio);
        audiocheckBox = itemView.findViewById(R.id.checkBox);

        audioBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //3. 아이템 클릭 이벤트 핸들러 메스드에서 리스너 객체 메서드 호출
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    // 리스너 객체의 메서드 호출.
                    if (listener != null) {
                        listener.onItemClick(view, pos);
                    }
                }
            }
        });
    }
}}