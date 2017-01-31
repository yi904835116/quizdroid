package edu.washington.yiz24.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GameplayActivity extends AppCompatActivity {

    private String math = "The math quiz test is going to test you some basic questions about calculation";
    private String pythics = "The pythics test text is going to test you some basic questions about Mechanics Physics";
    private String marvel = "the marvel test is going to test the basic knowledge about Marvel Herios";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        Intent intent = getIntent();

        TextView title = (TextView) findViewById(R.id.title);
        title.setText("Math");

        TextView description = (TextView) findViewById(R.id.description);
        description.setText(math);

        Button begin = (Button) findViewById(R.id.button);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GameplayActivity.this,Questions.class);
                startActivity(i);
            }
        });


    }




}
