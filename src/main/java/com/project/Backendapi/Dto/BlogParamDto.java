package com.project.Backendapi.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@ApiModel(value = "블로그 검색 요청 파라미터", description = "검색을 원하는 질의어, 결과 문서 정렬방식, 결과 페이지 번호, 한 페이지에 보여질 문서 수를 가진 DTO")
public class BlogParamDto {
    @ApiModelProperty(value = "검색을 원하는 질의어")
    private String query;
    @ApiModelProperty(value = "결과 문서 정렬방식")
    private String sort;
    @ApiModelProperty(value = "결과 페이지 번호")
    private Integer page;
    @ApiModelProperty(value = "한 페이지에 보여질 문서 수")
    private Integer size;

//    @Builder
//    private BlogParamDto(String query, String sort, Integer page, Integer size) {
//        this.query = query;
//        this.sort = sort;
//        this.page = page;
//        this.size = size;
//    }
}
