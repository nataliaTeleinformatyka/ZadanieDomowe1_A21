package com.example.zadaniedomowe1;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final int CONTACT_ACTIVITY_REQUEST_CODE = 2;
    private static final int SOUND_ACTIVITY_REQUEST_CODE = 1;
    private String name = "John Dee";
    private int startSound=5;
    private int start;
    private MediaPlayer mp;

    public void myClickHandler(View view) {
        TextView txtV = (TextView) findViewById(R.id.textView);
        ImageView imgV = (ImageView) findViewById(R.id.imageView);
        switch (view.getId()) {
            case R.id.button:
                Button contact = (Button) findViewById(R.id.button);
                break;
            case R.id.button2:
                Button sound = (Button) findViewById(R.id.button2);
                break;
            default:
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        Button contact = (Button) findViewById(R.id.button);
        Button sound = (Button) findViewById(R.id.button2);
        ImageView imgV = (ImageView)  findViewById(R.id.imageView);

        int images [] = {R.drawable.kowalski, R.drawable.dee, R.drawable.kowalska,R.drawable.lisiak, R.drawable.nowak};
        imgV.setImageResource(images[0]); //poczatkowy obraz przy wlaczeniu aplikacji

        changeContact();
        changeSound();
        addListenerOnButton();
    }
    public void changeSound() {
        Button sound = (Button) findViewById(R.id.button2);
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentContact = new Intent(MainActivity.this, Sound.class);
                if (mp.isPlaying()) {
                    mp.stop();
                }
                startActivityForResult(intentContact,SOUND_ACTIVITY_REQUEST_CODE);
            }
        });
    }
    public void changeContact () {
        Button contact = (Button) findViewById(R.id.button);

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentContact = new Intent(getApplicationContext(),Contact.class);
                if (mp.isPlaying()) {
                    mp.stop();
                }
                intentContact.putExtra("name", name);
                startActivityForResult(intentContact,CONTACT_ACTIVITY_REQUEST_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final TextView txtV = (TextView) findViewById(R.id.textView);

        if (resultCode == RESULT_OK && requestCode == CONTACT_ACTIVITY_REQUEST_CODE) {
            if (data.hasExtra("value")) {
                String named = data.getExtras().getString("value") ;
                txtV.setText(named);
                name = named;
                RandomImage();
                addListenerOnButton();
            }
        }
        if (resultCode == RESULT_OK && requestCode == SOUND_ACTIVITY_REQUEST_CODE) {
            if (data.hasExtra("soundNumber")) {
                startSound = data.getExtras().getInt("soundNumber");
                addListenerOnButton();
            }
        }
        if(resultCode != RESULT_OK) {
            addListenerOnButton();
        }
    }
    protected void RandomImage() {
        Random rand = new Random();
        start = rand.nextInt(6);
        ImageView imgV = (ImageView)  findViewById(R.id.imageView);
        int images [] = {R.drawable.kowalski, R.drawable.dee, R.drawable.kowalska,R.drawable.lisiak, R.drawable.nowak};
        imgV.setImageResource(images[start]);
    }
    public void addListenerOnButton() {
        switch(startSound) {
            case 0:
                mp = MediaPlayer.create(MainActivity.this, R.raw.sound1);
                break;
            case 1:
                mp = MediaPlayer.create(MainActivity.this, R.raw.sound2);
                break;
            case 2:
                mp = MediaPlayer.create(MainActivity.this, R.raw.sound3);
                break;
            case 3:
                mp = MediaPlayer.create(MainActivity.this, R.raw.sound4);
                break;
            case 4:
                mp = MediaPlayer.create(MainActivity.this, R.raw.sound5);
                break;
            case 5:
                mp = MediaPlayer.create(MainActivity.this, R.raw.sound5);
                break;
            default:
                Toast.makeText(MainActivity.this, "Nie wybrano dźwięku. ", Toast.LENGTH_SHORT).show();
        }
        FloatingActionButton buttonPlayer = findViewById(R.id.fab);

        buttonPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp.isPlaying()) {
                    mp.pause();
                }
                else {
                    mp.start();
                    mp.setLooping(true); // odtwarzanie w petli
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}