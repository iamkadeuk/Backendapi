package com.project.Backendapi.Service.Impl;

import com.project.Backendapi.Common.KakaoRestapiHelper;
import com.project.Backendapi.Dto.BlogDocRespDto;
import com.project.Backendapi.Dto.BlogParamDto;
import com.project.Backendapi.Dto.BlogRespDto;
import com.project.Backendapi.Entity.UserKeyword;
import com.project.Backendapi.Repository.UserKeywordRepository;
import com.project.Backendapi.Service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
    private UserKeywordRepository userKeywordRepository;

    public BlogRespDto searchingBlogList (BlogParamDto blogParamDto) {
        BlogRespDto result = new BlogRespDto();
        try {
            ResponseEntity<Map> resultMap = kakaoRestapiHelper.blog(blogParamDto);
            log.debug(resultMap.toString());

            if (HttpStatus.OK == resultMap.getStatusCode()) {
                // USER_KEYWORD 테이블에 저장
                UserKeyword userKeyword = userKeywordRepository.findById(blogParamDto.getQuery()).orElse(null);
                if (userKeyword != null) {
                    // update
                    userKeywordRepository.save(UserKeyword.builder()
                            .keyword(blogParamDto.getQuery())
                            .cnt(userKeyword.getCnt() + 1)
                            .fstRegDtmd(userKeyword.getFstRegDtmd())
                            .lastChgDtmd(LocalDateTime.now())
                            .build());
                } else {
                    // insert
                    userKeywordRepository.save(UserKeyword.builder()
                            .keyword(blogParamDto.getQuery())
                            .cnt(1)
                            .fstRegDtmd(LocalDateTime.now())
                            .lastChgDtmd(LocalDateTime.now())
                            .build());
                }

                result = parsingKakaoBlogResult(resultMap);
            } else {
                // 네이버 블로그 검색 구현
                result = null;
            }

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            result = null;
            log.error("searchingBlogList httpClientErrorException | HttpServerErrorException 발생={}", e.getStackTrace().toString());
        } catch (Exception e) {
            result = null;
            log.error("searchingBlogList Exception 발생={}", e.toString());
        } finally {
            return result;
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
}
