package com.rnb.chauffeur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RoomJoinActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_join);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Join Room");

        Button joinRoom = findViewById(R.id.joinButton);
        joinRoom.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(RoomJoinActivity.this, RoomActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("role", "Member");
        i.putExtras(bundle);
        startActivity(i);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}