package edu.washington.yiz24.quizdroid;

/**
 * Created by yizhaoyang on 3/2/17.
 */

import java.io.Serializable;
import java.util.List;


public class Topic implements Serializable {
    private String topic;
    private String shortDesc;
    private String longDesc;
    private List<Question> questions;
    private int numQuestions;
    private int imageId;

    public Topic() { }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public String getLongDesc() {
        return shortDesc;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
        numQuestions = questions.size();
    }

    public void addQuestion(Question question) {
        questions.add(question);
        numQuestions++;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public int getNumQuestions() {
        return numQuestions;
    }

    public void setImageId(int id) {
        imageId = id;
    }

    public int getImageId() {
        return R.mipmap.ic_launcher;
    }

}