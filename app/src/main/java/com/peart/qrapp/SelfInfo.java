package com.peart.qrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SelfInfo extends AppCompatActivity {

 public static final String MY_CONTACT = "MyContactDetails";
    EditText name,phone,email,address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_info);

        name = findViewById(R.id.selfName);
        phone = findViewById(R.id.selfNumber);
        email = findViewById(R.id.selfEmail);
        address = findViewById(R.id.selfAddress);
        Button saveExit = findViewById(R.id.selfSave);

        Intent intent = new Intent(this, MainActivity.class);

        saveExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences contactSharedPreference = getSharedPreferences(MY_CONTACT,MODE_PRIVATE);
                SharedPreferences.Editor contactEditor = contactSharedPreference.edit();
                contactEditor.putString("name",name.getText().toString());
                contactEditor.putString("email",email.getText().toString());
                contactEditor.putString("address",address.getText().toString());
                contactEditor.putString("phone",phone.getText().toString());
                contactEditor.apply();


                Bundle b = new Bundle();
                b.putString("name", name.getText().toString());
                b.putString("postal", address.getText().toString());
                //b.putString("phone", phone);
                //b.putString("email", email);

                intent.putExtras(b);

                startActivity(intent);
            }
        });
    }
}