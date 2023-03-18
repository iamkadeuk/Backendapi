package com.project.Backendapi.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
public class BlogParam {

    public String query;
    public String sort;
    public Integer page;
    public Integer size;
    @Builder
    public BlogParam (String query, String sort, Integer page, Integer size) {
        this.query = query;
        this.sort = sort;
        this.page = page;
        this.size = size;
    }
}
