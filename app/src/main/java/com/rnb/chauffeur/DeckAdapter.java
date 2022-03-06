package com.rnb.chauffeur;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DeckAdapter extends BaseAdapter {

    // on below line we have created variables
    // for our array list and context.
    private ArrayList<PlaceModal> placeData;
    private Context context;

    // on below line we have created constructor for our variables.
    public DeckAdapter(ArrayList<PlaceModal> placeData, Context context) {
        this.placeData = placeData;
        this.context = context;
    }

    @Override
    public int getCount() {
        // in get count method we are returning the size of our array list.
        return placeData.size();
    }

    @Override
    public Object getItem(int position) {
        // in get item method we are returning the item from our array list.
        return placeData.get(position);
    }

    @Override
    public long getItemId(int position) {
        // in get item id we are returning the position.
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // in get view method we are inflating our layout on below line.
        View v = convertView;
        if (v == null) {
            // on below line we are inflating our layout.
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_rv_item, parent, false);
        }
        // on below line we are initializing our variables and setting data to our variables.
        ((TextView) v.findViewById(R.id.cardPlaceName)).setText(placeData.get(position).getCourseName());
        ((TextView) v.findViewById(R.id.cardPlaceDescription)).setText(placeData.get(position).getCourseDescription());
        ((TextView) v.findViewById(R.id.cardPlaceRating)).setText(placeData.get(position).getCourseDuration());
        ((TextView) v.findViewById(R.id.cardPlacePrice)).setText(placeData.get(position).getCourseTracks());
        ((TextView) v.findViewById(R.id.cardPlaceReviewNum)).setText(placeData.get(position).getCourseTracks());
        ((TextView) v.findViewById(R.id.cardPlaceAlias)).setText(placeData.get(position).getCourseTracks());
        Picasso.get().load(placeData.get(position).getImgURL()).into((ImageView) v.findViewById(R.id.cardPlaceImage));
        return v;
    }
}