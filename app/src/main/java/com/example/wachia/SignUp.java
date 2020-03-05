package com.example.wachia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.wachia.navigationMenu.NavigationDrawerMenu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

private EditText SignUpEmailEditText,SignUpPasswordtext;

public static FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        SignUpEmailEditText = (EditText) findViewById(R.id.signup_email);
        SignUpPasswordtext = (EditText) findViewById(R.id.signup_password);
        //firebaseAuth = FirebaseAuth.getInstance();
        findViewById(R.id.sign_up_button).setOnClickListener(this);
        findViewById(R.id.sign_up_login_button).setOnClickListener(this);
    }
    public void registerUser(){
        String userEmail = SignUpEmailEditText.getText().toString().trim();
        String password = SignUpPasswordtext.getText().toString().trim();

        if(userEmail.isEmpty()){
            SignUpEmailEditText.setError("email required");
            SignUpEmailEditText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            SignUpEmailEditText.setError("please enter a valid email");
            SignUpEmailEditText.requestFocus();
            return;
        }
        if(password.isEmpty()){
            SignUpPasswordtext.setError("password required");
            SignUpPasswordtext.requestFocus();
            return;
        }
        if(password.length()<6){
            SignUpPasswordtext.setError("minimum length of password should be 6, try again");
            SignUpPasswordtext.requestFocus();
            return;
        }



        firebaseAuth.createUserWithEmailAndPassword(userEmail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Intent intent = new Intent(SignUp.this, NavigationDrawerMenu.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK) ;
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                    finish();
                }else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"You are already a user",Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                SignUpEmailEditText.setText(null);
                SignUpPasswordtext.setText(null);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_up_button:
                    registerUser();
                break;
            case R.id.sign_up_login_button:
                startActivity(new Intent(this,MainActivity.class));
                break;
        }

    }
}
