package cn.zust.se.service;

import cn.zust.se.eneity.Question;

import java.util.List;

public interface QuestionService {
    Integer insertQ(String uid,Integer sleep,Integer getup,Integer smoke);
    List<Question> selQ(String uid);

}
