package com.project.Backendapi.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BlogResp {
    private Integer totalCount;
    private Integer pageableCount;
    private Boolean isEnd;
    private List<BlogDocResp> documents;

    @Builder
    private BlogResp (Integer totalCount, Integer pageableCount, Boolean isEnd, List<BlogDocResp> documents) {
        this.totalCount = totalCount;
        this.pageableCount = pageableCount;
        this.isEnd = isEnd;
        this.documents = documents;
    }
}
