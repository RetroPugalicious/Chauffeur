package com.rnb.chauffeur;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.rnb.chauffeur.fragment.LeaderFragment;
import com.rnb.chauffeur.fragment.MemberFragment;
import com.rnb.chauffeur.logic.Room;
import com.rnb.chauffeur.logic.User;

import java.io.Serializable;
import java.lang.reflect.Member;
import java.net.MalformedURLException;

public class RoomActivity extends AppCompatActivity {

    String role = "undetermined";
    String roomcode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        TextView errorText = findViewById(R.id.error);
        errorText.setVisibility(View.INVISIBLE);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            role = bundle.getString("ROLE");
            roomcode = bundle.getString("roomcode");
        }

        if(role.equals("Member")) {
            Room myRoom = new Room();
            int index = 0;
            try {
                index = myRoom.joinRoom(roomcode);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            User localUser = new User(index, roomcode);
            MemberFragment f = new MemberFragment();
            Bundle args = new Bundle();
            args.putString("ROOM", roomcode);
            args.putString("ROLE", role);
            f.setArguments(args);
            replaceFragment(f);
        }
        else if (role.equals("Leader")) {
            Room myRoom = new Room();
            try {
                roomcode = myRoom.createRoom();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            int index = 0;
            try {
                index = myRoom.joinRoom(roomcode);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            User localUser = new User(index, roomcode);
            LeaderFragment f = new LeaderFragment();
            // Supply index input as an argument.
            Bundle args = new Bundle();
            args.putString("ROOM", roomcode);
            args.putString("ROLE", role);
            f.setArguments(args);
            replaceFragment(f);
        }
        else if (role.equals("Solo")) {
            Room myRoom = new Room();
            try {
                roomcode = myRoom.createRoom();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            int index = 0;
            try {
                index = myRoom.joinRoom(roomcode);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            User localUser = new User(index, roomcode);
            LeaderFragment f = new LeaderFragment();
            // Supply index input as an argument.
            Bundle args = new Bundle();
            args.putString("ROOM", roomcode);
            args.putString("ROLE", role);
            f.setArguments(args);
            replaceFragment(f);
        }
        else
            errorText.setVisibility(View.VISIBLE);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}