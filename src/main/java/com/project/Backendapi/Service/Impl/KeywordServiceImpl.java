package com.project.Backendapi.Service.Impl;

import com.project.Backendapi.Dto.PopularKeywordDto;
import com.project.Backendapi.Entity.UserKeywordEntity;
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
        List<UserKeywordEntity> userKeywordEntityList = userKeywordRepository.findFirst10ByOrderByCntDescLastChgDtmdDesc();

        if (userKeywordEntityList != null && userKeywordEntityList.size() > 0) {
            for (UserKeywordEntity userKeywordEntity : userKeywordEntityList) {
                popularKeywordDtoList.add(PopularKeywordDto.builder()
                        .keyword(userKeywordEntity.getKeyword())
                        .count(userKeywordEntity.getCnt())
                        .build());
            }
        }
        return popularKeywordDtoList;
    }

}
