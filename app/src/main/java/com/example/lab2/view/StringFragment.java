package com.example.lab2.view;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.lab2.R;
import com.example.lab2.model.HistoryEntry;
import com.google.android.material.snackbar.Snackbar;


public class StringFragment extends Fragment implements View.OnClickListener {

    TextView res1;
    TextView res2;
    Button go_button;
    Button clear_button;
    EditText b;
    EditText a;
    EditText c;

    public StringFragment(){

    }
    public static Fragment newInstance() { return new StringFragment();}

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_string, container, false);

        res1 = view.findViewById(R.id.res1);
        res2 = view.findViewById(R.id.res2);
        go_button = view.findViewById(R.id.go_button);
        clear_button = view.findViewById(R.id.clear_button);
        b = view.findViewById(R.id.b_edit);
        a = view.findViewById(R.id.a_edit);
        c = view.findViewById(R.id.c_edit);

        go_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                res1.setText("");
                res2.setText("");
                Double x0, y0, a1, b1, c1;

                if(a.getText().toString().isEmpty()){
                    a1 = 0.0;
                } else{
                    a1 = Double.parseDouble(a.getText().toString());
                }
                if(b.getText().toString().isEmpty()){
                    b1 = 0.0;
                } else {
                    b1 = Double.parseDouble(b.getText().toString());
                }
                if(c.getText().toString().isEmpty()){
                    c1 = 0.0;
                } else{
                    c1 = Double.parseDouble(c.getText().toString());
                }

                x0 = -b1/(2*a1);
                y0 = a1 * x0 * x0 + b1 * x0 + c1;

                res1.setText(String.valueOf(String.format("%.2f",x0)));
                res2.setText(String.valueOf(String.format("%.2f",y0)));
                addHistoryItem(a1,b1,c1,x0,y0,"str");

            }
        });

        clear_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DialogToDelete(view);
            }
        });

        return view;

    }

    private void DialogToDelete(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.alerttitle);
        builder.setMessage(R.string.alertmsg);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                res1.setText("");
                res2.setText("");
                a.setText("");
                b.setText("");
                c.setText("");
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Snackbar.make(view,R.string.cancel,Snackbar.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onClick(View view) {

    }

    private void addHistoryItem(double a, double b, double c, double result1, double result2, String type){
        String aString = String.format("%.1f", a);
        String bString = String.format("%.1f", b);
        String cString = String.format("%.1f", c);
        String result1String = String.format("%.2f", result1);
        String result2String = String.format("%.2f", result2);

        MainActivity parent = (MainActivity)getActivity();
        parent.addToHistory(new HistoryEntry(aString, bString, cString, result1String, result2String,type));
    }
}
