package com.example.marek.frenchapp;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumberActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer = new MediaPlayer();
    private AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            switch (i) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    mediaPlayer.start();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    mediaPlayer.stop();
                    releaseMediaPlayer();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    mediaPlayer.pause();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    mediaPlayer.stop();
                    releaseMediaPlayer();
                    break;
            }
        }
    };
    MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "lutti"));
        words.add(new Word("two", "tik"));
        words.add(new Word("three", "rik"));
        words.add(new Word("four", "sik"));
        words.add(new Word("five", "lol"));
        words.add(new Word("six", "cyk"));
        words.add(new Word("seven", "byk"));
        words.add(new Word("three", "rik"));
        words.add(new Word("four", "sik"));
        words.add(new Word("five", "lol"));
        words.add(new Word("six", "cyk"));
        words.add(new Word("seven", "byk"));
        words.add(new Word("three", "rik"));
        words.add(new Word("four", "sik"));
        words.add(new Word("five", "lol"));
        words.add(new Word("six", "cyk"));
        words.add(new Word("seven", "byk"));
        ListView rootView = (ListView) findViewById(R.id.rootView);
        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_numbers);
        rootView.setAdapter(itemsAdapter);
        rootView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = (Word) adapterView.getItemAtPosition(i);
                if (word.hasMusic()) {
                    mediaPlayer.release();
                    audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), word.getMusicResources());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                }
            }
        });
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaPlayer();
    }
}


