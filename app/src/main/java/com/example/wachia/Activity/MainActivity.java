package com.example.wachia.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wachia.R;
import com.example.wachia.navigationMenu.NavigationDrawerMenu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import static com.example.wachia.Activity.SignUp.firebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Button login,sign_up;
    private EditText LoginPasswordText, LoginEmailText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getStarted=(Button)findViewById(R.id.getStarted);
        sign_up=(Button) findViewById(R.id.sign_up_login_button);
        login= (Button) findViewById(R.id.sign_up_button);
        LoginPasswordText=(EditText)findViewById(R.id.signup_password);
        LoginEmailText=(EditText)findViewById(R.id.signup_email);

       //OnClickinGstartedButton();
        findViewById(R.id.sign_up_login_button).setOnClickListener(this);
        findViewById(R.id.sign_up_button).setOnClickListener(this);


    }


//    public void OnClickinGstartedButton(){
//
//        Animation animation = AnimationUtils.loadAnimation(this,R.anim.blink);
//        getStarted.startAnimation(animation);
//        getStarted.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(".Drawer");
//                startActivity(intent);
//            }
//        });
//}
    public void onClickingLoginButton(){

        String userEmail = LoginEmailText.getText().toString().trim();
        String password = LoginPasswordText.getText().toString().trim();

        if(userEmail.isEmpty()){
            LoginEmailText.setError("email required");
            LoginEmailText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            LoginEmailText.setError("please enter a valid email");
            LoginEmailText.requestFocus();
            return;
        }
        if(password.isEmpty()){
            LoginPasswordText.setError("password required");
            LoginPasswordText.requestFocus();
            return;
        }
        if(password.length()<6){
            LoginPasswordText.setError("minimum length of password should be 6, try again");
            LoginPasswordText.requestFocus();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(userEmail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                 if(task.isSuccessful()){
                     finish();
                     Intent intent = new Intent(MainActivity.this, NavigationDrawerMenu.class);
                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK) ;
                     intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                     startActivity(intent);
                     //finish();

                 }else {
                     Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                 }
                LoginPasswordText.setText(null);
                LoginEmailText.setText(null);
            }
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(firebaseAuth.getCurrentUser() != null){
//            finish();
//            startActivity(new Intent(this,NavigationDrawerMenu.class));
//        }
//    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.sign_up_login_button:
            finish();
            startActivity( new Intent(this, SignUp.class));
            break;
        case R.id.sign_up_button:
                onClickingLoginButton();
            break;
    }
    }
}
