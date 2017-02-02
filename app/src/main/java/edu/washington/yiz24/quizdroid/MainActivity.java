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

public class MainActivity extends AppCompatActivity {
    private String[] list = {"Math","Physics","Marvel Super Heroes"};
    ListView listView;

    Question math1;
    Question math2;
    Question math3;
    Question physics1;
    Question physics2;
    Question physics3;
    Question marvel1;
    Question marvel2;
    Question marvel3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,list);
        listView.setAdapter(adapter);


        final ArrayList<Question> mathList = new ArrayList<Question>();
        math1 = new Question();
        math2 = new Question();
        math3 = new Question();

        physics1 = new Question();
        physics2 = new Question();
        physics3 = new Question();

        marvel1 = new Question();
        marvel2 = new Question();
        marvel3 = new Question();


        math1.addQuestion("2 * 2 + 3 * 4 = ?",new String[]{"25","46","44","40"},"46");
        math2.addQuestion("2 * 4 = ?",new String[]{"8","10","25","4"},"8");
        math3.addQuestion("5 * 5 = ?",new String[]{"25","46","32","21"},"25");

        mathList.add(math1);
        mathList.add(math2);
        mathList.add(math3);



        physics1.addQuestion("An airplane accelerates down a runway at 3.20 m/s2 for 32.8 s until is" +
                        " finally lifts off the ground. Determine the distance traveled before takeoff.",
                new String[] {"345","1720","1620","1540"},
                "1720");
        physics2.addQuestion("A car starts from rest and accelerates uniformly over a time of 5.21 seconds" +
                        " for a distance of 110 m. Determine the acceleration of the car.",new String[] {"8.1","7.1","6.1","8.5"},
                "8.1");
        physics3.addQuestion("Upton Chuck is riding the Giant Drop at Great America. If Upton free falls for 2.60" +
                        " seconds, what will be his final velocity and how far will he fall?",new String[] {"33.1/25.5","32/26","44/25","21/44"},
                "33.1/25.5");


        final ArrayList<Question> pyList = new ArrayList<Question>();
        pyList.add(physics1);
        pyList.add(physics2);
        pyList.add(physics3);


        marvel1.addQuestion("Which hero wear costume that bears an American flag motif, and is armed with a nearly indestructible shield that he throws at foes?",
                new String[]{"caption america","superman","iron man","spider man"},"caption america");
        marvel2.addQuestion("Who is the the director of Iron Man?",new String[]{"Avi Arad","Jon Favreau","Art Marcum","Don Heck"}
                ,"Jon Favreau");
        marvel3.addQuestion("When is the first appearance of spider man?",new String[]{"1962","1971","1964","1954"},"1962");

        final ArrayList<Question> marList = new ArrayList<Question>();
        marList.add(marvel1);
        marList.add(marvel2);
        marList.add(marvel3);






        listView.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedFromList =(String) (listView.getItemAtPosition(position));

                Intent intent = new Intent(MainActivity.this, GameplayActivity.class);
                Bundle bundle = new Bundle();

                if(list[position].equals("Math")){
                    bundle.putParcelableArrayList("content", mathList);
                    bundle.putString("topic","Math");
                    intent.putExtra("topic","Math");
                }else if(list[position].equals("Physics")){
                    bundle.putString("topic","Physics");
                    bundle.putParcelableArrayList("content",pyList);
                    intent.putExtra("topic","Physics");
                }else{
                    bundle.putString("topic","Marvel");
                    bundle.putParcelableArrayList("content",marList);
                    intent.putExtra("topic","Physics");
                }

//                Bundle bundle = new Bundle();
//                bundle.putSerializable("topic", topics.get(position));
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }

}