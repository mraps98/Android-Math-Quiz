package com.example.multiplicationquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int a;
    private int b;
    private int operation;
    private int answer;
    private String signs[] = {"plus","minus","times"};

    private String positive[] = {"Very good!","Excellent!","Nice work!","Keep up the good work!"};
    private String negative[] = {"No. Please try again.","Wrong. Try once more.","Don’t give up!","No. Keep trying."};
    private double correct = 0;
    private double tries = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reset();
    }

    public void checkAnswer(View view){
        EditText editText = (EditText) findViewById(R.id.editText);
        Random random = new Random();
        if((Integer.parseInt(editText.getText().toString())) == answer){
            int n = random.nextInt(4);
            Toast.makeText(getApplicationContext(),positive[n], Toast.LENGTH_LONG).show();
            correct++;
            tries++;
            reset();
        }else {
            int n = random.nextInt(4);
            Toast.makeText(getApplicationContext(), negative[n], Toast.LENGTH_LONG).show();
            tries++;
        }
        updateScore();
    }

    public void reset(){
        EditText editText = (EditText) findViewById(R.id.editText);
        Random random = new Random();
        TextView textView = (TextView) findViewById(R.id.textView);
        a = random.nextInt(9) + 1;
        b = random.nextInt(9) + 1;
        operation = random.nextInt(2);
        switch(operation){
            case 0:
                answer = a + b;
                break;
            case 1:
                answer = a - b;
                break;
            case 2:
                answer = a * b;
                break;
        }
        textView.setText("How much is " + a + " " + signs[operation] + " " + b);
        editText.setText("");
    }

    public void updateScore(){
        TextView textViewCorrect = (TextView) findViewById(R.id.textViewCorrect);
        TextView textViewTries = (TextView) findViewById(R.id.textViewTries);
        TextView textViewPercentage = (TextView) findViewById(R.id.textViewPercentage);

        textViewCorrect.setText(String.format("%.0f",correct));
        textViewTries.setText(String.format("%.0f",tries));
        textViewPercentage.setText(String.format("%.2f",correct/tries*100));
    }
}
