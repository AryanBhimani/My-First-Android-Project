package com.sm.finalproject.Recording;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.sm.finalproject.R;
import java.io.File;
import java.io.IOException;

public class AudioListView extends Activity {
    ConstraintLayout audio_playersheet;
    BottomSheetBehavior bottomSheetBehavior;
    RecyclerView record_list;
    File[] allFiles;
    Rec_list_adapter rec_list_adapter;
    MediaPlayer mediaPlayer = null;
    boolean isPlaying = false;
    File file_toPlay;
    ImageButton play_prev_btn,play_forw_btn,play_btn;
    TextView player_file_name,player_title;
    SeekBar seekBar;
    Handler seekbarHandler;
    Runnable updateseekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_andio_recorderfiles);
        ControlBindd();
        String rec_path_files = getExternalFilesDir("/").getAbsolutePath();
        File dir = new File(rec_path_files);
        allFiles = dir.listFiles();
        rec_list_adapter = new Rec_list_adapter(allFiles, this);
        record_list.setHasFixedSize(true);
        record_list.setLayoutManager(new LinearLayoutManager(new AudioListView()));
        record_list.setAdapter(rec_list_adapter);

        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState == BottomSheetBehavior.STATE_HIDDEN) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });
        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPlaying) {
                    pauseAudio();
                } else {
                    if(file_toPlay!= null) {
                        resumeAudio();
                    }
                }
            }
        });
        play_prev_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseAudio();
                int curr_pos = mediaPlayer.getCurrentPosition();
                mediaPlayer.seekTo(curr_pos-1000);
                int seek_prog = seekBar.getProgress();
                seekBar.setProgress(seek_prog-10);
                updateRunnable();
                resumeAudio();
            }
        });
        play_forw_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseAudio();
                int curr_pos = mediaPlayer.getCurrentPosition();
                mediaPlayer.seekTo(curr_pos+1000);
                int seek_prog = seekBar.getProgress();
                seekBar.setProgress(seek_prog+10);
                updateRunnable();
                resumeAudio();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                pauseAudio();
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                mediaPlayer.seekTo(progress);
                resumeAudio();
            }
        });
    }
    public void onClick_Listener(File file, int position) {
        file_toPlay = file;
        if(isPlaying) {
            stopAudio();
            playAudio(file_toPlay);
        } else {
            playAudio(file_toPlay);
        }
    }
    private void stopAudio() {
        play_btn.setImageDrawable(getResources().getDrawable(R.drawable.player_play_btn,null));
        player_title.setText("Stopped");
        isPlaying = false;
        mediaPlayer.stop();
    }
    private void playAudio(@NonNull File file_toPlay) {
        mediaPlayer = new MediaPlayer();
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        try {
            mediaPlayer.setDataSource(file_toPlay.getAbsolutePath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        play_btn.setImageDrawable(getResources().getDrawable(R.drawable.player_pause_btn,null));
        player_file_name.setText(file_toPlay.getName());
        player_title.setText("Playing");
        isPlaying = true;
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stopAudio();
                player_title.setText("Finsihed");
            }
        });
        seekBar.setMax(mediaPlayer.getDuration());
        seekbarHandler = new Handler();
        updateRunnable();
        seekbarHandler.postDelayed(updateseekbar,0);
    }
    private void updateRunnable() {
        updateseekbar = new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                seekbarHandler.postDelayed(this,500);
            }
        };
    }
    private void pauseAudio() {
        mediaPlayer.pause();
        play_btn.setImageDrawable(getResources().getDrawable(R.drawable.player_play_btn,null));
        isPlaying = false;
        seekbarHandler.removeCallbacks(updateseekbar);
    }
    private void resumeAudio() {
        mediaPlayer.start();
        play_btn.setImageDrawable(getResources().getDrawable(R.drawable.player_pause_btn,null));
        isPlaying = true;
        updateRunnable();
        seekbarHandler.postDelayed(updateseekbar,0);
    }
    @Override
    public void onStop() {
        super.onStop();
        if(isPlaying) {
            stopAudio();
        }
    }
    private void ControlBindd() {
        audio_playersheet = findViewById(R.id.playersheet);
        bottomSheetBehavior = BottomSheetBehavior.from(audio_playersheet);
        record_list = findViewById(R.id.record_recycler_list);
        play_btn = findViewById(R.id.play_btn);
        play_prev_btn = findViewById(R.id.play_back_btn);
        play_forw_btn = findViewById(R.id.play_forward_btn);
        player_file_name = findViewById(R.id.player_file_name);
        player_title = findViewById(R.id.player_title);
        seekBar = findViewById(R.id.seekbar_player);
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), Audio.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}