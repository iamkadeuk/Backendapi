package com.project.Backendapi.Service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Backendapi.Common.KakaoRestapiHelper;
import com.project.Backendapi.Dto.BlogDocResp;
import com.project.Backendapi.Dto.BlogParam;
import com.project.Backendapi.Dto.BlogResp;
import com.project.Backendapi.Service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private KakaoRestapiHelper kakaoRestapiHelper;
    public BlogResp searchingBlogList (BlogParam blogParam) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        BlogResp blogResp = new BlogResp();
        List<BlogDocResp> blogDocRespList = new ArrayList<>();

        try {
            ResponseEntity<Map> resultMap = kakaoRestapiHelper.blog(blogParam);
            log.debug(resultMap.toString());

            if (HttpStatus.OK == resultMap.getStatusCode()) {
                HashMap<String, Object> metaMap = (HashMap<String, Object>) resultMap.getBody().get("meta");

                ArrayList<Map> documentsList = (ArrayList<Map>) resultMap.getBody().get("documents");
                log.debug(documentsList.toString());

                for (Map document: documentsList) {
                    blogDocRespList.add(BlogDocResp.builder()
                            .title(document.get("title").toString())
                            .contents(document.get("contents").toString())
                            .url(document.get("url").toString())
                            .blogname(document.get("blogname").toString())
                            .thumbnail(document.get("thumbnail").toString())
                            .datetime(LocalDateTime.parse(document.get("datetime").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")))
                            .build()
                    );
                }

                blogResp = BlogResp.builder()
                        .totalCount((Integer) metaMap.get("total_count"))
                        .pageableCount((Integer) metaMap.get("pageable_count"))
                        .isEnd((Boolean) metaMap.get("is_end"))
                        .documents(blogDocRespList)
                        .build();

                log.debug(blogResp.toString());
            }

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            result.put("statusCode", e.getRawStatusCode());
            result.put("body", e.getStatusText());
            log.error("searchingBlogList httpClientErrorException | HttpServerErrorException 발생={}", e.getStackTrace().toString());
        } catch (Exception e) {
            result.put("statusCode", "999");
            result.put("body", "Exception 오류");
            log.error("searchingBlogList Exception 발생={}", e.toString());
        }

        /*
        resultMap
            status: 200
            headers
            body
                documents
                    key
                    value
                meta
                    key
                    value
         */

        return blogResp;
    }
}
