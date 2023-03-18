package com.project.Backendapi.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class PopularKeywordDto {
    public String keyword;
    public Integer count;
}
