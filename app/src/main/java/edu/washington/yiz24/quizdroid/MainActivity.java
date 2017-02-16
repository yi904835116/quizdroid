package edu.washington.yiz24.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String[] list = {"Math","Physics","Marvel Super Heroes"};
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,list);
        listView.setAdapter(adapter);


        QuizApp app = (QuizApp) getApplication();

        final List<Topic> topics = app.getAllTopics();


        String[] quizTopics = new String[topics.size()];
        for (int i = 0; i < topics.size(); i++) {
            quizTopics[i] = topics.get(i).getTitle();
        }




        listView.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedFromList =(String) (listView.getItemAtPosition(position));

                Intent intent = new Intent(MainActivity.this, GameplayActivity.class);
                Bundle bundle = new Bundle();

                bundle.putSerializable("topic", topics.get(position));

                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }

}