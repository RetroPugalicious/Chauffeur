package com.rnb.chauffeur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RoomActivity extends AppCompatActivity {

    String role = "undetermined";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        TextView errorText = findViewById(R.id.error);
        errorText.setVisibility(View.INVISIBLE);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("ROOM");

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
            role = bundle.getString("role");

        if(role.equals("Member"))
            replaceFragment(new MemberFragment());
        else if (role.equals("Leader"))
            replaceFragment(new LeaderFragment());
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