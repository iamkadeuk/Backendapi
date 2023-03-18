package com.project.Backendapi.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class BlogParamDto {

    private String query;
    private String sort;
    private Integer page;
    private Integer size;

//    @Builder
//    private BlogParamDto(String query, String sort, Integer page, Integer size) {
//        this.query = query;
//        this.sort = sort;
//        this.page = page;
//        this.size = size;
//    }
}
