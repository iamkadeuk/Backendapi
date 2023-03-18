package com.project.Backendapi.Entity;

import lombok.Data;

import java.util.List;

@Data
public class SearchingBlogResp {
    public Integer totalCount;
    public Integer pageableCount;
    public Boolean isEnd;
    public List<SearchingBlogDocResp> documents;
}
