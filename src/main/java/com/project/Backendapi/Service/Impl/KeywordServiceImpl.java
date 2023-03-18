package com.project.Backendapi.Service.Impl;

import com.project.Backendapi.Dto.PopularKeywordDto;
import com.project.Backendapi.Entity.UserKeyword;
import com.project.Backendapi.Repository.UserKeywordRepository;
import com.project.Backendapi.Service.KeywordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class KeywordServiceImpl implements KeywordService {

    @Autowired
    private UserKeywordRepository userKeywordRepository;

    public List<PopularKeywordDto> popularKeywordList () {
        List<PopularKeywordDto> popularKeywordDtoList = new ArrayList<>();
        List<UserKeyword> userKeywordList = userKeywordRepository.findFirst10ByOrderByCntDescLastChgDtmdDesc();

        if (userKeywordList != null && userKeywordList.size() > 0) {
            for (UserKeyword userKeyword: userKeywordList) {
                popularKeywordDtoList.add(PopularKeywordDto.builder()
                        .keyword(userKeyword.getKeyword())
                        .count(userKeyword.getCnt())
                        .build());
            }
        }
        return popularKeywordDtoList;
    }

}
