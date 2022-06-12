package cn.zust.se.service.impl;

import cn.zust.se.dao.QuestionDao;
import cn.zust.se.eneity.Question;
import cn.zust.se.eneity.Questionnaire;
import cn.zust.se.service.QuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    QuestionDao  questionDao;
    @Override
    public Integer insertQ(Question question) {
        return questionDao.insertQ(question);
    }

    @Override
    public List<Question> selQ(String uid) {
        return questionDao.selQ(uid);
    }

    @Override
    public List<Questionnaire> selQByName(String name) {
        return questionDao.selQByName(name);
    }

    @Override
    public List<Questionnaire> selQs(String uid, Integer sleep, Integer getup, String temper,String name) {
        List<Questionnaire> questionnaires=questionDao.selQs(uid, sleep, getup, temper);
        if(name!=null){
            List<Questionnaire> questionnaires1=new ArrayList<>();
            for(int i=0;i<questionnaires.size();i++){
                if(Objects.equals(questionDao.selS(questionnaires.get(i).getUid()).getName(), name)){
                    questionnaires.get(i).setName(name);
                    questionnaires1.add(questionnaires.get(i));
                    return questionnaires1;
                }
            }
            return null;
        }else {
            List<Questionnaire> questionnaires2=new ArrayList<>();
            for(int i=0;i<questionnaires.size();i++){
                String name1=questionDao.selS(questionnaires.get(i).getUid()).getName();
                questionnaires.get(i).setName(name1);
                questionnaires2.add(questionnaires.get(i));
            }
            return questionnaires2;
        }
    }
}
