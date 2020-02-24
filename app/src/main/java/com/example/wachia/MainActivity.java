package com.example.wachia;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

   public static DatabaseHelper myDataBaseHelper;
    public static Button getStarted;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        myDataBaseHelper = new DatabaseHelper(this);
        OnClickinGstartedButton();


    }

    public void OnClickinGstartedButton(){
        getStarted=(Button)findViewById(R.id.getStarted);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.blink);
        getStarted.startAnimation(animation);
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(".details_activity");
                startActivity(intent);
            }
        });

    }
}
