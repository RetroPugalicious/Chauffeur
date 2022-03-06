package com.rnb.chauffeur;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

        Button startSearch = rootview.findViewById(R.id.beginSearchButton);
        startSearch.setOnClickListener(this);

        return rootview;
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getActivity(), SearchActivity.class));
    }
}