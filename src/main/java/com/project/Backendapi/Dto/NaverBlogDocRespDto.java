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
@ApiModel(value = "네이버 블로그 검색 응답 파라미터(Items)",
        description = "블로그 포스트의 제목, 블로그 포스트의 URL, 블로그 포스트의 내용을 요약한 패시지 정보, 블로그 포스트가 있는 블로그의 이름, 블로그 포스트가 있는 블로그의 주소, 블로그 포스트가 작성된 날짜를 가진 DTO")
public class NaverBlogDocRespDto {
    @ApiModelProperty(value = "블로그 포스트의 제목")
    private String title;
    @ApiModelProperty(value = "블로그 포스트의 URL")
    private String link;
    @ApiModelProperty(value = "블로그 포스트의 내용을 요약한 패시지 정보")
    private String descriptions;
    @ApiModelProperty(value = "블로그 포스트가 있는 블로그의 이름")
    private String bloggername;
    @ApiModelProperty(value = "블로그 포스트가 있는 블로그의 주소")
    private String bloggerlink;
    @ApiModelProperty(value = "블로그 포스트가 작성된 날짜")
    private String postdate;
}
