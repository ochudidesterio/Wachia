# Wachia
Wachia is an android app that is meant for managing storage facilities and Wachia app aims to improve security aspects as it uses Quick Response Code. As customers arrive with their luggage, their details
are captured and a quick response code is generated and sent to their respective emails as compared to earlier days where by the customers are given cards. Wachia digitalizes the process and uses firebase 
real time database for storage.

## Prerequisites

- Android studio with minimum API level 21 installed.
- Knowlegde about firebaseAuth and firebase Storage.
- Zxing library (You should know how to use it)
- Threads


# Project Flow

## Splash screen 

This is a launcher activity that runs on a thread.The thread sleeps for 3000 milliseconds after which the main activity of the app is launched.

``` Java

Private class LogoLauncher extends Thread{
        public void run(){
            try {
                sleep(1000*SLEEP_TIMER);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
            SplashScreen.this.finish();
        }
    }
  ```
  ![Splash](https://github.com/ochudidesterio/Wachia/blob/master/wachia%20images/Splash%20Screen.png?raw=true)
  
  ## Sign In and Sign Up Activities
  
  This activities allows the user, in this case the owner of a storage facility, to sign in to his account and if he doesnt have one, he can sign up for an account. This           activities use firebaseAuth to sign in a user or creating a new user
  
  ``` Java
          firebaseAuth.signInWithEmailAndPassword(userEmail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) { 
            //task after successfuly login
            }});
  ```
  <br>
  ``` Java
             firebaseAuth.createUserWithEmailAndPassword(userEmail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
            //task after successful sign in
            }});
  ```
  
   ![signup](https://github.com/ochudidesterio/Wachia/blob/master/wachia%20images/SignUp%20Options.png?raw=true)
    
    
  ## Navigation Menu
  
  The navigation menu has three list items namely: Client, Scan and logout. The client item listener fires the activity to capture clients details, the scan item listener 
  launches the scan fargment and logout exits the application.
  
   ![Splash](https://github.com/ochudidesterio/Wachia/blob/master/wachia%20images/navigation%20menu.png?raw=true)
    
  ## Client Information
  
  This activities function to capture client details,send them to firebase storage and generate a quick response code according to client the information captured. the Quick       response code is generated using the zxing libray. To use this library, open the build.gradle file at the app level and add the following dependencies
  
  ``` Java
        implementation 'com.journeyapps:zxing-android-embedded:3.4.0'

        implementation 'com.google.zxing:core:3.3.2'
  ```
   ![clientdetails](https://github.com/ochudidesterio/Wachia/blob/master/wachia%20images/Customer%20details.png?raw=true)
   
   ## Sharing the QR Code
   
   On firing the listener to the send button, the email share intent is triggured and the generated quick response code is passed via Intent extras as follows:
   
   ``` Java
           Intent sendmail = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
           sendmail.setType("image/*");

            sendmail.putExtra(Intent.EXTRA_EMAIL, recipients);
            sendmail.putExtra(Intent.EXTRA_SUBJECT,"Your QR code");
            sendmail.putExtra(Intent.EXTRA_STREAM,uri);
            sendmail.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(sendmail, "Choose an email client from..."));
            
   ```
   
   ![shareemail](https://github.com/ochudidesterio/Wachia/blob/master/wachia%20images/Send%20Email.png?raw=true)
   
   
   ## QR Code Scanning
   
   This activities function to start QR code scanner from the zxing library to scan the result.
   
   ![shareemail](https://github.com/ochudidesterio/Wachia/blob/master/wachia%20images/Scanning.png?raw=true)

   
   
    


  
  
    
  

  


  


