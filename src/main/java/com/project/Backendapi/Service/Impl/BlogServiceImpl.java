package com.project.Backendapi.Service.Impl;

import com.project.Backendapi.Common.KakaoRestapiHelper;
import com.project.Backendapi.Common.NaverRestapiHelper;
import com.project.Backendapi.Dto.BlogDocRespDto;
import com.project.Backendapi.Dto.BlogParamDto;
import com.project.Backendapi.Dto.BlogRespDto;
import com.project.Backendapi.Entity.UserKeywordEntity;
import com.project.Backendapi.Repository.UserKeywordRepository;
import com.project.Backendapi.Service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
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
    @Autowired
    private NaverRestapiHelper naverRestapiHelper;
    @Autowired
    private UserKeywordRepository userKeywordRepository;

    public Map<String, Object> searchingBlogList (BlogParamDto blogParamDto) {
        BlogRespDto result = new BlogRespDto();
        Map<String, Object> resultMap = new HashMap<>();

        this.insertUpdateUserKeyword(blogParamDto.getQuery());

        try {
            ResponseEntity<Map> kakaoResultMap = kakaoRestapiHelper.blog(blogParamDto);
            log.debug(kakaoResultMap.toString());

            //if (HttpStatus.OK == kakaoResultMap.getStatusCode()) {
            if (false) {
                result = this.parsingKakaoBlogResult(kakaoResultMap);
                resultMap.put(HttpStatus.OK.toString(), result);

            } else {

                //네이버 블로그 검색
                ResponseEntity<Map> naverResultMap = naverRestapiHelper.blog(blogParamDto);
                log.debug(naverResultMap.toString());

                if (HttpStatus.OK == naverResultMap.getStatusCode()) {
                    result = this.parsingNaverBlogResult(naverResultMap);
                    resultMap.put(HttpStatus.OK.toString(), result);
                }
            }

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            resultMap.put(HttpStatus.INTERNAL_SERVER_ERROR.toString(), null);
            log.error("searchingBlogList httpClientErrorException | HttpServerErrorException 발생={}", e.getStackTrace().toString());
        } catch (Exception e) {
            resultMap.put(HttpStatus.INTERNAL_SERVER_ERROR.toString(), null);
            log.error("searchingBlogList Exception 발생={}", e.toString());
        } finally {
            return resultMap;
        }
    }

    public void insertUpdateUserKeyword (String keyword) {
        // USER_KEYWORD 테이블에 저장
        UserKeywordEntity userKeywordEntity = userKeywordRepository.findById(keyword).orElse(null);
        if (userKeywordEntity != null) {
            // update
            userKeywordEntity.setCnt(userKeywordEntity.getCnt() + 1);
            userKeywordRepository.save(userKeywordEntity);
        } else {
            // insert
            userKeywordRepository.save(UserKeywordEntity.builder()
                    .keyword(keyword)
                    .cnt(1)
                    .build());
        }
    }

    public BlogRespDto parsingKakaoBlogResult (ResponseEntity<Map> resultMap) {
        BlogRespDto blogRespDto = new BlogRespDto();
        List<BlogDocRespDto> blogDocRespDtoList = new ArrayList<>();
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
        HashMap<String, Object> metaMap = (HashMap<String, Object>) resultMap.getBody().get("meta");

        ArrayList<Map> documentsList = (ArrayList<Map>) resultMap.getBody().get("documents");
        log.debug(documentsList.toString());

        for (Map document: documentsList) {
            blogDocRespDtoList.add(BlogDocRespDto.builder()
                    .title(document.get("title").toString())
                    .contents(document.get("contents").toString())
                    .url(document.get("url").toString())
                    .blogname(document.get("blogname").toString())
                    .thumbnail(document.get("thumbnail").toString())
                    .datetime(LocalDateTime.parse(document.get("datetime").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")))
                    .build()
            );
        }

        blogRespDto = BlogRespDto.builder()
                .totalCount((Integer) metaMap.get("total_count"))
                .pageableCount((Integer) metaMap.get("pageable_count"))
                .isEnd((Boolean) metaMap.get("is_end"))
                .documents(blogDocRespDtoList)
                .build();

        log.debug(blogRespDto.toString());

        return blogRespDto;
    }

    public BlogRespDto parsingNaverBlogResult (ResponseEntity<Map> resultMap) {
        BlogRespDto blogRespDto = new BlogRespDto();
        List<BlogDocRespDto> blogDocRespDtoList = new ArrayList<>();
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
        HashMap<String, Object> metaMap = (HashMap<String, Object>) resultMap.getBody().get("meta");

        ArrayList<Map> documentsList = (ArrayList<Map>) resultMap.getBody().get("documents");
        log.debug(documentsList.toString());

        for (Map document: documentsList) {
            blogDocRespDtoList.add(BlogDocRespDto.builder()
                    .title(document.get("title").toString())
                    .contents(document.get("contents").toString())
                    .url(document.get("url").toString())
                    .blogname(document.get("blogname").toString())
                    .thumbnail(document.get("thumbnail").toString())
                    .datetime(LocalDateTime.parse(document.get("datetime").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")))
                    .build()
            );
        }

        blogRespDto = BlogRespDto.builder()
                .totalCount((Integer) metaMap.get("total_count"))
                .pageableCount((Integer) metaMap.get("pageable_count"))
                .isEnd((Boolean) metaMap.get("is_end"))
                .documents(blogDocRespDtoList)
                .build();

        log.debug(blogRespDto.toString());

        return blogRespDto;
    }
}
