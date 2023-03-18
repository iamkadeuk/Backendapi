package com.project.Backendapi.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor
public class BlogRespDto {
    private Integer totalCount;
    private Integer pageableCount;
    private Boolean isEnd;
    private List<BlogDocRespDto> documents;

//    @Builder
//    private BlogRespDto(Integer totalCount, Integer pageableCount, Boolean isEnd, List<BlogDocRespDto> documents) {
//        this.totalCount = totalCount;
//        this.pageableCount = pageableCount;
//        this.isEnd = isEnd;
//        this.documents = documents;
//    }
}
