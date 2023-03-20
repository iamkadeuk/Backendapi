package com.project.Backendapi.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor
@ApiModel(value = "카카오 블로그 검색 응답 파라미터",
        description = "검색된 문서수, 노출 가능 문서 수, 현재 페이지가 마지막 페이지인지 여부, 검색된 블로그 목록을 가진 DTO")
public class KakaoBlogRespDto {
    @ApiModelProperty(value = "검색된 문서수")
    private Integer totalCount;
    @ApiModelProperty(value = "노출 가능 문서 수")
    private Integer pageableCount;
    @ApiModelProperty(value = "현재 페이지가 마지막 페이지인지 여부")
    private Boolean isEnd;
    @ApiModelProperty(value = "검색된 블로그 목록")
    private List<KakaoBlogDocRespDto> documents;
}
