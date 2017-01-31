package edu.washington.yiz24.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Questions extends AppCompatActivity {
    private int total;
    private int correct;
    private String answer;
    private String input;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Intent intent = getIntent();
        Button submit = (Button) findViewById(R.id.submit);
        total++;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup);
                RadioButton selected = (RadioButton) findViewById(group.getCheckedRadioButtonId());
                if(selected != null){
                    input = (String) selected.getText();
                    answer = "16";
                    if(selected.getText().equals("16")){
                        correct++;
                    }

                    Bundle bundle = new Bundle();
                    bundle.putInt("total",total);
                    bundle.putInt("correct", correct);
                    bundle.putString("answer",answer);
                    bundle.putString("input", input);
                    Intent newIntent = new Intent(Questions.this,Answer.class);
                    newIntent.putExtra("bundle",bundle);

                    startActivity(newIntent);

                }
            }
        });

//        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                RadioButton selected = (RadioButton) findViewById(checkedId);
//
//            }
//        });

    }
}
