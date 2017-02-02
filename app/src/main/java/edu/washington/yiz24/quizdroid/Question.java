package edu.washington.yiz24.quizdroid;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yizhaoyang on 01/02/2017.
 */

public class Question  implements Parcelable {
    private String question;
    private String[] option;
    private String correct;

    public Question(){
        option = new String[4];
    }

    public void addQuestion(String question, String[] option, String correct){
        this.question = question;
        this.option = option;
        this.correct = correct;
    }

    public String getQuestion(){
        return  this.question;
    }

    public String getCorrect(){
        return this.correct;
    }

    public String[] getOption() {
        return this.option;
    }
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(question);
        out.writeStringArray(option);
        out.writeString(correct);
    }

    public static final Parcelable.Creator<Question> CREATOR
            = new Parcelable.Creator<Question>() {
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    private Question(Parcel in) {
        question = in.readString();
        option = new String[4];
        in.readStringArray(option);
        correct = in.readString();
    }
}
