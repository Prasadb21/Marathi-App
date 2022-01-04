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


public class FamilyFragment extends Fragment {
    MediaPlayer players;

    private MediaPlayer.OnCompletionListener mCompletion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };



    public FamilyFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_list, container, false);
        final ArrayList<word> numbers = new ArrayList<word>();

        numbers.add(new word("father" , "वडील" , R.drawable.family_father , R.raw.father));
        numbers.add(new word("mother" , "आई" , R.drawable.family_mother  , R.raw.mom));
        numbers.add(new word("son" , "मुलगा" , R.drawable.family_son   , R.raw.son));
        numbers.add(new word("daughter" , "मुलगी" , R.drawable.family_daughter , R.raw.daughter));
        numbers.add(new word("older brother" , "मोठा भाऊ" , R.drawable.family_older_brother , R.raw.bigbrother));
        numbers.add(new word("younger brother" , "लहान भाऊ" , R.drawable.family_younger_brother , R.raw.smallbro));
        numbers.add(new word("older sister" ,    "मोठी बहीण" , R.drawable.family_older_sister , R.raw.bigsister));
        numbers.add(new word("younger sister" , "धाकटी बहीण" , R.drawable.family_younger_sister , R.raw.smallsis));
        numbers.add(new word("grandmother" , "आजी" , R.drawable.family_grandmother , R.raw.grandmother));
        numbers.add(new word("grandfather" , "आजोबा" , R.drawable.family_grandfather , R.raw.grandfather));


        WordAdaptor adaptor = new WordAdaptor(getActivity() , numbers , R.color.category_family);

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

