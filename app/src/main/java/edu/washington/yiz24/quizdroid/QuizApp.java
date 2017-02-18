package edu.washington.yiz24.quizdroid;

import android.app.Application;         //???
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.*;
import java.io.*;


/**
 * Created by yizhaoyang on 15/02/2017.
 */

public class QuizApp extends Application {

    //    private TopicRepository repo;
    private TopicRepository repo;
    private static QuizApp instance;

    public static QuizApp getInstance() {
        if (instance == null) instance = new QuizApp();
        return instance;
    }

    @Override
    public void onCreate() {
        Log.i("QuizApp", "onCreate() called");
        super.onCreate();

        try { // gets the JSON and creates a new TopicRepository
            InputStream inputStream = getAssets().open("questions.json");
            if (inputStream == null) Log.i("QuizApp","inputStream == null");

            String json = readJSONFile(inputStream);
            repo = new JSONRepository(json);
        } catch (IOException e) {
            e.printStackTrace();
            repo = new MemoryRepository(); // if JSON fails, backup to use default hard-code repo
        }

    }

    public List<Topic> getAllTopics() {
        return repo.getAllTopics();
    }

    // reads given InputStream of JSON file and returns it in string format

    public String readJSONFile(InputStream fis) throws IOException {
        int size = fis.available();
        byte[] buffer = new byte[size];
        fis.read(buffer);
        fis.close();
        return new String(buffer, "UTF-8");
    }
}

    class JSONRepository implements TopicRepository {
        List<Topic> topics;

        public JSONRepository(String json) {
            topics = new ArrayList<Topic>();

            try {
                JSONArray topics= new JSONArray(json);

                // looks through each topic and parses the JSON to Quiz/Topic objects
                for (int i = 0; i < topics.length(); i++) {
                    JSONObject obj = topics.getJSONObject(i);
                    String title = obj.getString("title");
                    String desc = obj.getString("desc");

                    List<Quiz> topicQuestions = new ArrayList<Quiz>();
                    JSONArray questions = obj.getJSONArray("questions");

                    // looks through each question in a topic and adds it to its list of topic questions
                    for (int j = 0; j < questions.length(); j++) {
                        JSONObject eachQuestion = questions.getJSONObject(j);
                        String question = eachQuestion .getString("text");
                        int answer = eachQuestion .getInt("answer");
                        JSONArray answers = (JSONArray) eachQuestion .get("answers");


                        String question1 = answers.get(0).toString();
                        String question2 = answers.get(1).toString();
                        String question3 = answers.get(2).toString();
                        String question4 = answers.get(3).toString();

                        Quiz quiz = new Quiz(question, question1, question2, question3,
                                question4, answer);

                        topicQuestions.add(quiz);
                    }

                    this.topics.add(new Topic(title, desc, topicQuestions));
                }
            } catch (JSONException error) {
                error.getStackTrace();
            }
        }

        public List<Topic> getAllTopics() {
            return this.topics;
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
                int correctAnswer) {
        this.text = text;
        answers = new ArrayList<>();
        this.answers.add(answer1);
        this.answers.add(answer2);
        this.answers.add(answer3);
        this.answers.add(answer4);
        this.correctAnswer = answers.get(correctAnswer - 1);
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
}

class MemoryRepository implements TopicRepository{

    private List<Topic> topics;
//    private static MemoryRepository instance = null;

    public MemoryRepository() {
        topics = new ArrayList<Topic>();

        List<Quiz> mathQuestions = new ArrayList<>();
        List<Quiz> physicsQuestions = new ArrayList<>();
        List<Quiz> marvelQuestions = new ArrayList<>();

        mathQuestions.add(new Quiz("What is 1 + 1?", "2", "11", "13", "4", 1));
        mathQuestions.add(new Quiz("What is 1 * 1?", "1", "3", "11", "111", 1));
        physicsQuestions.add(new Quiz("What is the correct formula of acceleration?",
                "a = (v - v0)^2 * t", "a = (v-v0)t", "a = 1/2 * t * v" , "a = x * t", 1));
        physicsQuestions.add(new Quiz("What is formula of force?", "mass", "mass * acceleration",
                "speed + time", "acceleration * speed", 2));
        marvelQuestions.add(new Quiz("What is the name of Iron man",
                "Tom Stark", "Tony Stark", "Stan Lee",
                "Don Heck", 2));
        marvelQuestions.add(new Quiz(" Who is the writer of Doctor Strange",
                "Steve Ditko", "Steve Ditko ", "Stan Lee",
                "Tom Palmer",3));


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