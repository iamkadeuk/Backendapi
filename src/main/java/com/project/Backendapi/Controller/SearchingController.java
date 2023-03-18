package com.project.Backendapi.Controller;

import com.project.Backendapi.Dto.BlogParam;
import com.project.Backendapi.Dto.PopularKeyword;
import com.project.Backendapi.Dto.BlogResp;
import com.project.Backendapi.Service.BlogService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/searching")
public class SearchingController {

    @Autowired
    private BlogService blogService;

    @GetMapping(value = "/blog")
    public List<BlogResp> getBlogList (String query, String sort, Integer page, Integer size) {

        BlogParam blogParam = BlogParam.builder().query(query).sort(sort).page(page).size(size).build();

        List<BlogResp> result = blogService.searchingBlogList(blogParam);

        return result;
    }

    @GetMapping(value = "/popular/keyword")
    public List<PopularKeyword> getPopularKeywordList () {
        List<PopularKeyword> result = new ArrayList<>();

        return result;
    }
}
