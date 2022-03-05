package com.rnb.chauffeur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("ROOM");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}