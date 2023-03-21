package com.project.Backendapi.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@SuperBuilder
@NoArgsConstructor
@ApiModel(value = "블로그 검색 요청 파라미터",
        description = "검색을 원하는 질의어, 결과 문서 정렬방식, 결과 페이지 번호, 한 페이지에 보여질 문서 수를 가진 DTO")
public class BlogParamDto {
    @NotNull
    @ApiModelProperty(value = "검색을 원하는 질의어(키워드)")
    private String query;
    @ApiModelProperty(value = "결과 문서 정렬방식: accuracy(정확도순) | recency(최신순)")
    private String sort;
    @Min(1)
    @Max(50)
    @ApiModelProperty(value = "결과 페이지 번호")
    private Integer page;
    @Min(1)
    @Max(50)
    @ApiModelProperty(value = "한 페이지에 보여질 문서 수")
    private Integer size;
}
