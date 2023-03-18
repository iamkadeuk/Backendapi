package com.project.Backendapi.Repository;

import com.project.Backendapi.Dto.PopularKeywordDto;
import com.project.Backendapi.Entity.UserKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserKeywordRepository extends JpaRepository<UserKeyword, String> {
    List<UserKeyword> findFirst10ByOrderByCntDescLastChgDtmdDesc();
}
