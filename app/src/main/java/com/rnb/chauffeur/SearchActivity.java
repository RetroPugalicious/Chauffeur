package com.rnb.chauffeur;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.daprlabs.cardstack.SwipeDeck;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    // on below line we are creating variable
    // for our array list and swipe deck.
    private SwipeDeck cardStack;
    private ArrayList<PlaceModal> placeModalArrayList;

    String roomcode;
    String location;
    String criteria;
    int radius;
    int index = 0;
    String distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            roomcode = bundle.getString("ROOM");
            location = bundle.getString("LOCATION");
            criteria = bundle.getString("TYPE");
            radius = bundle.getInt("RANGE");
        }
        setTitle(roomcode);

        JSONObject yelpReturn = new JSONObject();

        try {
            yelpReturn = yelpCall(roomcode, location, criteria, radius * 1609);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        JSONArray businesses = new JSONArray();
        try {
            businesses = yelpReturn.getJSONArray("businesses");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // on below line we are initializing our array list and swipe deck.
        placeModalArrayList = new ArrayList<>();
        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);

        for (int i=0; i < businesses.length(); i++){
            JSONObject business = new JSONObject();
            try {
                business = businesses.getJSONObject(i);
                double away = Double.parseDouble(business.get("distance").toString());
                away = away/1069;
                distance = String.format("%.2f miles", away);
                PlaceModal newCard = new PlaceModal(business.get("name").toString(),
                        ("Rating: " + business.get("rating").toString()),
                        ("Total Reviews: " + business.get("review_count").toString()),
                        distance,
                        business.get("image_url").toString(),
                        ("Price: " + business.get("price").toString()),
                        business.get("display_phone").toString(),
                        business.get("alias").toString());
                placeModalArrayList.add(newCard);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // on below line we are creating a variable for our adapter class and passing array list to it.
        final DeckAdapter adapter = new DeckAdapter(placeModalArrayList, this);

        // on below line we are setting adapter to our card stack.
        cardStack.setAdapter(adapter);

        // on below line we are setting event callback to our card stack.
        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                // on card swipe left we are displaying a toast message.
                Toast.makeText(SearchActivity.this, "Voted No", Toast.LENGTH_SHORT).show();
                index++;
            }

            @Override
            public void cardSwipedRight(int position) {
                // on card swiped to right we are displaying a toast message.
                Toast.makeText(SearchActivity.this, "Voted Yes", Toast.LENGTH_SHORT).show();
                Intent i1 = new Intent(SearchActivity.this, VictoryActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("victoryImage", placeModalArrayList.get(index).getImgURL());
                bundle1.putString("victoryName", placeModalArrayList.get(index).getPlaceName());
                bundle1.putString("victoryDistance", distance);
                bundle1.putString("victoryPrice", placeModalArrayList.get(index).getPlacePrice());
                bundle1.putString("victoryPhone", placeModalArrayList.get(index).getPhone());
                bundle1.putString("victoryAddress", placeModalArrayList.get(index).getAddress());
                i1.putExtras(bundle1);
                startActivity(i1);
            }

            @Override
            public void cardsDepleted() {
                // this method is called when no card is present
                Toast.makeText(SearchActivity.this, "No more places present", Toast.LENGTH_SHORT).show();
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

    public JSONObject yelpCall(String room_number, String location, String type, int range) throws MalformedURLException {
        final JSONObject[] list = {new JSONObject()};

        String url = "http://192.168.254.69:5000/call/" + room_number + '/' + location +
                '/' + type + '/' + range;
        try {
            URL url1 = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(() -> {
            try  {
                try (InputStream is = new URL(url).openStream()) {
                    BufferedReader rd = new BufferedReader(new InputStreamReader(is,
                            Charset.forName("UTF-8")));
                    StringBuilder sb = new StringBuilder();
                    int cp;
                    while ((cp = rd.read()) != -1) {
                        sb.append((char) cp);
                    }
                    String read = sb.toString();
                    list[0] = new JSONObject(read);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread.start();

        while(true) {
            if (!thread.isAlive()) {
                return list[0];
            }
        }
    }
}
