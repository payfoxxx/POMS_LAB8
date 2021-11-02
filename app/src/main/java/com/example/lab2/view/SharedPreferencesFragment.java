package com.example.lab2.view;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.TextView;
import com.example.lab2.R;

public class SharedPreferencesFragment extends PreferenceFragment {
   private TextView mEditText;
    public SharedPreferencesFragment(){

    }

    public static SharedPreferencesFragment newInstance() {
        SharedPreferencesFragment fragment = new SharedPreferencesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

   /* @Override
    public void onResume(){
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        float fSize = Float.parseFloat(prefs.getString(getString(R.string.text_size),"20"));
        mEditText =
        mEditText.setTextSize(fSize);
    }*/
}
