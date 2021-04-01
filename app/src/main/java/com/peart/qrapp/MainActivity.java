package com.peart.qrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.zxing.WriterException;
import com.google.zxing.client.result.VCardResultParser;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

import static android.content.ClipData.newIntent;

public class MainActivity extends AppCompatActivity {
    Button generateBtn, scanBtn, contactsBtn, selfInfoBtn;
    ImageView qrImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize values
        selfInfoBtn = findViewById(R.id.editSelf);
        generateBtn = findViewById(R.id.genButton);
        scanBtn = findViewById(R.id.scanBtn);
        contactsBtn = findViewById(R.id.contactsBtn);

        qrImage = findViewById(R.id.qrPlaceHolder);


        selfInfoBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SelfInfo.class));
            }
        });

        generateBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Get the data from the selfInfo activity
                //Intent intent = getIntent();
                //Bundle contact = intent.getExtras();

                Bundle contact = new Bundle();
                contact.putString("BEGIN","VCARD");
                contact.putString("name", "Matthew Peart");
                contact.putString("postal","126 calf pasture ln");
                contact.putString("email", "matthewpeart@hotmail.com");
                ;

                QRGEncoder qrgEncoder = new QRGEncoder(null, contact, QRGContents.Type.CONTACT,500);
                Bitmap qrBits = qrgEncoder.getBitmap();
                qrImage.setImageBitmap(qrBits);
            }
        });


        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Scanner.class));
            }
        });

        contactsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Contacts.class));
            }
        });
    }
}

