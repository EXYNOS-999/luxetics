package com.example.luxetics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MyActivity3 extends AppCompatActivity {
    TextView result;
    Integer correct;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my3);

        //Bundle b = getIntent().getExtras();
        //String user_answer = b.getString("user_answer");

        /*result = (TextView)findViewById(R.id.results);
        intent = getIntent();
        String user_answer = intent.getStringExtra("users_answers");
        String correct_answer = intent.getStringExtra("correct_answers");*/

        result.setText("Trial Answer!");
    }
}
