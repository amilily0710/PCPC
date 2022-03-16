package com.example.solution.ui.graph;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.solution.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraphFragment extends Fragment {

    private GraphViewModel graphViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        graphViewModel =
                new ViewModelProvider(this).get(GraphViewModel.class);
        View root = inflater.inflate(R.layout.fragment_graph, container, false);

        BarChart barChart = root.findViewById(R.id.barChart);

        //샘플 데이터
        ArrayList<BarEntry> visitors = new ArrayList<>();
        visitors.add(new BarEntry(2014, 420));
        visitors.add(new BarEntry(2015, 450));
        visitors.add(new BarEntry(2016, 520));
        visitors.add(new BarEntry(2017, 620));
        visitors.add(new BarEntry(2018, 540));


        BarDataSet barDataSet = new BarDataSet(visitors, "성장");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Bar Chart Example");
        barChart.animateY(2000);

        return root;
    }
}