package com.project.Backendapi.Entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SearchingBlogDocResp {
    public String title;
    public String contents;
    public String url;
    public String blogname;
    public String thumbnail;
    public LocalDateTime datetime;
}
