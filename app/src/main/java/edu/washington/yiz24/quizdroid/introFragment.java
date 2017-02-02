package edu.washington.yiz24.quizdroid;


import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class introFragment extends Fragment {



    String topic;
    int question;


    ArrayList<Question> content;

    String mathD;
    String physicsD;
    String marvelD;

    Bundle bundle;

    public introFragment() {
        // Required empty public constructor

        Question math;
        Question physics;
        Question marvel;
        content = new ArrayList<Question>();
        mathD = "The math quiz test is going to test you some basic questions about calculation";
        physicsD = "The pythics test text is going to test you some basic questions about Mechanics Physics";
        marvelD = "the marvel test is going to test the basic knowledge about Marvel Herios";
        question = 3;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        bundle = getArguments();
        topic = bundle.getString("topic");

        content = bundle.getParcelableArrayList("content");


        View view = inflater.inflate(R.layout.fragment_intro, container, false);

        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(topic);
        TextView des = (TextView) view.findViewById(R.id.description);
        if(topic.equals("Math")){
            des.setText(this.mathD);
        }else if(topic.equals("Physics")) {
            des.setText(physicsD);
        }else {
            des.setText(marvelD);
        }

        TextView q = (TextView) view.findViewById(R.id.number);
        q.setText("Total questions:" + question);

        Button begin = (Button) view.findViewById(R.id.button);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionFragment qf = new QuestionFragment();
                bundle.putString("topic",topic);
                bundle.putInt("round",0);
                bundle.putInt("total",0);


                FragmentManager fm = getFragmentManager();
                qf.setArguments(bundle);

                fm.beginTransaction().replace(R.id.container,qf).commit();

            }
        });


        return view;
    }

}
