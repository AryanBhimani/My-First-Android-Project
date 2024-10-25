package com.sm.finalproject.Recording;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.loader.content.CursorLoader;
import com.sm.finalproject.R;
import java.io.File;
public class AudioFile extends Activity {
    static final int AUDIO_REQUEST = 1;
    final int PICK_AUDIO = 1;
    TextView select_Audio;
    Button Audio;
    String filepath;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_file);
        ControlBindd();
        findViewById(R.id.imgBack1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Audio = findViewById(R.id.autofill);
        Audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getString(R.string.Select), Toast.LENGTH_SHORT).show();
                Intent audio = new Intent();
                audio.setType("audio/*");
                audio.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(audio, "Select Audio"), PICK_AUDIO);
            }
        });
    }
    private void ControlBindd() {
        select_Audio = findViewById(R.id.select_Audio);
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == AUDIO_REQUEST && null != data) {
            if (requestCode == AUDIO_REQUEST) {
                Uri uri = data.getData();
                try {
                    File file = new File(uri.getPath());
                    final String[] split = file.getPath().split(":");
                    filepath = split[1];
                    select_Audio.setText(file.getPath());
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), R.string.Unable, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    private String getAudioPath(Uri uri) {
        String[] data = {MediaStore.Audio.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(), uri, data, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), Audio.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}