package com.example.lab2.viewmodel;

import static java.lang.Thread.sleep;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.lab2.view.ServiceActivity;

public class MyService extends Service {
    MyTask task;

    public void onCreate() { super.onCreate();}

    public int onStartCommand(Intent intent, int flags, int startId){
        PendingIntent pendingIntent = intent.getParcelableExtra(ServiceActivity.PENDING_INTENT_KEY);
        task = new MyTask(pendingIntent);
        startWork();
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy(){
        super.onDestroy();
        task.stop();
    }

    public IBinder onBind(Intent intent) {return null;}

    void startWork(){
        Thread thread = new Thread(task, "T1");
        thread.start();
    }


    private class MyTask implements Runnable{
        private boolean exit;
        private PendingIntent pendingIntent;

        MyTask(PendingIntent pendingIntent) {this.pendingIntent = pendingIntent;}

        @Override
        public void run(){
            talkToCreator(new Intent(), ServiceActivity.COUNTER_START);

            exit = false;
            String a = "abcdefghijklmnopqrstuvwxyz";
            char a_char;
            final int much = 26;
            for(int i = 0; i<much && !exit; i++){
                a_char = a.charAt(i);
                talkToCreator(new Intent().putExtra(ServiceActivity.COUNTER_ANSWER_KEY, a_char), ServiceActivity.COUNTER_ANSWER);

                try{
                    sleep(3000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                    break;
                }
            }
            talkToCreator(new Intent(), ServiceActivity.COUNTER_FINISH);
        }

        void stop() {exit=true;}

        void talkToCreator(Intent intent, int code){
            try{
                pendingIntent.send(MyService.this, code, intent);
            }
            catch (PendingIntent.CanceledException e){
                e.printStackTrace();
            }
        }
    }
}