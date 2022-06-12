package cn.zust.se.service.impl;

import cn.zust.se.dao.InformDao;
import cn.zust.se.eneity.Inform;
import cn.zust.se.service.InformService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class InformServiceImpl implements InformService {
    @Resource
    InformDao informDao;

    @Override
    public List<Inform> selAllInform() {
        return informDao.selAllInform();
    }

    @Override
    public Integer updateInform(Integer id, String title, String content,String user) {
        if(Objects.equals(user, informDao.selInform(id).getPublisher())){
            return informDao.updateInform(id, title, content);
        }else {
            return 0;
        }

    }

    @Override
    public Integer delInform(Integer id) {
        return informDao.delInform(id);
    }

    @Override
    public Inform selInform(Integer id) {
        return informDao.selInform(id);
    }

    @Override
    public List<Inform> selInformByPublisher(String publisher) {
        return informDao.selInformByPublisher(publisher);
    }

    @Override
    public List<Inform> selInformByTime(Date time) {
        return informDao.selInformByTime(time);
    }

    @Override
    public List<Inform> selInformByPAndT(String publisher, Date time) {
        return informDao.selInformByPAndT(publisher, time);
    }

    @Override
    public Integer insertInform(Inform inform) {

        return informDao.insertInform(inform);
    }

    @Override
    public List<Inform> selInforms(String publisher, Date time) {
        return informDao.selInforms(publisher, time);
    }
}
