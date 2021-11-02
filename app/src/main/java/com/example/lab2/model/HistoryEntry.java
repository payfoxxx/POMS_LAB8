package com.example.lab2.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


import java.lang.reflect.Type;

@Entity(tableName = "history")
public class HistoryEntry implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "a")
    private String a;
    @ColumnInfo(name = "b")
    private String b;
    @ColumnInfo(name = "c")
    private String c;
    @ColumnInfo(name = "result1")
    private String result1;
    @ColumnInfo(name = "result2")
    private String result2;
    @ColumnInfo(name = "type")
    private String type;

    public String getA() {return a;}
    public String getB() {return b;}
    public String getC() {return c;}
    public String getResult1() {return result1;}
    public String getResult2() {return result2;}
    public String getType() {return type;}

    public HistoryEntry(String a, String b, String c, String result1, String result2, String type){
        this.a = a;
        this.b = b;
        this.c = c;
        this.result1 = result1;
        this.result2 = result2;
        this.type = type;
    }

    public static final Parcelable.Creator<HistoryEntry> CREATOR = new Parcelable.Creator<HistoryEntry>(){
        @Override
        public HistoryEntry createFromParcel(Parcel in) { return new HistoryEntry(in);}

        @Override
        public HistoryEntry[] newArray(int size) { return new HistoryEntry[size];}
    };

    public String getTextRepresentation(){
        String textRepresentation;

        if(type.equals("num")){
            textRepresentation = String.format("Quadratic equation %1sx^2 + %2sx + %3s Result 1: %4s, Result 2: %5s",
                    a,b,c,result1,result2);
        } else{
            textRepresentation = String.format("Quadratic equation %1sx^2 + %2sx + %3s has top of the parabola in x0: %4s, y0: %5s",
                    a,b,c,result1,result2);
        }
        return textRepresentation;
    }

    @Override
    public int describeContents() { return 0;}

    protected HistoryEntry(Parcel in){
        a = in.readString();
        b = in.readString();
        c = in.readString();
        result1 = in.readString();
        result2 = in.readString();
        type = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(a);
        dest.writeString(b);
        dest.writeString(c);
        dest.writeString(result1);
        dest.writeString(result2);
        dest.writeString(type);
    }
}