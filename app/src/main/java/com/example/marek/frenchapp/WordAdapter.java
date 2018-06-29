package com.example.marek.frenchapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.frenchapp.R;

import java.util.ArrayList;


/**
 * Created by marek on 17.04.2018.
 */


/**
 * Custom WordAdapter to show custom layout in listview
 */
public class WordAdapter extends ArrayAdapter<Word> {

    private int itemColor;

    public WordAdapter(Activity context, ArrayList<Word> array, int itemColor) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, array);
        this.itemColor = itemColor;
    }

    /**
     * showing custom view in listview on each position
     * default view is a single/double textview definied in android.R....
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }


        Word currentAndroidFlavor = getItem(position);

        ImageView image = (ImageView) listItemView.findViewById(R.id.imageView);
        if (currentAndroidFlavor.hasImage()) { //if word to recycle in listview has an image
            image.setImageResource(currentAndroidFlavor.getImageResourceId());
            image.setVisibility(View.VISIBLE);
        } else {
            image.setVisibility(View.GONE);
        }


        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.first);
        // set this text on the name TextView
        nameTextView.setText(currentAndroidFlavor.getDefaultTranslation());
        //textAppearance is better to use instead of setTextSize
        //textApperance is predefined size on andorid sdk for specific phone
        nameTextView.setTextAppearance(getContext(), android.R.style.TextAppearance_DeviceDefault_Medium);
        nameTextView.setTextColor(listItemView.getResources().getColor(R.color.textColor));
        nameTextView.setGravity(Gravity.BOTTOM);

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.second);
        // set this text on the number TextView
        numberTextView.setText(currentAndroidFlavor.getFrenchTranslation());
        numberTextView.setTextAppearance(getContext(), android.R.style.TextAppearance_DeviceDefault_Large);
        numberTextView.setTextColor(listItemView.getResources().getColor(R.color.textColor));
        numberTextView.setGravity(Gravity.TOP);

        View textItem = listItemView.findViewById(R.id.textItem);
        //contextcopmat use to get resource(color) safely
        int color = ContextCompat.getColor(listItemView.getContext(), itemColor);
        textItem.setBackgroundColor(color);

        View playButton = listItemView.findViewById(R.id.playButton);
        if (currentAndroidFlavor.hasMusic()) { //if word hasnt go an audio to play
            playButton.setVisibility(View.VISIBLE); //show playbutton
            playButton.setBackgroundColor(color);
        } else {
            playButton.setVisibility(View.GONE);
        }

        return listItemView;

    }
}
