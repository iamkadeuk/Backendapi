package com.project.Backendapi.Common;


import com.project.Backendapi.Dto.BlogParamDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;
import java.util.Map;

@Component
public class KakaoRestapiHelper {
    @Value("${kakao.restapi.key}")
    private String restApiKey;

    private static final String API_SERVER_HOST = "https://dapi.kakao.com";
    private static final String SEARCH_PLACE_KEYWORD_PATH = "/v2/search/blog";

    public ResponseEntity<Map> blog (BlogParamDto blogParamDto) throws Exception {
        String queryString = "?query=" + URLEncoder.encode(blogParamDto.getQuery(), "UTF-8")
                + "&sort=" + blogParamDto.getSort() + "&page=" + blogParamDto.getPage() + "&size=" + blogParamDto.getSize();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "KakaoAK " + restApiKey);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        URI url = URI.create(API_SERVER_HOST+SEARCH_PLACE_KEYWORD_PATH + queryString);
        RequestEntity<String> rq = new RequestEntity<>(headers, HttpMethod.GET, url);
        ResponseEntity<Map> re = restTemplate.exchange(rq, Map.class);

        return re;
    }
}
