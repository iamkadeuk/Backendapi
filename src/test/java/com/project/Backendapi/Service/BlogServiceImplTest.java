package com.project.Backendapi.Service;

import com.project.Backendapi.Common.KakaoRestapiHelper;
import com.project.Backendapi.Common.NaverRestapiHelper;
import com.project.Backendapi.Dto.BlogParamDto;
import com.project.Backendapi.Repository.UserKeywordRepository;
import com.project.Backendapi.Service.Impl.BlogServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class BlogServiceImplTest {

    @InjectMocks
    private BlogServiceImpl blogServiceImpl;
    @InjectMocks
    private KakaoRestapiHelper kakaoRestapiHelper;
    @InjectMocks
    private NaverRestapiHelper naverRestapiHelper;
    @Mock
    private UserKeywordRepository userKeywordRepository;

    @Test
    @DisplayName("블로그 검색 서비스 테스트")
    void searchingBlogListTest () throws Exception {
        Map<String, Map<String, Object>> result = blogServiceImpl.searchingBlogList(BlogParamDto.builder().query("카뱅").sort("accuracy").size(1).page(1).build());
        assertThat(result.containsKey(HttpStatus.OK.toString())).isEqualTo(true);
        assertThat(result.get(HttpStatus.OK.toString()).containsKey("KAKAO")).isEqualTo(true);
        assertThat(result.get(HttpStatus.OK.toString()).containsKey("NAVER")).isEqualTo(false);
    }
}