package com.project.Backendapi.Repository;

import com.project.Backendapi.Entity.UserKeywordEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
class UserKeywordRepositoryTest {
    @Autowired
    private UserKeywordRepository userKeywordRepository;

    @Test
    @DisplayName("키워드 저장 및 조회 테스트")
    void saveKeywordTest () {
        List<UserKeywordEntity> ukeList = new ArrayList<>();

        for (int i = 1; i <= 10; i ++) {
            userKeywordRepository.save(UserKeywordEntity.builder().keyword("키워드_" + i).cnt(i).build());
            ukeList.add(UserKeywordEntity.builder().keyword("키워드_" + (11 - i)).cnt(11 - i).build());
        }
        List<UserKeywordEntity> ukeResult = userKeywordRepository.findFirst10ByOrderByCntDescLastChgDtmdDesc();
        for (int i = 0; i < 10; i ++) {
            assertThat(ukeResult.get(i).getKeyword()).isEqualTo(ukeList.get(i).getKeyword());
        }
    }

}