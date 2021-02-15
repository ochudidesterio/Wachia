package com.example.wachia.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wachia.R;

public class ScanResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);
        String s = getIntent().getStringExtra("RESULT");

        TextView t = (TextView)findViewById(R.id.textResult);
        t.setText(s);
    }
}
