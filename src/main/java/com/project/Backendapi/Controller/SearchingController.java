package com.project.Backendapi.Controller;

import com.project.Backendapi.Dto.BlogParamDto;
import com.project.Backendapi.Dto.PopularKeywordDto;
import com.project.Backendapi.Dto.BlogRespDto;
import com.project.Backendapi.Service.BlogService;
import com.project.Backendapi.Service.KeywordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "조회 API")
@RestController
@RequestMapping("/api/v1/searching")
public class SearchingController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private KeywordService keywordService;

    @GetMapping(value = "/blog")
    public ResponseEntity<BlogRespDto> getBlogList (
            @ApiParam(value = "검색을 원하는 질의어(키워드)") @RequestParam(value = "query", required = true) String query,
            @ApiParam(value = "결과 문서 정렬 방식") @RequestParam(value = "sort", required = false, defaultValue = "accuracy") String sort,
            @ApiParam(value = "결과 페이지 번호") @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @ApiParam(value = "한 페이지에 보여질 문서 수") @RequestParam(value = "size", required = false, defaultValue = "10") Integer size
    ) {

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
    public ResponseEntity<List<PopularKeywordDto>> getPopularKeywordList () {
        List<PopularKeywordDto> popularKeywordDtoList = keywordService.popularKeywordList();

        if (popularKeywordDtoList != null) {
            return new ResponseEntity<>(popularKeywordDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
