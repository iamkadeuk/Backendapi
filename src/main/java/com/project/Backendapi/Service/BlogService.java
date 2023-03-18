package com.project.Backendapi.Service;

import com.project.Backendapi.Dto.BlogParam;
import com.project.Backendapi.Dto.BlogResp;

import java.util.HashMap;
import java.util.List;
public interface BlogService {
    public BlogResp searchingBlogList (BlogParam blogParam);
}
