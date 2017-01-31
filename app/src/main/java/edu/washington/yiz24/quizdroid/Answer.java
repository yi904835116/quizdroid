package edu.washington.yiz24.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Answer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        int total = bundle.getInt("total");
        int correct = bundle.getInt("correct");
        String answer = bundle.getString("answer");
        String input = bundle.getString("input");

        TextView report = (TextView) findViewById(R.id.report);
        TextView answerDisplay = (TextView) findViewById(R.id.answerDisplay);

        report.setText("You have " + correct +" out of " + total + " correct." );
        answerDisplay.setText("You select " + input + " and the correct answer is " + answer);


        Button finish = (Button) findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(Answer.this,MainActivity.class);
                startActivity(newIntent);
            }
        });



    }
}
