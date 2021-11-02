package com.example.lab2.view;

import androidx.appcompat.app.AppCompatActivity;



import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.lab2.R;
import com.example.lab2.model.HistoryEntry;
import com.example.lab2.viewmodel.HistoryFacade;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    FrameLayout fragmentSlot;
    private int state;
    private final int NUMBER_STATE = 1;
    private final int STRING_STATE = 2;
    public static final String HISTORY_KEY = "history";
    SharedPreferences sp;
    TextView mEditTextRes1;
    TextView mEditTextRes2;
    TextView mEditText1;
    TextView mEditText2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragmentSlot = findViewById(R.id.fragment_slot);
        Button switchButton = findViewById(R.id.switch_fragment_button);

        if(fragmentSlot!=null){
            setNumberFragment();
            switchButton.setOnClickListener(view -> switchFragment());
        }

        Button nextActivityButton = findViewById(R.id.next_activity_button);
        nextActivityButton.setOnClickListener(view -> gotoNextActivity());


    }

    @Override
    protected void onResume(){

        sp = PreferenceManager.getDefaultSharedPreferences(this);
        float fSize = Float.parseFloat(sp.getString("text_size","21"));
        mEditTextRes1 = findViewById(R.id.textres1);
        mEditTextRes2 = findViewById(R.id.textres2);
        mEditText1 = findViewById(R.id.res1);
        mEditText2 = findViewById(R.id.res2);
        mEditTextRes1.setTextSize(fSize);
        mEditTextRes2.setTextSize(fSize);
        mEditText1.setTextSize(fSize);
        mEditText2.setTextSize(fSize);

        mEditTextRes2.setTextColor(Color.GREEN);

        String regular = sp.getString("pref_style","");
        String color = sp.getString("color_set","");
        int color_set = Color.WHITE;
        if(color.contains(getString(R.string.white_color))){
            color_set = Color.WHITE;
        }
        if(color.contains(getString(R.string.red_color))){
            color_set = Color.RED;
        }
        if(color.contains(getString(R.string.green_color))){
            color_set = Color.GREEN;
        }
        if(color.contains(getString(R.string.blue_color))){
            color_set = Color.BLUE;
        }

        int typeface = Typeface.NORMAL;


        if(regular.contains(getString(R.string.bold_style))) {
            typeface += Typeface.BOLD;
        }

        if(regular.contains(getString(R.string.italic_style))) {
            typeface += Typeface.ITALIC;
        }

        mEditTextRes1.setTypeface(null, typeface);
        mEditTextRes2.setTypeface(null, typeface);
        mEditTextRes1.setTextColor(color_set);
        mEditTextRes2.setTextColor(color_set);
        mEditText1.setTextColor(color_set);
        mEditText2.setTextColor(color_set);
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot, fragment);
        fragmentTransaction.commit();
    }



    private void gotoNextActivity(){
        Intent intent = new Intent(this,NextActivity.class);
        startActivity(intent);
    }



    private void setNumberFragment(){
        setFragment(NumberFragment.newInstance());
        state = NUMBER_STATE;
    }

    private void setStringFragment(){
        setFragment(StringFragment.newInstance());
        state = STRING_STATE;
    }

    private void switchFragment(){
        if(state == NUMBER_STATE){
            setStringFragment();
        } else if(state == STRING_STATE){
            setNumberFragment();
        }
    }

    public void addToHistory(HistoryEntry newItem){
        HistoryFacade.addItem(getBaseContext(), newItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.open_history_item:
                intent = new Intent(this, HistoryListActivity.class);
                intent.putParcelableArrayListExtra(HISTORY_KEY,new ArrayList<>(HistoryFacade.getAllAsList(getBaseContext())));
                startActivity(intent);
                break;
            case R.id.open_service_item:
                intent = new Intent(this, ServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.open_browser_item:
                intent = new Intent(this,BrowserActivity.class);
                startActivity(intent);
                break;
            case R.id.open_file_show_item:
                intent = new Intent(this,FileActivity.class);
                startActivity(intent);
                break;
            case R.id.open_db_activity_item:
                intent = new Intent(this,DataBaseActivity.class);
                startActivity(intent);
                break;
            case R.id.open_pref_shared_item:
                intent = new Intent(this,SharedPreferencesActivity.class);
                startActivity(intent);
            case R.id.open_graphic_activity_item:
                intent = new Intent(this,GraphicActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}