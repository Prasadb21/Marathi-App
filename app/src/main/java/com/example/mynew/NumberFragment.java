package com.example.mynew;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.UserDictionary;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class NumberFragment extends Fragment {
    MediaPlayer players;

    private MediaPlayer.OnCompletionListener mCompletion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };



    public NumberFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_list, container, false);
        final ArrayList<word> numbers = new ArrayList<word>();

            numbers.add(new word("one" , "एक" , R.drawable.number_one , R.raw.one));
            numbers.add(new word("Two" , "दोन" , R.drawable.number_two , R.raw.two));
            numbers.add(new word("Three" , "तीन" ,R.drawable.number_three , R.raw.three));
            numbers.add(new word("Four" , "चार" , R.drawable.number_four , R.raw.four));
            numbers.add(new word("Five" , "पाच" , R.drawable.number_five , R.raw.five));
            numbers.add(new word("Six" , "सहा" , R.drawable.number_six , R.raw.six));
            numbers.add(new word("Seven" , "सात" , R.drawable.number_seven , R.raw.seven));
            numbers.add(new word("Eight" , "आठ" , R.drawable.number_eight , R.raw.eight));
            numbers.add(new word("Nine" , "नऊ" , R.drawable.number_nine , R.raw.nine));
            numbers.add(new word("Ten" , "दहा" , R.drawable.number_ten , R.raw.ten));

        WordAdaptor adaptor = new WordAdaptor(getActivity() , numbers , R.color.category_numbers);

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







