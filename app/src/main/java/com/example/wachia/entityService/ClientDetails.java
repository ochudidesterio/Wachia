package com.example.wachia.entityService;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wachia.Entity.ClientDAO;
import com.example.wachia.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//import static com.example.wachia.MainActivity.myDataBaseHelper;

public class ClientDetails extends AppCompatActivity {
    EditText idnumber,phonenumber,email,description;
    Button submit;
    public static DatabaseReference databaseReference;
    public static ClientDAO clientDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client);
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

                clientDAO = new ClientDAO();
                int idNo= Integer.parseInt(idnumber.getText().toString().trim());
                int phone = Integer.parseInt(phonenumber.getText().toString().trim());

                clientDAO.setIdNumber(idNo);
                clientDAO.setPhoneNumber(phone);
                clientDAO.setEmail(email.getText().toString().trim());
                clientDAO.setDescription(description.getText().toString().trim());

                databaseReference.push().setValue(clientDAO);

                idnumber.setText(null);
                phonenumber.setText(null);
                email.setText(null);
                description.setText(null);

                Toast.makeText(ClientDetails.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
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
