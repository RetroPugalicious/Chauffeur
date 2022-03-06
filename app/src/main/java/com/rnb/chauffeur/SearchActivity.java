package com.rnb.chauffeur;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.daprlabs.cardstack.SwipeDeck;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    // on below line we are creating variable
    // for our array list and swipe deck.
    private SwipeDeck cardStack;
    private ArrayList<PlaceModal> placeModalArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("ROOM");

        // on below line we are initializing our array list and swipe deck.
        placeModalArrayList = new ArrayList<>();
        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);

        // on below line we are adding data to our array list.
        placeModalArrayList.add(new PlaceModal("C++", "30 days", "20 Tracks", "C++ Self Paced Course", "https://i.imgur.com/DvpvklR.png"));
        placeModalArrayList.add(new PlaceModal("Java", "30 days", "20 Tracks", "Java Self Paced Course", "https://s3-media3.fl.yelpcdn.com/bphoto/H7v8lwod_6bzB7i8gkGznA/o.jpg"));
        placeModalArrayList.add(new PlaceModal("Python", "30 days", "20 Tracks", "Python Self Paced Course", "https://s3-media1.fl.yelpcdn.com/bphoto/aG4fcmhjWLZLroFHUqqLBQ/o.jpg"));
        placeModalArrayList.add(new PlaceModal("DSA", "30 days", "20 Tracks", "DSA Self Paced Course", "https://i.imgur.com/DvpvklR.png"));
        placeModalArrayList.add(new PlaceModal("PHP", "30 days", "20 Tracks", "PHP Self Paced Course", "https://i.imgur.com/DvpvklR.png"));

        // on below line we are creating a variable for our adapter class and passing array list to it.
        final DeckAdapter adapter = new DeckAdapter(placeModalArrayList, this);

        // on below line we are setting adapter to our card stack.
        cardStack.setAdapter(adapter);

        // on below line we are setting event callback to our card stack.
        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                // on card swipe left we are displaying a toast message.
                Toast.makeText(SearchActivity.this, "Card Swiped Left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cardSwipedRight(int position) {
                // on card swiped to right we are displaying a toast message.
                Toast.makeText(SearchActivity.this, "Card Swiped Right", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cardsDepleted() {
                // this method is called when no card is present
                Toast.makeText(SearchActivity.this, "No more courses present", Toast.LENGTH_SHORT).show();
                startActivity( new Intent(SearchActivity.this, VictoryActivity.class));
            }

            @Override
            public void cardActionDown() {
                // this method is called when card is swiped down.
                Log.i("TAG", "CARDS MOVED DOWN");
            }

            @Override
            public void cardActionUp() {
                // this method is called when card is moved up.
                Log.i("TAG", "CARDS MOVED UP");
            }
        });

        Button acceptButton = (Button) findViewById(R.id.acceptButton);
        acceptButton.setOnClickListener(this);
        Button rejectButton = (Button) findViewById(R.id.rejectButton);
        rejectButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.acceptButton:
                cardStack.swipeTopCardRight(1000);
                break;
            case R.id.rejectButton:
                cardStack.swipeTopCardLeft(1000);
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
