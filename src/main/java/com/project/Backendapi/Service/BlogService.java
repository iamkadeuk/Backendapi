package com.project.Backendapi.Service;

import com.project.Backendapi.Dto.BlogParamDto;

import java.util.Map;

public interface BlogService {
    public Map<String, Map<String, Object>> searchingBlogList (BlogParamDto blogParamDto);
}
