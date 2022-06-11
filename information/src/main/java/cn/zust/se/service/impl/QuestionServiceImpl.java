package cn.zust.se.service.impl;

import cn.zust.se.dao.QuestionDao;
import cn.zust.se.eneity.Question;
import cn.zust.se.eneity.Questionnaire;
import cn.zust.se.service.QuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    QuestionDao  questionDao;
    @Override
    public Integer insertQ(String uid, Integer sleep, Integer getup, Integer temper) {
        return questionDao.insertQ(uid, sleep, getup, temper);
    }

    @Override
    public List<Question> selQ(String uid) {
        return questionDao.selQ(uid);
    }

    @Override
    public List<Questionnaire> selQByName(String name) {
        return questionDao.selQByName(name);
    }
}
