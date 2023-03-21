package com.project.Backendapi.Controller;

import com.project.Backendapi.Dto.BlogParamDto;
import com.project.Backendapi.Dto.PopularKeywordDto;
import com.project.Backendapi.Service.BlogService;
import com.project.Backendapi.Service.KeywordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Api(tags = "조회 API")
@RestController
@RequestMapping("/api/v1/searching")
public class SearchingController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private KeywordService keywordService;

    @PostMapping(value = "/blog")
    public ResponseEntity<Map<String, Object>> getBlogList (
            @Valid @RequestBody BlogParamDto blogParamDto
    ) {
        if (!ObjectUtils.isEmpty(blogParamDto)) {
            //BlogParamDto blogParamDto = BlogParamDto.builder().query(query).sort(sort).page(page).size(size).build();
            Map<String, Map<String, Object>> resultMap = blogService.searchingBlogList(blogParamDto);

            if (resultMap.containsKey(HttpStatus.OK.toString())) {
                return new ResponseEntity<>(resultMap.get(HttpStatus.OK.toString()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/popular/keyword")
    public ResponseEntity<List<PopularKeywordDto>> getPopularKeywordList () {
        List<PopularKeywordDto> popularKeywordDtoList = keywordService.popularKeywordList();

        if (popularKeywordDtoList != null) {
            return new ResponseEntity<>(popularKeywordDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
