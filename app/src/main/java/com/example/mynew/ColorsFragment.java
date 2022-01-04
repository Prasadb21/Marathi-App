package com.example.mynew;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class ColorsFragment extends Fragment {

    MediaPlayer players;

    private MediaPlayer.OnCompletionListener mCompletion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };



    public ColorsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_list, container, false);
        final ArrayList<word> numbers = new ArrayList<word>();

        numbers.add(new word("red" , "लाल" , R.drawable.color_red   , R.raw.red));
        numbers.add(new word("green" , "हिरवा" , R.drawable.color_green  , R.raw.green));
        numbers.add(new word("brown" , "तपकिरी" , R.drawable.color_brown  , R.raw.brown));
        numbers.add(new word("gray" , "राखाडी" , R.drawable.color_gray  , R.raw.gray));
        numbers.add(new word("black" , "काळा" , R.drawable.color_black  , R.raw.black));
        numbers.add(new word("white" , "पांढरा" , R.drawable.color_white  , R.raw.white));
        numbers.add(new word("dusty yellow" , "धूळ पिवळा" , R.drawable.color_dusty_yellow  , R.raw.dustyyellow));
        numbers.add(new word("yellow" , "पिवळा" , R.drawable.color_mustard_yellow  , R.raw.yellow));

        WordAdaptor adaptor = new WordAdaptor(getActivity() , numbers , R.color.category_colors);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(adaptor);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                word whichPosition = numbers.get(i);
                releaseMediaPlayer();
                players = MediaPlayer.create(getActivity(), whichPosition.getAudioResource() );
                players.start();
                players.setOnCompletionListener(mCompletion);

            }
        });
        return rootView;

    }

    @Override
    public void onStop() {
        super.onStop();

        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (players != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            players.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            players = null;


        }
    }

}