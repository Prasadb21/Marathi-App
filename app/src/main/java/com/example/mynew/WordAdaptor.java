package com.example.mynew;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdaptor extends ArrayAdapter<word> {

    private int mColorResourceId ;



    public WordAdaptor(Activity context, ArrayList<word> numbers , int colorId) {

        super(context, 0, numbers);
        mColorResourceId = colorId;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        word currentWord = getItem(position);

        TextView mTextView = (TextView) listItemView.findViewById(R.id.marathi_TW);

        mTextView.setText(currentWord.getMarathiTranslation());

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_TW);

        ImageView iconView = (ImageView) listItemView.findViewById(R.id.image);

        if(currentWord.hasImage()) {
            iconView.setImageResource(currentWord.getImageResourceId());

            iconView.setVisibility(View.VISIBLE);
        }
        else {
            iconView.setVisibility(View.GONE);
        }

        View textColor = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext() , mColorResourceId);
        textColor.setBackgroundColor(color);


        defaultTextView.setText(currentWord.getDefaultTranslation());

        return listItemView;
    }
}
