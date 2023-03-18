package com.project.Backendapi.Service.Impl;

import com.project.Backendapi.Common.KakaoRestapiHelper;
import com.project.Backendapi.Dto.BlogParam;
import com.project.Backendapi.Dto.BlogResp;
import com.project.Backendapi.Service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private KakaoRestapiHelper krh;
    public List<BlogResp> searchingBlogList (BlogParam blogParam) {
        List<BlogResp> result = new ArrayList<>();
        try {
            ResponseEntity<String> tmp = krh.blog(blogParam);
            log.info(tmp.toString());
        } catch (Exception e) {
            log.error("searchingBlogList Exception 발생={}", e.getStackTrace().toString());
        }

        return result;
    }
}
