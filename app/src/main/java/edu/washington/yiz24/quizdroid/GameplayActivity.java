package edu.washington.yiz24.quizdroid;

import android.content.Intent;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class GameplayActivity extends AppCompatActivity {

    String topic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

//        topic = intent.getStringExtra("topic");
//        bundle.putString("topic",topic);
        introFragment intro = new introFragment();
        intro.setArguments(bundle);
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.container, intro).commit();



//        if(topic.equals("Math")){
//            setMathFragment();
//        }else if (topic.equals("Physics")) {
//            physicsFragment();
//        }else{
//            marvelFragment();
//        }

//        TextView title = (TextView) findViewById(R.id.title);
//        title.setText("Math");
//
//        TextView description = (TextView) findViewById(R.id.description);
//        description.setText(math);
//
//        Button begin = (Button) findViewById(R.id.button);
//        begin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(GameplayActivity.this,Questions.class);
//
//                startActivity(i);
//            }
//        });

    }


}
