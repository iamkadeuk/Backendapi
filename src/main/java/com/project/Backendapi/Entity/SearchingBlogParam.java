package com.project.Backendapi.Entity;

import lombok.Builder;
import lombok.Data;

@Data
public class SearchingBlogParam {

    public String query;
    public String sort;
    public Integer page;
    public Integer size;
}
