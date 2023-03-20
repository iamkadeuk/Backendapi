package com.project.Backendapi.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor
@ApiModel(value = "카카오 블로그 검색 응답 파라미터(Documents)",
        description = "블로그 글 제목, 블로그 글 요약, 블로그 글 URL, 블로그의 이름, 검색 시스템에서 추출한 대표 미리보기 이미지 URL, 블로그 글 작성시간(ISO8601)을 가진 DTO")
public class KakaoBlogDocRespDto {
    @ApiModelProperty(value = "블로그 글 제목")
    private String title;
    @ApiModelProperty(value = "블로그 글 요약")
    private String contents;
    @ApiModelProperty(value = "블로그 글 URL")
    private String url;
    @ApiModelProperty(value = "블로그의 이름")
    private String blogname;
    @ApiModelProperty(value = "검색 시스템에서 추출한 대표 미리보기 이미지 URL")
    private String thumbnail;
    @ApiModelProperty(value = "블로그 글 작성시간(ISO8601)")
    private LocalDateTime datetime;
}
