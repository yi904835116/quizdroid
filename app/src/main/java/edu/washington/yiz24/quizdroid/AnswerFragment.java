package edu.washington.yiz24.quizdroid;


import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnswerFragment extends Fragment {
    int round;
    int total;
    int correct;
    String input;
    String answer;

    Bundle bundle;

    public AnswerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_answer, container, false);

        bundle = getArguments();
        total = bundle.getInt("total");
        correct = bundle.getInt("correct");
        answer = bundle.getString("answer");
        input = bundle.getString("input");
        round = bundle.getInt("round");
        round++;
        TextView report = (TextView) view.findViewById(R.id.number);
        TextView inputContent = (TextView) view.findViewById(R.id.input);
        TextView answerDisplay = (TextView) view.findViewById(R.id.answerDisplay);
        report.setText("You have " + correct +" out of " + total + " correct!" );
        answerDisplay.setText("Correct answer: " + answer);
        inputContent.setText("You select: " + input);

        Button next = (Button) view.findViewById(R.id.next);
        Button finish = (Button) view.findViewById(R.id.finish);

        if(round < 3){
            finish.setVisibility(view.GONE);
        }else{
            next.setVisibility(view.GONE);
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt("round",round);
                QuestionFragment qf = new QuestionFragment();

                FragmentManager fm = getFragmentManager();
                qf.setArguments(bundle);

                fm.beginTransaction().replace(R.id.container,qf).commit();
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),MainActivity.class);
                startActivity(i);
            }
        });


        return view;
    }






}
