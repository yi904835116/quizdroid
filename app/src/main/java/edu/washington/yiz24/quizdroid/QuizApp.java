package edu.washington.yiz24.quizdroid;

import android.app.Application;         //???
import android.util.Log;

import java.io.Serializable;
import java.util.*;
import java.io.*;


/**
 * Created by yizhaoyang on 15/02/2017.
 */

public class QuizApp extends Application {

//    private TopicRepository repo;
    private MemoryRepository repo;
    private static QuizApp instance;



    public TopicRepository getTopicRepository() {
        return this.repo;
    }

    public QuizApp() {

    }


    public static QuizApp getInstance(){
        if(instance == null) instance = new QuizApp();
        return instance;
    }

    @Override
    public void onCreate() {
        Log.i("QuizApp", "onCreate() called");
        super.onCreate();

//        repo = repo.getInstance();

        repo = new MemoryRepository();
    }

    public List<Topic> getAllTopics(){
        return repo.getAllTopics();
    }


}

class Topic implements Serializable {
    private String title;
    private String description;
    private List<Quiz> questions;

    public Topic(String title, String description, List<Quiz> questions) {
        this.title = title;
        this.description = description;
        this.questions = questions;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Quiz> getQuestions() {
        return questions;
    }
}

class Quiz implements Serializable{
    private String text;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private List<String> answers;
    private String correctAnswer;

    public Quiz(String text, String answer1, String answer2, String answer3, String answer4,
                String correctAnswer) {
        this.text = text;
        answers = new ArrayList<>();
        this.answers.add(answer1);
        this.answers.add(answer2);
        this.answers.add(answer3);
        this.answers.add(answer4);
        this.correctAnswer = correctAnswer;
    }

    public String getText() {
        return text;
    }

    public List<String> getAnswers() {
        return this.answers;
    }

    public String getCorrect() {
        return correctAnswer;
    }
}


interface TopicRepository {
    public List<Topic> getAllTopics();
    public Topic getTopicByTitle(String title);
}

class MemoryRepository implements TopicRepository{

    private List<Topic> topics;
//    private static MemoryRepository instance = null;

    public MemoryRepository() {
        topics = new ArrayList<Topic>();

        List<Quiz> mathQuestions = new ArrayList<>();
        List<Quiz> physicsQuestions = new ArrayList<>();
        List<Quiz> marvelQuestions = new ArrayList<>();

        mathQuestions.add(new Quiz("What is 1 + 1?", "2", "11", "13", "4", "1"));
        mathQuestions.add(new Quiz("What is 1 * 1?", "1", "3", "11", "111", "1"));
        physicsQuestions.add(new Quiz("What is the correct formula of acceleration?",
                "a = (v - v0)^2 * t", "a = (v-v0)t", "a = 1/2 * t * v" , "a = x * t", "a = (v - v0)^2 * t"));
        physicsQuestions.add(new Quiz("What is formula of force?", "mass", "mass * acceleration",
                "speed + time", "acceleration * speed", "mass * acceleration"));
        marvelQuestions.add(new Quiz("What is the name of Iron man",
                "Tom Stark", "Tony Stark", "Stan Lee",
                "Don Heck", "Tony Stark"));
        marvelQuestions.add(new Quiz(" Who is the writer of Doctor Strange",
                "Steve Ditko", "Steve Ditko ", "Stan Lee",
                "Tom Palmer", "Stan Lee"));


        Topic math = new Topic("Math","This section test your mathematics ability", mathQuestions);
        Topic physics = new Topic("Physics", "This section test your mathematics ability",
                physicsQuestions);
        Topic marvel = new Topic("Marvel Super Heroes",
                "Find out your knowledge about Marvel", marvelQuestions);

        topics.add(math);
        topics.add(physics);
        topics.add(marvel);
    }

    public List<Topic> getAllTopics() {
        return topics;
    }

    public Topic getTopicByTitle(String title) {
        for (Topic topic : topics) {
            if (topic.getTitle().equals(title)) {
                return topic;
            }
        }
        return null;
    }
}