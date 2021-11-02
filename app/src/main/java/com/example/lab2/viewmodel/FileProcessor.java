package com.example.lab2.viewmodel;


import android.content.Context;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileProcessor {
    public static String loadFile(String fileName){
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[0];
        try {
            bytes = new byte[fin.available()];
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fin.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String text = new String(bytes);
        return text;
    }

    public static void writeToFile(String fileName, String msg, Context ctx){
        FileOutputStream fos = null;
        try {
            fos = ctx.openFileOutput(fileName, Context.MODE_APPEND);
            msg = msg+"\n";
            try {
                fos.write(msg.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(fos!=null)
                    fos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }


    }
}

