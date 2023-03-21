package com.project.Backendapi.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Backendapi.Dto.BlogParamDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class SearchingControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("블로그 검색 API 테스트")
    void getBlogList () throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/v1/searching/blog");
        BlogParamDto dto = BlogParamDto.builder().query("애플페이").sort("recency").page(1).size(1).build();

        final ResultActions actions = mvc.perform((builder)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON));
        actions
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("인기 키워드 검색 API 테스트")
    void getPopularKeywordList () throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/api/v1/searching/popular/keyword");

        final ResultActions actions = mvc.perform((builder));
        actions
                .andExpect(status().isOk())
                .andDo(print());
    }
}