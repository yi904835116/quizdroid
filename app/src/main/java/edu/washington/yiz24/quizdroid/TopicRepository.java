package edu.washington.yiz24.quizdroid;

/**
 * Created by yizhaoyang on 3/2/17.
 */

import java.io.InputStream;
import java.util.List;

public interface TopicRepository {
    public  void createTopics(InputStream inputStream);
    public List<Question> getQuestions(Topic t);
    public void updateTopic(Topic t, Question q);
    public void deleteTopic(Topic t);
}