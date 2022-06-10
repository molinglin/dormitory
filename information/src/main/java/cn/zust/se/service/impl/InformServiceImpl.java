package cn.zust.se.service.impl;

import cn.zust.se.dao.InformDao;
import cn.zust.se.eneity.Inform;
import cn.zust.se.service.InformService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class InformServiceImpl implements InformService {
    @Resource
    InformDao informDao;

    @Override
    public List<Inform> selAllInform() {
        return informDao.selAllInform();
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
    public Integer insertInform(String publisher, String content,String title) {

        return informDao.insertInform(publisher, content,title);
    }

    @Override
    public List<Inform> selInforms(String publisher, Date time) {
        return informDao.selInforms(publisher, time);
    }
}
