package com.example.luxetics;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class MyActivity2 extends AppCompatActivity {
    Button Consistent, Inconsistent;
    String my_str, answer, users_answer, prev_answer;
    Integer count, has_answered, random_int1, random_int2;
    TextView my_text;
    Random rand;
    MediaPlayer ring;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my2);

        count = 0;
        users_answer = "";
        answer = "";
        has_answered = 0;
        rand = new Random();
        random_int1 = rand.nextInt(2);
        random_int2 = rand.nextInt(2); // Mid element number
        my_str = newString(random_int1, random_int2);
        prev_answer ="";
        my_text = (TextView) findViewById(R.id.userTextView);
        my_text.setText(my_str);
        Consistent = (Button) findViewById(R.id.Consistent);
        Inconsistent = (Button) findViewById(R.id.Inconsistent);
        ring = MediaPlayer.create(MyActivity2.this, R.raw.mysound);


        new CountDownTimer(8000, 800) {
            @Override
            public void onTick(long millisUntilFinished) {
                count += 1;

                Consistent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        users_answer += '1';
                        has_answered = 1;
                    }
                });

                Inconsistent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        users_answer += '0';
                        has_answered = 1;
                    }
                });

                if (has_answered == 0) {
                    users_answer += ' ';
                }

                prev_answer = my_str;
                random_int1 = rand.nextInt(2);
                random_int2 = rand.nextInt(2); // Mid element number
                my_str = newString(random_int1, random_int2);
                while(prev_answer==my_str){
                    random_int1 = rand.nextInt(2);
                    random_int2 = rand.nextInt(2); // Mid element number
                    my_str = newString(random_int1, random_int2);
                }
                my_text.setText(my_str);

            }

            @Override
            public void onFinish() {
                my_text.setText("Finished");
                myActivity9();
            }

            private void myActivity9() {
                Intent intent = new Intent(MyActivity2.this, MyActivity9.class);
                intent.putExtra("users_answers", users_answer);
                intent.putExtra("correct_answers", answer);
                startActivity(intent);
            }
        }.start();
    }

    private String newString(int random_int1, int random_int2) {
            if (random_int1==random_int2){
                answer+='1';
            }
            else{
                answer+='0';
            }
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
