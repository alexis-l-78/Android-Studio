package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.widget.TextView;
public class Activity2 extends AppCompatActivity {


    boolean threadIsRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_2);

        Log.e("DEVE0304", "Activity2:onCreate()");

        Intent myIntent = getIntent(); // gets the previously created intent
        String firstKeyName = myIntent.getStringExtra("Nom de l'utilisateur");
        Log.e("DEVE0304", "Activity2:onCreate() : Intent key value : " + firstKeyName);

        threadIsRunning = false;

    }

    public void effacertexte(View view){

        try{
            TextView monText = (EditText)findViewById(R.id.text2);

            if(monText.getVisibility() == View.VISIBLE){
                monText.setVisibility(View.INVISIBLE);
            }else{
                monText.setVisibility(View.VISIBLE);
            }

        }catch(Exception e){
            System.out.println("Error " + e.getMessage());
        }


    }

    public void runThread(View view){

        Log.e("DEVE0304", "Button clicked : runThread");

        threadIsRunning = true;

        new Thread(new Runnable() {
            public void run() {

                while (threadIsRunning)
                {
                    // a potentially time consuming task
                    Log.e("DEVE0304", "Thread 1 : click");
                    SystemClock.sleep(2000);
                }
            }
        }).start();

    };


    public void stopThread(View view){

        Log.e("DEVE0304", "Button clicked : stopThread");

        threadIsRunning = false;
    };




    public void saveData(View view){

        Log.e("DEVE0304", "Button clicked : saveData");

        EditText myDataField = findViewById(R.id.myDataField);


        String value = myDataField.getText().toString();
        int finalValue = Integer.parseInt(value);

        Log.e("DEVE0304", "Data read from field " + finalValue);

        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = myPreferences.edit();
        editor.putInt("storedNumber", finalValue); // value to store
        editor.apply();

    };


    public void loadData(View view){

        Log.e("DEVE0304", "Button clicked : loadData");

        SharedPreferences myPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        int retrievedInt = myPreferences.getInt("storedNumber", -1);

        Log.e("DEVE0304", "Button clicked : loadData " + retrievedInt);


        EditText myDataField = findViewById(R.id.myDataField);
        myDataField.setText(Integer.toString(retrievedInt));
        //myDataField.setText(Integer.toString(retrievedInt));

        Toast.makeText(getApplicationContext(), "Retrieved value : " + Integer.toString(retrievedInt), Toast.LENGTH_LONG).show();
    };


}