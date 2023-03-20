package com.project.Backendapi.Service.Impl;

import com.project.Backendapi.Common.KakaoRestapiHelper;
import com.project.Backendapi.Common.NaverRestapiHelper;
import com.project.Backendapi.Dto.*;
import com.project.Backendapi.Entity.UserKeywordEntity;
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
    private NaverRestapiHelper naverRestapiHelper;
    @Autowired
    private UserKeywordRepository userKeywordRepository;

    public Map<String, Map<String, Object>> searchingBlogList (BlogParamDto blogParamDto) {
        KakaoBlogRespDto kakaoBlogRespDto = new KakaoBlogRespDto();
        NaverBlogRespDto naverBlogRespDto = new NaverBlogRespDto();
        Map<String, Map<String, Object>> resultMap = new HashMap<>();
        Map<String, Object> subResultMap = new HashMap<>();

        // 키워드 저장
        this.insertUpdateUserKeyword(blogParamDto.getQuery());

        try {
            // 카카오 블로그 우선 검색
            ResponseEntity<Map> kakaoResultMap = kakaoRestapiHelper.blog(blogParamDto);

            // log.debug(kakaoResultMap.toString());

            if (HttpStatus.OK == kakaoResultMap.getStatusCode()) {
            //if (false) {
                kakaoBlogRespDto = this.parsingKakaoBlogResult(kakaoResultMap);
                subResultMap.put("KAKAO", kakaoBlogRespDto);
                resultMap.put(HttpStatus.OK.toString(), subResultMap);

            } else {

                // 네이버 블로그 검색
                ResponseEntity<Map> naverResultMap = naverRestapiHelper.blog(blogParamDto);

                // log.debug(naverResultMap.toString());

                if (HttpStatus.OK == naverResultMap.getStatusCode()) {
                    naverBlogRespDto = this.parsingNaverBlogResult(naverResultMap);
                    subResultMap.put("NAVER", naverBlogRespDto);
                    resultMap.put(HttpStatus.OK.toString(), subResultMap);
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

    public KakaoBlogRespDto parsingKakaoBlogResult (ResponseEntity<Map> resultMap) {
        KakaoBlogRespDto kakaoBlogRespDto = new KakaoBlogRespDto();
        List<KakaoBlogDocRespDto> kakaoBlogDocRespDtoList = new ArrayList<>();

        HashMap<String, Object> metaMap = (HashMap<String, Object>) resultMap.getBody().get("meta");
        ArrayList<Map> documentsList = (ArrayList<Map>) resultMap.getBody().get("documents");

        // log.debug(documentsList.toString());

        for (Map document: documentsList) {
            kakaoBlogDocRespDtoList.add(KakaoBlogDocRespDto.builder()
                    .title(document.get("title").toString())
                    .contents(document.get("contents").toString())
                    .url(document.get("url").toString())
                    .blogname(document.get("blogname").toString())
                    .thumbnail(document.get("thumbnail").toString())
                    .datetime(LocalDateTime.parse(document.get("datetime").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")))
                    .build()
            );
        }

        kakaoBlogRespDto = KakaoBlogRespDto.builder()
                .totalCount((Integer) metaMap.get("total_count"))
                .pageableCount((Integer) metaMap.get("pageable_count"))
                .isEnd((Boolean) metaMap.get("is_end"))
                .documents(kakaoBlogDocRespDtoList)
                .build();

        // log.debug(kakaoBlogRespDto.toString());

        return kakaoBlogRespDto;
    }

    public NaverBlogRespDto parsingNaverBlogResult (ResponseEntity<Map> resultMap) {
        NaverBlogRespDto naverBlogRespDto = new NaverBlogRespDto();
        List<NaverBlogDocRespDto> naverBlogDocRespDtoList = new ArrayList<>();

        ArrayList<Map> itemsList = (ArrayList<Map>) resultMap.getBody().get("items");

        // log.debug(itemsList.toString());

        for (Map item: itemsList) {
            naverBlogDocRespDtoList.add(NaverBlogDocRespDto.builder()
                    .title(item.get("title").toString())
                    .link(item.get("link").toString())
                    .descriptions(item.get("description").toString())
                    .bloggername(item.get("bloggername").toString())
                    .bloggerlink(item.get("bloggerlink").toString())
                    .postdate(item.get("postdate").toString())
                    .build()
            );
        }

        naverBlogRespDto = NaverBlogRespDto.builder()
                .lastBuildDate(resultMap.getBody().get("lastBuildDate").toString())
                .total((Integer) resultMap.getBody().get("total"))
                .start((Integer) resultMap.getBody().get("start"))
                .display((Integer) resultMap.getBody().get("display"))
                .items(naverBlogDocRespDtoList)
                .build();

        // log.debug(naverBlogRespDto.toString());

        return naverBlogRespDto;
    }
}
