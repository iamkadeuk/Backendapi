package com.project.Backendapi.Service;

import com.project.Backendapi.Dto.BlogParamDto;
import com.project.Backendapi.Dto.BlogRespDto;

import java.util.Map;

public interface BlogService {
    public Map<String, Object> searchingBlogList (BlogParamDto blogParamDto);
}
