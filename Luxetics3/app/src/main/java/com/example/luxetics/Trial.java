package com.example.luxetics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class Trial extends AppCompatActivity {
    Button Consistent;
    Button Inconsistent;
    String my_str;
    Integer count;
    TextView my_text;
    Random rand= new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial);
        count=0;
        final int random_int1 = rand.nextInt(2);
        final int random_int2 = rand.nextInt(2); // Mid element number
        //newString is a function that generates a new string of < or >
        my_str=newString(random_int1,random_int2);
        my_text = (TextView)findViewById(R.id.mytextView);
        my_text.setText(my_str);

        Consistent=(Button)findViewById(R.id.Consistent);
        Consistent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count+=1;
                if (count<10) {
                    final int random_int1 = rand.nextInt(2);
                    final int random_int2 = rand.nextInt(2); // Mid element number
                    my_str = newString(random_int1, random_int2);
                    my_text.setText(my_str);
                }
                if (count==10){
                    my_str="Trial is over!";
                    my_text.setText(my_str);
                    count=0;
                    my_str="";
                    openInstructions();
                }
            }
        });

        Inconsistent=(Button)findViewById(R.id.Inconsistent);
        Inconsistent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count+=1;
                if (count<10) {
                    final int random_int1 = rand.nextInt(2);
                    final int random_int2 = rand.nextInt(2); // Mid element number
                    my_str = newString(random_int1, random_int2);
                    my_text.setText(my_str);
                }
                if (count==10){
                    my_str="Trial is over!";
                    my_text.setText(my_str);
                    count=0;
                    my_str="";
                    openInstructions();
                }
            }
        });
    }

    private void openInstructions() {
        Intent intent = new Intent(Trial.this, Instructions.class);
        startActivity(intent);
    }

    // Function that returns a new string value as per the random numbers set
    public String newString(int random_int1, int random_int2){
        String mytempstr="";
        Random rand= new Random();
        // Setting the for loop for the String to be printed out
        for (int i=1; i<6 ; i++){
            if (i==3) {
                if (random_int2==0){
                    mytempstr+="<";
                }
                else{
                    mytempstr+=">";
                }
            }
            else{
                if (random_int1==0) {
                    mytempstr += "<";
                }
                else {
                    mytempstr += ">";
                }
            }
        }
        return mytempstr;
    }
}
