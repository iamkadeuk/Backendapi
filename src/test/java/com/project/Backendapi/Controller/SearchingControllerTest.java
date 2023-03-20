package com.project.Backendapi.Controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = SearchingController.class)
//@SpringBootTest
class SearchingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void 블로그_검색결과가_리턴된다 () throws Exception {
//        BlogParamDto blogParamDto = BlogParamDto.builder().query("카카오").sort("accuracy").page(1).size(2).build();
//        assertNull(blogParamDto.getQuery());

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