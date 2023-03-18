package com.project.Backendapi.Dto;

import lombok.Data;

import java.util.List;

@Data
public class BlogResp {
    public Integer totalCount;
    public Integer pageableCount;
    public Boolean isEnd;
    public List<BlogDocResp> documents;
}
