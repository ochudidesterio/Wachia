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

#Splash screen 

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
  ![Splash](https://github.com/ochudidesterio/GADS-NoteKeeper/blob/master/images/Home.png?raw=true)

