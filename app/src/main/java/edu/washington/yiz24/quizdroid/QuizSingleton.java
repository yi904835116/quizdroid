package edu.washington.yiz24.quizdroid;

/**
 * Created by yizhaoyang on 3/2/17.
 */

public class QuizSingleton {

    private static QuizSingleton instance;

    public static void initInstance() {
        if (instance == null) {
            instance = new QuizSingleton();
        }
    }

    public static QuizSingleton getInstance() {
        // Return the instance
        return instance;
    }

    private QuizSingleton() {
    }

}