package com.project.Backendapi;

import com.project.Backendapi.Controller.SearchingController;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = SearchingController.class)
public class SearchingControllerTest {
    @Autowired
    private MockMvc mvc;
}
