package com.example.android.newsfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.newsfeed.database.DbAccessObj;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String MYPREFS = "myprefs";
    public static final String NAMEKEY = "namekey";
    public static final String PWDKEY = "pwdkey";

    DbAccessObj dbAccessObj;
    EditText nameEditText, pwdEditText;
    CheckBox rememberMe;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Log.i(TAG, "onCreate");
        nameEditText = findViewById(R.id.editTextName);
        pwdEditText = findViewById(R.id.editTextPwd);
        rememberMe = findViewById(R.id.remember);
        dbAccessObj = new DbAccessObj(this);
        dbAccessObj.openDb();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onpause");
        if(rememberMe.isChecked()) {
            int i = 1;
        }
        else{
            restoreData();
        }
        //saveData();
    }

    /**
     * this method will save data from edittexts into a sharedprefs
     */
    private void saveData() {
        Log.i(TAG, "saveData");

        //get the data from the edittext
        String name = nameEditText.getText().toString();
        String pwd = pwdEditText.getText().toString();
        //create a file names myprefs
        SharedPreferences preferences = getSharedPreferences(MYPREFS, MODE_PRIVATE);
        //open the file
        SharedPreferences.Editor editor = preferences.edit();
        //write to the file
        editor.putString(NAMEKEY, name);
        editor.putString(PWDKEY, pwd);
        //save the file
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onresume");
        //restoreData();
    }

    private void restoreData() {
        Log.i(TAG, "restoreData");

        //open the file
        SharedPreferences preferences = getSharedPreferences(MYPREFS, MODE_PRIVATE);
        //read the file
        String name = preferences.getString(NAMEKEY, "");
        String pwd = preferences.getString(PWDKEY, "");
        //set the data in edittexts        nameEditText.setText(name);
        pwdEditText.setText(pwd);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onstop");

    }

    public void clickHandler(View view) {
        Log.e(TAG, "clickHandler");

        switch (view.getId()) {
            case R.id.buttonlogin:
                getCredentials();
                break;
        }
    }


    private void startHome() {
        Intent hIntent = new Intent(LoginActivity.this, MainActivity.class);
        // hIntent.putExtra("mykey","abdul");

        startActivity(hIntent);
    }


        }


