package com.example.lab2.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPreferencesActivity extends PreferenceActivity {
    TextView mEditText;

    /*@Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SharedPreferencesFragment())
                .commit();
    } */

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SharedPreferencesFragment())
                .commit();
        //addPreferencesFromResource(R.xml.settings);
    }

    /*@Override
    public void onResume(){
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        float fSize = Float.parseFloat(prefs.getString(getString(R.string.text_size),"20"));
        mEditText = findViewById(R.id.res1);
                mEditText.setTextSize(fSize);
    }*/


}
