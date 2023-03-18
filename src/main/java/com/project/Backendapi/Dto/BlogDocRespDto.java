package com.project.Backendapi.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class BlogDocRespDto {
    private String title;
    private String contents;
    private String url;
    private String blogname;
    private String thumbnail;
    private LocalDateTime datetime;

    @Builder
    private BlogDocRespDto(String title, String contents, String url, String blogname, String thumbnail, LocalDateTime datetime) {
        this.title = title;
        this.contents = contents;
        this.url = url;
        this.blogname = blogname;
        this.thumbnail = thumbnail;
        this.datetime = datetime;
    }
}
