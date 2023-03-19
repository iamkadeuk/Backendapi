package com.project.Backendapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Backendapi.Controller.SearchingController;
import com.project.Backendapi.Dto.BlogParamDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;



//@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class SearchingControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private SearchingController searchingController;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void 블로그검색결과가_리턴된다() throws Exception {

        BlogParamDto blogParamDto = BlogParamDto.builder().query("카카오").sort("accuracy").page(1).size(2).build();

        mvc.perform(get("/blog")
                .contentType(MediaType.APPLICATION_JSON)
                .content("query=카카오&sort=accuracy&page=1&size=2"))
                .andExpect(status().isOk());
    }
    @Test
    public void 인기키워드가_리턴된다() throws Exception {

        mvc.perform(get("/popular/keyword"))
                .andExpect(status().isOk());
    }
}
