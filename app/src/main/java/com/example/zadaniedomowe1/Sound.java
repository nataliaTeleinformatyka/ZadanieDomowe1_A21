package com.example.zadaniedomowe1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Sound extends AppCompatActivity {
    public static int soundNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        Button confirmSound = (Button) findViewById(R.id.button6);
        Button cancelSound = (Button) findViewById(R.id.button5);

        confirmSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSoundReturn = new Intent(Sound.this,MainActivity.class);
                intentSoundReturn.putExtra("soundNumber",soundNumber);
                setResult(RESULT_OK, intentSoundReturn);
                finish();
            }
        });
        cancelSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.soundArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int id, long position) {
                switch ((int) position) {
                    case 0:
                        Toast.makeText(Sound.this, "Wybrano dźwięk 1 z listy.", Toast.LENGTH_SHORT).show();
                        soundNumber =0;
                        break;
                    case 1:
                        Toast.makeText(Sound.this, "Wybrano dźwięk 2 z listy.", Toast.LENGTH_SHORT).show();
                        soundNumber =1;
                        break;
                    case 2:
                        Toast.makeText(Sound.this, "Wybrano dźwięk 3 z listy.", Toast.LENGTH_SHORT).show();
                        soundNumber =2;
                        break;
                    case 3:
                        Toast.makeText(Sound.this, "Wybrano dźwięk 4 z listy.", Toast.LENGTH_SHORT).show();
                        soundNumber =3;
                        break;
                    case 4:
                        Toast.makeText(Sound.this, "Wybrano dźwięk 5 z listy.", Toast.LENGTH_SHORT).show();
                        soundNumber =4;
                        break;
                default:
                    Toast.makeText(Sound.this, "Nie wybrano dźwięku.", Toast.LENGTH_SHORT).show();
                    soundNumber =5;
                    break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                Toast.makeText(Sound.this, "Błąd - Nie wybrano dźwięku z listy." , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
