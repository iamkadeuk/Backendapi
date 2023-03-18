package com.project.Backendapi.Service;

import com.project.Backendapi.Dto.BlogParamDto;
import com.project.Backendapi.Dto.BlogRespDto;

public interface BlogService {
    public BlogRespDto searchingBlogList (BlogParamDto blogParamDto);
}
