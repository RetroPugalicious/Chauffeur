package com.rnb.chauffeur;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class LeaderFragment extends Fragment implements View.OnClickListener {

    public LeaderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_leader, container, false);

        TextView distance = rootview.findViewById(R.id.rangeDisplay);
        distance.setText(R.string.ten_miles);

        SeekBar rangeBar = rootview.findViewById(R.id.rangeSlider);
        rangeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar bar, int progress, boolean fromUser) {
                distance.setText(String.valueOf(progress)+ getString(R.string.miles));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        Button startSearch = rootview.findViewById(R.id.beginSearchButton);
        startSearch.setOnClickListener(this);

        return rootview;
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getActivity(), SearchActivity.class));
    }
}