package com.rnb.chauffeur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureButton();
    }

    private void configureButton() {
        Button soloButton = findViewById(R.id.solo);
        Button partyButton = findViewById(R.id.party);
        soloButton.setOnClickListener(this);
        partyButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.solo:
                startActivity(new Intent(MainActivity.this, RoomActivity.class));
                break;
            case R.id.party:
                startActivity(new Intent(MainActivity.this, RoomActivity.class));
                break;
        }
    }
}