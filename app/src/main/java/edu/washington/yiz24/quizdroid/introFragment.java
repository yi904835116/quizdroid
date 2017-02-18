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



    Topic topic;
    int question;


    Bundle bundle;

    public introFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        bundle = getArguments();
        topic = (Topic) bundle.getSerializable("topic");
        final String titleName = topic.getTitle();

        question = bundle.getInt("totalRound");

        View view = inflater.inflate(R.layout.fragment_intro, container, false);

        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(titleName);
        TextView des = (TextView) view.findViewById(R.id.description);
//        if(topic.equals("Math")){
//            des.setText(this);
//        }else if(topic.equals("Physics")) {
//            des.setText(physicsD);
//        }else {
//            des.setText(marvelD);
//        }
        des.setText(topic.getDescription());

        TextView q = (TextView) view.findViewById(R.id.number);
        q.setText("Total questions:" + question);

        Button begin = (Button) view.findViewById(R.id.button);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionFragment qf = new QuestionFragment();
                bundle.putInt("round",0);
                bundle.putInt("total",0);
                bundle.putInt("correct",0);

                FragmentManager fm = getFragmentManager();
                qf.setArguments(bundle);

                fm.beginTransaction().replace(R.id.container,qf).commit();

            }
        });


        return view;
    }

}
