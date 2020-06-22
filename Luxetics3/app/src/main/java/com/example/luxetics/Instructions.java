package com.example.luxetics;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Instructions extends AppCompatActivity {
    Button trial;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        next=(Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        trial=(Button)findViewById(R.id.trial);
        trial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTrial();
            }
        });
    }

    public void openTrial(){
        Intent intent = new Intent(Instructions.this, Trial.class);
        startActivity(intent);
    }

    public void openActivity2(){
        Intent intent = new Intent(Instructions.this, MyActivity2.class);
        startActivity(intent);
    }
}