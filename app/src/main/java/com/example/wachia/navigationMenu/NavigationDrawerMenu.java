package com.example.wachia.navigationMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wachia.Activity.QrCode;
import com.example.wachia.Entity.ClientDAO;
import com.example.wachia.R;
import com.example.wachia.entityService.ClientDetails;
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
   public static EditText idnumber,phonenumber,email,description;
   public static DatabaseReference databaseReference;
   public static ClientDAO clientDAO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawermenu);
        actionBarDrawerToggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);

        actionBarDrawerToggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment_container, new ClientFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_client);
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
        }
 drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onScanButtonselected() {

        final Activity activity = this;

        IntentIntegrator intentIntegrator = new IntentIntegrator(activity);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        intentIntegrator.setPrompt("Scanning");
        intentIntegrator.setCameraId(0);
        intentIntegrator.setBeepEnabled(false);
        intentIntegrator.setBarcodeImageEnabled(false);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            if (result.getContents() == null) {
                Toast.makeText(this,"You cancelled the scanning",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this,result.getContents(),Toast.LENGTH_LONG).show();
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onSubmitButtonSelected() {
        idnumber =(EditText)findViewById(R.id.IDtextview);
        phonenumber =(EditText)findViewById(R.id.phoneNumberTextView);
        email =(EditText)findViewById(R.id.emailtextView);
        description =(EditText)findViewById(R.id.DescriptionView);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Client");

        clientDAO = new ClientDAO();
        int idNo= Integer.parseInt(idnumber.getText().toString().trim());
        int phone = Integer.parseInt(phonenumber.getText().toString().trim());

        clientDAO.setIdNumber(idNo);
        clientDAO.setPhoneNumber(phone);
        clientDAO.setEmail(email.getText().toString().trim());
        clientDAO.setDescription(description.getText().toString().trim());

        databaseReference.push().setValue(clientDAO);

//        idnumber.setText(null);
//        phonenumber.setText(null);
//        email.setText(null);
//        description.setText(null);

        Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(NavigationDrawerMenu.this, QrCode.class);
        startActivity(intent);

        idnumber.setText(null);
        phonenumber.setText(null);
        email.setText(null);
        description.setText(null);

    }
}
