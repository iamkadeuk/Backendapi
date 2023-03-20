package com.project.Backendapi.Controller;

import com.project.Backendapi.Dto.BlogParamDto;
import com.project.Backendapi.Service.BlogService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SearchingControllerTest {

    @InjectMocks
    SearchingController searchingController;
    @Mock
    BlogService blogService;
    @Autowired
    MockMvc mockMvc;

    @Test
    void 블로그_검색결과가_리턴된다 () throws Exception {
        BlogParamDto blogParamDto = BlogParamDto.builder().query("카카오").sort("accuracy").page(1).size(2).build();

        assertNull(blogParamDto.getQuery());

        mockMvc.perform(get("/blog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("query=카카오&sort=accuracy&page=1&size=2"))
                .andExpect(status().isOk());
    }

    @Test
    public void 인기키워드가_리턴된다 () throws Exception {

        mockMvc.perform(get("/popular/keyword"))
                .andExpect(status().isOk());
    }
}