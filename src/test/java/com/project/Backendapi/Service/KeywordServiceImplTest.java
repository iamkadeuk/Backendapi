package com.project.Backendapi.Service;

import com.project.Backendapi.Repository.UserKeywordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class KeywordServiceImplTest {
    @Mock
    private UserKeywordRepository userKeywordRepository;
    @InjectMocks
    private KeywordService keywordService;

    @Test
    @DisplayName("인기 키워드 검색 서비스 테스트")
    void popularKeywordListTest () throws Exception {
    }
}