package com.foodbreak.vishad.mediaplayerapp;

import android.annotation.TargetApi;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.foodbreak.vishad.mediaplayerapp.model.ListDetails;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MediaPlayer mediaPlayer;
    private ImageView imageView;
    private TextView leftTime;
    private TextView rightTime;
    private SeekBar seekBar;
    private Button prevButton;
    private Button playButton;
    private Button nextButton;
    private Thread thread;
    TextView name;
    TextView title;

   Array ar[]= new Array[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            setUpUI();
            name = (TextView) findViewById(R.id.textView);
            title = (TextView) findViewById(R.id.textView2);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b){
                    mediaPlayer.seekTo(i);

                }
                SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
                int currentPos = mediaPlayer.getCurrentPosition();
                int duration = mediaPlayer.getDuration();
                leftTime.setText(dateFormat.format(new Date(currentPos)));

                rightTime.setText(dateFormat.format(new Date(duration-currentPos)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void setUpUI(){
        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.piano);
        imageView = (ImageView) findViewById(R.id.imageView);
        leftTime = (TextView) findViewById(R.id.leftTime);
        rightTime = (TextView) findViewById(R.id.rightTime);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        prevButton = (Button) findViewById(R.id.prevButton);
        playButton = (Button) findViewById(R.id.playButton);
        nextButton = (Button) findViewById(R.id.nextButton);
        prevButton.setOnClickListener(this);
        playButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);


    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void onClick(View v){
        switch (v.getId()){
            case R.id.prevButton:
                backMusic();
                break;
            case R.id.playButton:
                if (mediaPlayer.isPlaying()){
                    pauseMusic();
                }
                else {
                    startMusic();
                }

                break;
            case R.id.nextButton:
                nextMusic();
                break;
        }
    }
    public void pauseMusic(){
      if (mediaPlayer != null){
          mediaPlayer.pause();
          playButton.setBackgroundResource(android.R.drawable.ic_media_play);
      }
    }
    public void startMusic(){
        if (mediaPlayer != null){
            mediaPlayer.start();
            updateThread();
            playButton.setBackgroundResource(android.R.drawable.ic_media_pause);
        }
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void backMusic(){
//        if (mediaPlayer.isPlaying()){
//            mediaPlayer.seekTo(0);
//        }

       seekBar.setProgress(0);
       mediaPlayer.stop();
        playButton.setBackgroundResource(android.R.drawable.ic_media_play);
            mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.so);

    }
    public void nextMusic(){
//        if (mediaPlayer.isPlaying()){
//            mediaPlayer.seekTo(mediaPlayer.getDuration() - 1000);
//        }
     seekBar.setProgress(0);
     mediaPlayer.stop();
        playButton.setBackgroundResource(android.R.drawable.ic_media_play);
            mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.song);

    }
    public void updateThread(){
        thread = new Thread(){
          public void run(){
              try{
                  while (mediaPlayer != null && mediaPlayer.isPlaying() ) {
                      Thread.sleep(50);
                      runOnUiThread(new Runnable() {
                          @Override
                          public void run() {
                               int newPosition = mediaPlayer.getCurrentPosition();
                               int newMax = mediaPlayer.getDuration();
                               seekBar.setMax(newMax);
                               seekBar.setProgress(newPosition);
                               //updte the text
                              leftTime.setText(String.valueOf(new SimpleDateFormat("mm:ss").format(new Date(mediaPlayer.getCurrentPosition()))));
                              rightTime.setText(String.valueOf(new SimpleDateFormat("mm:ss").format(new Date(mediaPlayer.getDuration() - mediaPlayer.getCurrentPosition()))));

                          }
                      });
                  }
              }catch (InterruptedException e){
                  e.printStackTrace();
              }
          }
        };thread.start();

    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        thread.interrupt();
        thread = null;
        super.onDestroy();
    }
}
