package com.example.luxetics;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MyActivity9 extends AppCompatActivity {
    TextView myTextView;
    String my_str, correct_Answer, return_str;
    Integer marks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        marks=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my9);
        myTextView = (TextView)findViewById(R.id.myTextView);
        Intent intent = getIntent();
        my_str = intent.getStringExtra("users_answers");
        correct_Answer = intent.getStringExtra("correct_answers");
        for (int i=0;i<my_str.length();i++){
            if (my_str.charAt(i)==correct_Answer.charAt(i)){
                marks+=1;
            }
        }

        if (marks<5){
            return_str="Thank you for taking the Eriksen Flanker Test! You can do better! Please go for plan A";
        }
        else{
            return_str="Thank you for taking the Eriksen Flanker Test! You did great! Please go for plan B";
        }
        myTextView.setText(return_str);
    }
}