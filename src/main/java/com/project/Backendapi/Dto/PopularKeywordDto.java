package com.project.Backendapi.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@ApiModel(value = "인기 키워드 검색 응답 파라미터",
        description = "키워드와 조회수를 가진 DTO")
public class PopularKeywordDto {
    @ApiModelProperty(value = "키워드")
    public String keyword;
    @ApiModelProperty(value = "조회수")
    public Integer count;
}
