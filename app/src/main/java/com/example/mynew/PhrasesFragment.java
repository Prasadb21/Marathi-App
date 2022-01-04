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


public class PhrasesFragment extends Fragment {
    MediaPlayer players;

    private MediaPlayer.OnCompletionListener mCompletion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };



    public PhrasesFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_list, container, false);


        final ArrayList<word> numbers = new ArrayList<word>();

        numbers.add(new word("Where are you going?" , "तुम्ही कुठे जात आहात?" , R.raw.tumhi));
        numbers.add(new word("What is your name?" , "तुझं नाव काय आहे?" , R.raw.whatsyourname));
        numbers.add(new word("My name is..." , "माझं नावं आहे..." , R.raw.mynameis));
        numbers.add(new word("How are you feeling?" , "तुला कसे वाटत आहे?" , R.raw.howare));
        numbers.add(new word("I’m feeling good." , "मला बरं वाटतेय." , R.raw.feelgood));
        numbers.add(new word("Are you coming?" , "तू येत आहेस का?" , R.raw.areyoucoming));
        numbers.add(new word("Yes, I’m coming." , "होय, मी येत आहे" , R.raw.yesiam));
        numbers.add(new word("I’m coming." , "मी येतोय" , R.raw.iamcoming));
        numbers.add(new word("Let’s go." , "चल जाऊया" , R.raw.letsgo));
        numbers.add(new word("Come here." , "इकडे ये" , R.raw.comehere));

        WordAdaptor adaptor = new WordAdaptor(getActivity() , numbers , R.color.category_phrases);

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