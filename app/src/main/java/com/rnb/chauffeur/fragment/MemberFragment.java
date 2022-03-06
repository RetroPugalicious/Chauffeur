package com.rnb.chauffeur.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rnb.chauffeur.R;
import com.rnb.chauffeur.logic.Room;

import java.io.IOException;

public class MemberFragment extends Fragment {

    String roomcode;
    String role;

    Handler handler = new Handler();
    Runnable runnable;
    int delay = 10000;

    View rootview;

    public MemberFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle args = getArguments();
        if (args != null) {
            roomcode = args.getString("ROOM", "error");
            role = args.getString("ROLE", "undetermined");
        }
        getActivity().setTitle(roomcode);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_member, container, false);

        TextView members = rootview.findViewById(R.id.memberList);
        try {
            members.setText(getResources().getText(R.string.members) + " " + String.valueOf(Room.getUsers(roomcode)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rootview;
    }

    @Override
    public void onResume() {
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                handler.postDelayed(runnable, delay);
                System.out.println("Update users!");
                TextView members = rootview.findViewById(R.id.memberList);
                try {
                    members.setText(getResources().getText(R.string.members) + " " + String.valueOf(Room.getUsers(roomcode)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, delay);
        super.onResume();
    }

    @Override
    public void onPause() {
        handler.removeCallbacks(runnable);
        super.onPause();
    }
}