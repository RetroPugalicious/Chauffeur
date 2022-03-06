package com.rnb.chauffeur.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.rnb.chauffeur.MainActivity;
import com.rnb.chauffeur.R;
import com.rnb.chauffeur.RoomActivity;
import com.rnb.chauffeur.SearchActivity;
import com.rnb.chauffeur.logic.Room;
import com.rnb.chauffeur.logic.User;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class LeaderFragment extends Fragment implements View.OnClickListener {

    String roomcode;
    String role;
    User localUser;
    String searchCriteria = "";

    String location;
    int radius;

    Handler handler = new Handler();
    Runnable runnable;
    int delay = 10000;

    View rootview;

    public LeaderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle args = getArguments();
        roomcode = args.getString("ROOM", "error");
        role = args.getString("ROLE", "undetermined");
        //localUser = (User) args.getSerializable("USER");

        if (role.equals("Solo")) {
            Objects.requireNonNull(getActivity()).setTitle(role);
        } else {
            Objects.requireNonNull(getActivity()).setTitle(roomcode);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_leader, container, false);

        TextView distance = rootview.findViewById(R.id.rangeDisplay);
        distance.setText(R.string.ten_miles);
        radius = 10;

        SeekBar rangeBar = rootview.findViewById(R.id.rangeSlider);
        rangeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar bar, int progress, boolean fromUser) {
                distance.setText(String.valueOf(progress) + getString(R.string.miles));
                radius = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        RadioButton radioButton = rootview.findViewById(R.id.aeCheckbox);
        radioButton.setOnClickListener(this::onRadioButtonClicked);
        radioButton = rootview.findViewById(R.id.bsCheckbox);
        radioButton.setOnClickListener(this::onRadioButtonClicked);
        radioButton = rootview.findViewById(R.id.fCheckbox);
        radioButton.setOnClickListener(this::onRadioButtonClicked);
        radioButton = rootview.findViewById(R.id.nCheckbox);
        radioButton.setOnClickListener(this::onRadioButtonClicked);
        radioButton = rootview.findViewById(R.id.rCheckbox);
        radioButton.setOnClickListener(this::onRadioButtonClicked);
        radioButton = rootview.findViewById(R.id.sCheckbox);
        radioButton.setOnClickListener(this::onRadioButtonClicked);

        Button startSearch = rootview.findViewById(R.id.beginSearchButton);
        startSearch.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {
        if (searchCriteria.equals("")) {
            Toast toast = Toast.makeText(getActivity(), "Please select a category!", Toast.LENGTH_LONG);
            toast.show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle("Enter Address (Specific Addresses Produce More Accurate Distances):");
            // Set up the input
            final EditText input = new EditText(getContext());
            // Specify the type of input expected
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            // Set up the buttons
            builder.setPositiveButton("Confirm", (dialog, which) -> {
                String dialogText = input.getText().toString();
                logText(dialogText);
                Intent i1 = new Intent(getContext(), SearchActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("ROOM", roomcode);
                bundle1.putString("LOCATION", location);
                bundle1.putString("TYPE", searchCriteria);
                bundle1.putInt("RANGE", radius);
                i1.putExtras(bundle1);
                startActivity(i1);
            });
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

            builder.show();
        }
    }

    public void logText(String text) {
        location = text;
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.aeCheckbox:
                if (checked)
                    searchCriteria = "arts";
                break;
            case R.id.bsCheckbox:
                if (checked)
                    searchCriteria = "beautysvc";
                break;
            case R.id.fCheckbox:
                if (checked)
                    searchCriteria = "food";
                break;
            case R.id.nCheckbox:
                if (checked)
                    searchCriteria = "nightlife";
                break;
            case R.id.rCheckbox:
                if (checked)
                    searchCriteria = "restaurants";
                break;
            case R.id.sCheckbox:
                if (checked)
                    searchCriteria = "shopping";
                break;
        }
    }
}