package cn.zust.se.service;

import cn.zust.se.eneity.Question;
import cn.zust.se.eneity.Questionnaire;

import java.util.List;

public interface QuestionService {
    Integer insertQ(String uid,Integer sleep,Integer getup,Integer temper);
    List<Question> selQ(String uid);
    List<Questionnaire> selQByName(String name);

}
