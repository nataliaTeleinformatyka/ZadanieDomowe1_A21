package com.example.zadaniedomowe1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Contact extends AppCompatActivity {
    public static String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Button confirmContact = (Button) findViewById(R.id.button4);
        Button cancelContact = (Button) findViewById(R.id.button3);

        final String name = getIntent().getStringExtra("name");
        checked(name);

        confirmContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioGroup rGroup = (RadioGroup) findViewById(R.id.radioGroup);
                value = ((RadioButton) findViewById(rGroup.getCheckedRadioButtonId())).getText().toString();
                if (name.equals(value)) {
                    finish();
                }
                else {
                    Intent intentContactReturn = new Intent(Contact.this, MainActivity.class);
                    intentContactReturn.putExtra("value", value);
                    setResult(RESULT_OK, intentContactReturn);
                    finish();
                }
            }
        });
        cancelContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void onRadioButtonClick(View view) {
        boolean checked =((RadioButton)view).isChecked();
        if (checked) {
            RadioGroup rGroup = (RadioGroup) findViewById(R.id.radioGroup);
            value = ((RadioButton) findViewById(rGroup.getCheckedRadioButtonId())).getText().toString();
            Toast.makeText(Contact.this, " Wybrano kontakt : " + value, Toast.LENGTH_SHORT).show();
        }
    }
    public void checked(String name) {
        switch (name) {
            case "Jan Kowalski":
                final RadioButton rb = (RadioButton)findViewById(R.id.radioButton);
                rb.setChecked(true);
                break;
            case "Krystian Nowak":
                final RadioButton rb2 = (RadioButton)findViewById(R.id.radioButton2);
                rb2.setChecked(true);
                break;
            case "Aleksandra Lisiak":
                final RadioButton rb3 = (RadioButton)findViewById(R.id.radioButton3);
                rb3.setChecked(true);
                break;
            case "Maria Kowalska" :
                final RadioButton rb4 = (RadioButton)findViewById(R.id.radioButton4);
                rb4.setChecked(true);
                break;
            case "John Dee":
                final RadioButton rb5 = (RadioButton)findViewById(R.id.radioButton5);
                rb5.setChecked(true);
                break;
        }
    }
}
