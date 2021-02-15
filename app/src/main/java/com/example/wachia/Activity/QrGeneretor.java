package com.example.wachia.Activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wachia.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import static com.example.wachia.navigationMenu.NavigationDrawerMenu.description;
import static com.example.wachia.navigationMenu.NavigationDrawerMenu.email;
import static com.example.wachia.navigationMenu.NavigationDrawerMenu.idnumber;
import static com.example.wachia.navigationMenu.NavigationDrawerMenu.phonenumber;

public class QrGeneretor extends AppCompatActivity {
    private ImageView qr_imageView;
    private Button qr_button;
    private String Qr_idnumber,Qr_email,Qr_phonenumber,Qr_description;

    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        qr_imageView = (ImageView)findViewById(R.id.Qr_imageView);
        qr_button = (Button)findViewById(R.id.qr_send_button);

        Qr_idnumber = idnumber.getText().toString().trim();
        Qr_phonenumber=phonenumber.getText().toString().trim();
        Qr_email=email.getText().toString().trim();
        Qr_description = description.getText().toString().trim();



        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();


        try {
            BitMatrix bitMatrix = multiFormatWriter.encode("ID NUMBER: "+Qr_idnumber+"\n"+
                    "EMAIL: "+Qr_email+"\n"+"PHONE NUMBER: "+Qr_phonenumber+"\n"+"LUGGAGE DESCRIPTION: "+Qr_description, BarcodeFormat.QR_CODE,200,200);
            Bitmap bitmap = Bitmap.createBitmap(200,200,Bitmap.Config.RGB_565);
            for(int x=0;x<200;++x){
                for(int y=0;y<200;++y){
                    bitmap.setPixel(x,y,bitMatrix.get(x,y)? Color.BLACK : Color.WHITE);
                }
            }
            qr_imageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }


        onClickingQrCodeSendButton();
    }

    public void onClickingQrCodeSendButton(){
        qr_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
