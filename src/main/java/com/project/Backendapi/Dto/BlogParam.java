package com.project.Backendapi.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
public class BlogParam {

    private String query;
    private String sort;
    private Integer page;
    private Integer size;
    @Builder
    private BlogParam (String query, String sort, Integer page, Integer size) {
        this.query = query;
        this.sort = sort;
        this.page = page;
        this.size = size;
    }
}
