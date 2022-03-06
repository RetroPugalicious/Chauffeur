package com.rnb.chauffeur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class VictoryActivity extends AppCompatActivity {

    String name, distance, price, image, phone, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            name = bundle.getString("victoryName");
            distance = bundle.getString("victoryDistance");
            price = bundle.getString("victoryPrice");
            image = bundle.getString("victoryImage");
            phone = bundle.getString("victoryPhone");
            address = bundle.getString("victoryAddress");
        }

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(name);

        ImageView imageView = findViewById(R.id.victoryPlaceImage);
        Picasso.get().load(image).into(imageView);

        TextView nameText = findViewById(R.id.victoryPlaceName);
        nameText.setText(name);

        TextView ratingText = findViewById(R.id.victoryPlaceDistance);
        ratingText.setText(distance);

        TextView priceText = findViewById(R.id.victoryPlacePrice);
        priceText.setText(price);

        TextView phoneText = findViewById(R.id.victoryPlacePhoneNumber);
        phoneText.setText(phone);

        TextView addressText = findViewById(R.id.victoryPlaceAddress);
        addressText.setText(address);


    }

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(VictoryActivity.this, MainActivity.class));
        finish();
        return true;
    }
}