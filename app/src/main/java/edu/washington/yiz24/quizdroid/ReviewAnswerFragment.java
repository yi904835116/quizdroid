package edu.washington.yiz24.quizdroid;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ReviewAnswerFragment extends Fragment {
    public ReviewAnswerFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // get View of Fragment from fragment_main.xml
        View rootView = inflater.inflate(R.layout.fragment_review_answer, container, false);

        final Intent launchedMe = getActivity().getIntent();

        final Topic topic = (Topic) launchedMe.getSerializableExtra("topic");
        int questionNum = launchedMe.getIntExtra("questionNum", 0);
        int numCorrect = launchedMe.getIntExtra("numCorrect", 0);
        String correctAnswer = launchedMe.getStringExtra("correctAnswer");
        boolean isCorrect = launchedMe.getBooleanExtra("isCorrect", false);


        String feedback;
        if (isCorrect) {
            feedback = "You got it right!";
        } else {
            feedback = "Better luck next time. \nThe correct answer was: " + correctAnswer;
        }

        TextView fbck = (TextView) rootView.findViewById(R.id.fbck);
        fbck.setText(feedback);

        String displayText = "You have answered " + numCorrect + " out of " +
                questionNum + " questions correctly";

        TextView message = (TextView) rootView.findViewById(R.id.message);
        message.setText(displayText);

        Button next = (Button) rootView.findViewById(R.id.next);
        if (launchedMe.getBooleanExtra("quizFinished", false)) {
            next.setText("Finish");
        } else {
            next.setText("Next");
        }
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean quizOver = launchedMe.getBooleanExtra("quizFinished", false);
                if(quizOver) {
                    Intent nextActivity = new Intent(getActivity(), MainActivity.class);
                    startActivity(nextActivity);
                    getActivity().finish();
                } else {
                    int questionNum = launchedMe.getIntExtra("questionNum", 0);
                    int numCorrect = launchedMe.getIntExtra("numCorrect", 0);
                    launchedMe.putExtra("topic", topic);
                    launchedMe.putExtra("questionNum", questionNum);
                    launchedMe.putExtra("numCorrect", numCorrect);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, new ViewQuestionFragment()).commit();
                }
            }
        });

        return rootView;
    }
}

