package com.project.Backendapi.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BlogDocResp {
    public String title;
    public String contents;
    public String url;
    public String blogname;
    public String thumbnail;
    public LocalDateTime datetime;
}
