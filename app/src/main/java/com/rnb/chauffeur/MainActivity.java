package com.rnb.chauffeur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.rnb.chauffeur.logic.User;

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
                Intent i = new Intent(MainActivity.this, RoomActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ROLE", "Solo");
                i.putExtras(bundle);
                startActivity(i);
                break;
            case R.id.party:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Would you like to join an existing party or create your own?")
                        .setPositiveButton("Create", (dialog, id) -> {
                            Intent i1 = new Intent(MainActivity.this, RoomActivity.class);
                            Bundle bundle1 = new Bundle();
                            bundle1.putString("ROLE", "Leader");
                            i1.putExtras(bundle1);
                            startActivity(i1);
                        })
                        .setNeutralButton("Cancel", (dialog, id) -> {

                        })
                        .setNegativeButton("Join", (dialog, id) -> startActivity(new Intent(MainActivity.this, RoomJoinActivity.class)));
                // Create the AlertDialog object and return it
                builder.show();
                break;
        }
    }
}