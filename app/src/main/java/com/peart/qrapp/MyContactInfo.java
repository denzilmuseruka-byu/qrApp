package com.peart.qrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyContactInfo extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText phone;
    Button addContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contact_info);

        name = findViewById(R.id.etName);
        email = findViewById(R.id.etEmail);
        phone = findViewById(R.id.etPhone);
        addContact = findViewById(R.id.btnAdd);

        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!name.getText().toString().isEmpty() && (!email.getText().toString().isEmpty() || !phone.getText().toString().isEmpty())){
                    Intent intent = new Intent(Intent.ACTION_INSERT_OR_EDIT);
                    intent.setType(ContactsContract.RawContacts.CONTENT_ITEM_TYPE);
                    intent.putExtra(ContactsContract.Intents.Insert.NAME,name.getText().toString());
                    intent.putExtra(ContactsContract.Intents.Insert.EMAIL,email.getText().toString());
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE,phone.getText().toString());

                    if(intent.resolveActivity(getPackageManager()) != null ){
                        startActivity(intent);
                    }else {
                        Toast.makeText(MyContactInfo.this,"There is no app that support this action",Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(MyContactInfo.this,"Please fill at least one contact info",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}