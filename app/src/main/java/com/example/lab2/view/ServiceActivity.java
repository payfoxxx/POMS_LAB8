package com.example.lab2.view;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab2.R;
import com.example.lab2.viewmodel.FileProcessor;
import com.example.lab2.viewmodel.MyService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class ServiceActivity extends AppCompatActivity {

    public static final String PENDING_INTENT_KEY = "pending_intent";
    public static final String COUNTER_ANSWER_KEY = "pending_intent";

    private static final int COUNTER_SERVICE = 1;

    public static final int COUNTER_START = 1;
    public static final int COUNTER_ANSWER = 2;
    public static final int COUNTER_FINISH = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        Button start = findViewById(R.id.start_service_btn);
        Button stop = findViewById(R.id.stop_service_btn);
        start.setOnClickListener(v->startService());
        stop.setOnClickListener(v->stopService());
    }

    public void startService(){
        deleteFile("file.txt");
        PendingIntent pendingIntent = createPendingResult(COUNTER_SERVICE, new Intent(), 0);

        Intent intent = new Intent(this, MyService.class);
        intent.putExtra(PENDING_INTENT_KEY,pendingIntent);

        startService(intent);
    }

    public void stopService(){
        Intent intent = new Intent(this,MyService.class);
        stopService(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if(requestCode == COUNTER_SERVICE){
            switch (resultCode){
                case COUNTER_START:
                    Toast.makeText(this,getResources().getString(R.string.service_started), Toast.LENGTH_SHORT).show();
                    break;
                case COUNTER_ANSWER:
                    char counter = data.getCharExtra(COUNTER_ANSWER_KEY,'a');
                    Toast.makeText(this,String.valueOf(counter),Toast.LENGTH_SHORT).show();
                    writeToFile(String.valueOf(counter));
                    break;
                case COUNTER_FINISH:
                    Toast.makeText(this,getResources().getString(R.string.service_stopped), Toast.LENGTH_SHORT).show();
                    break;

            }
        }
        super.onActivityResult(requestCode,resultCode,data);
    }

    private void writeToFile(String msg){
        FileProcessor.writeToFile("file.txt",msg,this);
       /* File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(downloadsDir, "file.txt");
        try {
            FileWriter out = new FileWriter(myFile, true);
            out.write(msg+"\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("file.txt", MODE_APPEND);
            msg = msg+"\n";
            fos.write(msg.getBytes());
        } catch (IOException ex){
            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
        finally {
            try {
                if(fos!=null)
                    fos.close();
            } catch (IOException ex){
                Toast.makeText(this,ex.getMessage(),Toast.LENGTH_SHORT).show();
            }
        } */
    }
}
