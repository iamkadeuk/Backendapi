package com.project.Backendapi.Repository;

import com.project.Backendapi.Entity.UserKeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserKeywordRepository extends JpaRepository<UserKeywordEntity, String> {
    List<UserKeywordEntity> findFirst10ByOrderByCntDescLastChgDtmdDesc();
}
