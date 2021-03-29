package com.peart.qrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

import static android.content.ClipData.newIntent;

public class MainActivity extends AppCompatActivity {
    EditText qrvalue;
    Button generateBtn,myContactInfo, scanBtn, contactsBtn;
    ImageView qrImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize values
        qrvalue = findViewById(R.id.qrInput);
        generateBtn = findViewById(R.id.genButton);
        scanBtn = findViewById(R.id.scanBtn);
        qrImage = findViewById(R.id.qrPlaceHolder);
        contactsBtn = findViewById(R.id.contactsBtn);
        myContactInfo = findViewById(R.id.myContactBtn);

        generateBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String data = qrvalue.getText().toString();
                //Contact contactInfo = getContact(find(Matt));

                if(data.isEmpty()){
                    qrvalue.setError("Value Required.");
                }else {
                    QRGEncoder qrgEncoder = new QRGEncoder(data, null,QRGContents.Type.TEXT,500);
                    Bitmap qrBits = qrgEncoder.getBitmap();
                    qrImage.setImageBitmap(qrBits);
                }

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
                startActivity(new Intent(getApplicationContext(),Contacts.class));
            }
        });

        myContactInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MyContactInfo.class));
            }
        });

    }
}