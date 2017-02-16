package edu.washington.yiz24.quizdroid;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {

    // questions
//    ArrayList<Question> math;
//    ArrayList<Question> physics;
    ArrayList<Question> content;

    int round;
    int total;
    int correct;
    List<String> opt;
    String answer;

    Bundle bundle;

    Topic topic;

    public QuestionFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        bundle = getArguments();

        topic = (Topic) bundle.getSerializable("topic");


        String titleName = topic.getTitle();
        round = bundle.getInt("round");
        total = bundle.getInt("total");
        correct = bundle.getInt("correct");
//        this.math = bundle.getParcelableArrayList("math");
//        this.physics = bundle.getParcelableArrayList("physics");
//        this.content = bundle.getParcelableArrayList("content");

        final View view = inflater.inflate(R.layout.fragment_question, container, false);

        TextView q = (TextView) view.findViewById(R.id.question);
        List<Quiz> questions = topic.getQuestions();;
        Quiz current = questions.get(round);

        q.setText(current.getText());

        opt = current.getAnswers();
        answer = current.getCorrect();


        RadioGroup radiogroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        for(int i = 0; i < radiogroup.getChildCount(); i++){
            RadioButton button = (RadioButton) radiogroup.getChildAt(i);
            button.setText(opt.get(i));
        }


        Button submit = (Button) view.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total++;
                RadioGroup group = (RadioGroup) view.findViewById(R.id.radioGroup);
                RadioButton selected = (RadioButton) view.findViewById(group.getCheckedRadioButtonId());
                if(selected != null){
                    String input = (String) selected.getText();
                    if(selected.getText().equals(answer)){
                        correct++;
                    }
                    bundle.putInt("total",total);
                    bundle.putInt("correct", correct);
                    bundle.putString("answer",answer);
                    bundle.putString("input", input);

                    AnswerFragment am = new AnswerFragment();

                    FragmentManager fm = getFragmentManager();
                    am.setArguments(bundle);

                    fm.beginTransaction().replace(R.id.container,am).commit();
                }
            }
        });

        return view;
    }

}
