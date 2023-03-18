package com.project.Backendapi.Controller;

import com.project.Backendapi.Dto.BlogParamDto;
import com.project.Backendapi.Dto.PopularKeywordDto;
import com.project.Backendapi.Dto.BlogRespDto;
import com.project.Backendapi.Service.BlogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/searching")
public class SearchingController {

    @Autowired
    private BlogService blogService;

    @GetMapping(value = "/blog")
    public ResponseEntity<BlogRespDto> getBlogList (
            @RequestParam(value = "query", required = true) String query,
            @RequestParam(value = "sort", required = false, defaultValue = "accuracy") String sort,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size
    ) {

        HashMap<String, String> result = new HashMap<>();

        if (StringUtils.isNotBlank(query)) {
            BlogParamDto blogParamDto = BlogParamDto.builder().query(query).sort(sort).page(page).size(size).build();
            BlogRespDto searchingResult = blogService.searchingBlogList(blogParamDto);

            if (searchingResult != null) {
                return new ResponseEntity<>(searchingResult, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/popular/keyword")
    public List<PopularKeywordDto> getPopularKeywordList () {
        List<PopularKeywordDto> result = new ArrayList<>();

        return result;
    }
}
