package com.project.Backendapi.Common;


import com.project.Backendapi.Dto.BlogParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;

@Component
public class KakaoRestapiHelper {
    @Value("${kakao.restapi.key}")
    private String restApiKey;

    private static final String API_SERVER_HOST = "https://dapi.kakao.com";
    private static final String SEARCH_PLACE_KEYWORD_PATH = "/v2/search/blog";

    public ResponseEntity<String> blog (BlogParam blogParam) throws Exception {
        String queryString = "?query=" + URLEncoder.encode(blogParam.getQuery(), "UTF-8")
                + "&sort=" + blogParam.getSort() + "&page=" + blogParam.getPage() + "&size=" + blogParam.getSize();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "KakaoAK " + restApiKey);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        URI url = URI.create(API_SERVER_HOST+SEARCH_PLACE_KEYWORD_PATH + queryString);
        RequestEntity<String> rq = new RequestEntity<>(headers, HttpMethod.GET, url);
        ResponseEntity<String> re = restTemplate.exchange(rq, String.class);

        return re;
    }
}
