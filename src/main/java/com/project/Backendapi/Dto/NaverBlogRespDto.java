package com.project.Backendapi.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor
@ApiModel(value = "네이버 블로그 검색 응답 파라미터",
        description = "검색 결과를 생성한 시간, 총 검색 결과 개수, 검색 시작 위치, 한 번에 표시할 검색 결과 개수, 검색된 블로그 목록을 가진 DTO")
public class NaverBlogRespDto {
    @ApiModelProperty(value = "검색 결과를 생성한 시간")
    private String lastBuildDate;
    @ApiModelProperty(value = "총 검색 결과 개수")
    private Integer total;
    @ApiModelProperty(value = "검색 시작 위치")
    private Integer start;
    @ApiModelProperty(value = "한 번에 표시할 검색 결과 개수")
    private Integer display;
    @ApiModelProperty(value = "검색된 블로그 목록")
    private List<NaverBlogDocRespDto> items;
}
