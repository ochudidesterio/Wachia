package com.example.wachia.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.wachia.R;
import com.example.wachia.navigationMenu.NavigationDrawerMenu;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.FileOutputStream;

import static com.example.wachia.navigationMenu.NavigationDrawerMenu.counterNumber;

import static com.example.wachia.navigationMenu.NavigationDrawerMenu.email;
import static com.example.wachia.navigationMenu.NavigationDrawerMenu.idnumber;
import static com.example.wachia.navigationMenu.NavigationDrawerMenu.phonenumber;



public class QrCode extends AppCompatActivity {
    private ImageView qr_imageView;
    private Button qr_button;
    private String Qr_idnumber,Qr_email,Qr_phonenumber,Qr_description,Qr_date,Qr_Counter;

    Uri uri = null;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(QrCode.this, NavigationDrawerMenu.class);
        startActivity(i);
        email.setText(null);
        idnumber.setText(null);
        phonenumber.setText(null);
        counterNumber.setText(null);
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);


        qr_imageView = (ImageView)findViewById(R.id.Qr_imageView);
        qr_button = (Button)findViewById(R.id.qr_send_button);

        Qr_idnumber = idnumber.getText().toString().trim();
        Qr_phonenumber=phonenumber.getText().toString().trim();
        Qr_email=email.getText().toString().trim();
        Qr_Counter = counterNumber.getText().toString().trim();




        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();


        try {
            BitMatrix bitMatrix = multiFormatWriter.encode("ID NUMBER: "+Qr_idnumber+"\n"+"\n"+
                    "EMAIL: "+Qr_email+"\n"+"\n"+"PHONE NUMBER: "+Qr_phonenumber+"\n"+"\n"+"COUNTER NUMBER: "+Qr_Counter, BarcodeFormat.QR_CODE,200,200);
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


        OnClickingQRcodeSendButton();
    }

    public void OnClickingQRcodeSendButton(){
        qr_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ImageView emailImage = (ImageView)findViewById(R.id.Qr_imageView);
//
                Bitmap bitmap = ((BitmapDrawable)emailImage.getDrawable()).getBitmap();
                Bitmap btp = qr_imageView.getDrawingCache();

                File imagesFolder = new File(getCacheDir(),"images");
                try{
                    imagesFolder.mkdirs();
                    File file = new File(imagesFolder,"shared_image.png");
                    FileOutputStream stream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG,90,stream);
                    stream.flush();
                    stream.close();
                    uri = FileProvider.getUriForFile(QrCode.this,"com.example.wachia.fileprovider",file);

                }catch (Exception e){
                    e.printStackTrace();
                }
//
//
                // uri = FileProvider.getUriForFile(getApplicationContext(),getPackageName() + ".my.package.name.provider",new File(String.valueOf(bitmap)));
                String[] recipients = {email.getText().toString()};
                Intent sendmail = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));



                sendmail.setType("image/*");

                sendmail.putExtra(Intent.EXTRA_EMAIL, recipients);
                sendmail.putExtra(Intent.EXTRA_SUBJECT,"Your QR code");
                sendmail.putExtra(Intent.EXTRA_STREAM,uri);
                sendmail.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(Intent.createChooser(sendmail, "Choose an email client from..."));
            }
        });
    }

}
