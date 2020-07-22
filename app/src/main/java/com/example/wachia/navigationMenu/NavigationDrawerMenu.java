package com.example.wachia.navigationMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.annotation.SuppressLint;

import android.content.Intent;

import android.os.Bundle;

import android.util.Patterns;
import android.view.MenuItem;

import android.widget.EditText;
import android.widget.Toast;

import com.example.wachia.Activity.MainActivity;
import com.example.wachia.Activity.QrCode;
import com.example.wachia.Activity.Scan;
import com.example.wachia.Activity.ScanResult;
import com.example.wachia.Entity.ClientDAO;

import com.example.wachia.R;

import com.example.wachia.menuFragments.ClientFragment;
import com.example.wachia.menuFragments.ScanFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;



public class NavigationDrawerMenu extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,ScanFragment.onFragmentScanButtonSelected,ClientFragment.onClientSubmitSelected {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
   public static EditText idnumber,phonenumber,email,description,date,counterNumber;
   public static DatabaseReference databaseReference;
   public static ClientDAO clientDAO;



    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawermenu);
        actionBarDrawerToggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment_container, new ClientFragment()).commit();

        }

        navigationView.setCheckedItem(R.id.nav_client);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawermenu);
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);

        }else {
            if(fragment instanceof ClientFragment){
                super.onBackPressed();
            }else {
                showHome();
            }
        }

    }
    Fragment fragment = null;
    private void showHome(){
        fragment = new ClientFragment();
        if(fragment != null){
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.nav_fragment_container,fragment).commit();
        }
    }


    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_client:
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment_container, new ClientFragment()).commit();
                    break;
            case R.id.nav_scanner:
                     getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment_container, new ScanFragment()).commit();
                     break;
            case R.id.nav_logout:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
        }
 drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onScanButtonselected() {



        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scanning");
        integrator.setCameraId(0);
        integrator.setOrientationLocked(true);//changes
        integrator.setBeepEnabled(true);///////
        integrator.setBarcodeImageEnabled(false);
        integrator.setCaptureActivity(Scan.class);//chane
        integrator.initiateScan();



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        final String scanResult = result.getContents();
        if(result !=null){
            if(result.getContents() == null){
                Toast.makeText(this,"You cancelled the scan",Toast.LENGTH_LONG).show();

            }else {
                Intent intent = new Intent(this, ScanResult.class);

                intent.putExtra("RESULT",scanResult);
                startActivity(intent);
                //Toast.makeText(this,result.getContents(),Toast.LENGTH_LONG).show();
            }
        }else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
    @Override
    public void onSubmitButtonSelected() {
        idnumber =(EditText)findViewById(R.id.IDtextview);
        phonenumber =(EditText)findViewById(R.id.phoneNumber);
        email =(EditText)findViewById(R.id.emailEditText);

        counterNumber = (EditText)findViewById(R.id.counterTextView);


        databaseReference= FirebaseDatabase.getInstance().getReference().child("Client");

        clientDAO = new ClientDAO();

        String mEmail = email.getText().toString().trim();
        String mId = idnumber.getText().toString();
        String mPhone = phonenumber.getText().toString();
        String mCounter = counterNumber.getText().toString();


        if(idnumber.getText().toString().isEmpty()){
            idnumber.setError("Id required");
            idnumber.requestFocus();
            return;
        }
        else if(mId.length()<6 || mId.length()>6){
            idnumber.setError("Id must be 6 numbers");
            idnumber.requestFocus();
            return;
        }
        else if(mPhone.isEmpty()){
            phonenumber.setError("Phone Number Rquired");
            phonenumber.requestFocus();
            return;
        }
        else if(mPhone.length()<10 || mPhone.length()>10){
            phonenumber.setError("should be 10 numbers long");
            phonenumber.requestFocus();
            return;
        }else if(mEmail.isEmpty()){
            email.setError("email required");
            email.requestFocus();
            return;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()) {
            email.setError("please enter a valid email");
            email.requestFocus();
            return;
        }else if(mCounter.isEmpty()) {
            counterNumber.setError("counter required");
            counterNumber.requestFocus();
            return;
        }
        else {

            clientDAO.setIdNumber(mId);
            clientDAO.setPhoneNumber(mPhone);
            clientDAO.setEmail(mEmail);
            clientDAO.setCounterNumber(mCounter);

            // clientDAO.setDescription(description.getText().toString().trim());

            databaseReference.push().setValue(clientDAO);



            //Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(NavigationDrawerMenu.this, QrCode.class);
            startActivity(intent);
        }



    }


}
