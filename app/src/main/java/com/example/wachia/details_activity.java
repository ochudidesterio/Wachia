package com.example.wachia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//import static com.example.wachia.MainActivity.myDataBaseHelper;

public class details_activity extends AppCompatActivity {
    EditText idnumber,phonenumber,email,description;
    Button submit;
    public static DatabaseReference databaseReference;
    public static Client client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_activity);
        idnumber =(EditText)findViewById(R.id.IDtextview);
        phonenumber =(EditText)findViewById(R.id.phoneNumberTextView);
        email =(EditText)findViewById(R.id.emailtextView);
        description =(EditText)findViewById(R.id.DescriptionView);
        submit =(Button) findViewById(R.id.submit);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Client");

        OnClickSubmit();
    }
    public  void OnClickSubmit(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                client = new Client();
                int idNo= Integer.parseInt(idnumber.getText().toString().trim());
                int phone = Integer.parseInt(phonenumber.getText().toString().trim());

                client.setIdNumber(idNo);
                client.setPhoneNumber(phone);
                client.setEmail(email.getText().toString().trim());
                client.setDescription(description.getText().toString().trim());

                databaseReference.push().setValue(client);

                idnumber.setText(null);
                phonenumber.setText(null);
                email.setText(null);
                description.setText(null);

                Toast.makeText(details_activity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                /*

               boolean isInserted= myDataBaseHelper.insertData(idnumber.getText().toString(),
                        phonenumber.getText().toString(),
                        email.getText().toString(),
                        description.getText().toString());
               if(isInserted=true)
                   Toast.makeText(details_activity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
               else
                   Toast.makeText(details_activity.this, "Failed to insert data", Toast.LENGTH_SHORT).show();

               idnumber.setText(null);
               phonenumber.setText(null);
               email.setText(null);
               description.setText(null);*/
            }
        });
    }
}
