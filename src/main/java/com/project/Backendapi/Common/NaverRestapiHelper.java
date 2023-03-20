package com.project.Backendapi.Common;

import com.project.Backendapi.Dto.BlogParamDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;
import java.util.Map;

@Component
public class NaverRestapiHelper {
    @Value("${naver.client.id}")
    private String clientId;
    @Value("${naver.client.secret}")
    private String clientSecret;

    private static final String API_SERVER_HOST = "https://openapi.naver.com";
    private static final String SEARCH_PLACE_KEYWORD_PATH = "/v1/search/blog.json";

    public ResponseEntity<Map> blog (BlogParamDto blogParamDto) throws Exception {
        String customSortStr = new String();

        if (StringUtils.equals("recency", blogParamDto.getSort())) {
            customSortStr = "date";
        } else {
            customSortStr = "sim";
        }

        String queryString = "?query=" + URLEncoder.encode(blogParamDto.getQuery(), "UTF-8")
                + "&sort=" + customSortStr
                + "&start=" + blogParamDto.getPage()
                + "&display=" + blogParamDto.getSize();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("Accept", MediaType.ALL_VALUE);
        headers.add("X-Naver-Client-Id", clientId);
        headers.add("X-Naver-Client-Secret", clientSecret);

        URI url = URI.create(API_SERVER_HOST + SEARCH_PLACE_KEYWORD_PATH + queryString);
        RequestEntity<String> rq = new RequestEntity<>(headers, HttpMethod.GET, url);
        ResponseEntity<Map> re = restTemplate.exchange(rq, Map.class);

        return re;
    }
}
