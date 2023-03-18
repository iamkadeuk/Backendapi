package com.project.Backendapi.Controller;

import com.project.Backendapi.Entity.PopularKeyword;
import com.project.Backendapi.Entity.SearchingBlogResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/searching")
public class SearchingController {

    @GetMapping(value = "/blog")
    public List<SearchingBlogResp> getBlogList (String sortParam) {
        List<SearchingBlogResp> result = new ArrayList<>();

        return result;
    }

    @GetMapping(value = "/popular/keyword")
    public List<PopularKeyword> getPopularKeywordList () {
        List<PopularKeyword> result = new ArrayList<>();

        return result;
    }
}
