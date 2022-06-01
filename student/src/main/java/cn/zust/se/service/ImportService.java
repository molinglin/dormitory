package cn.zust.se.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ImportService {
    public Map<String,Object> importStu(MultipartFile file);
}
