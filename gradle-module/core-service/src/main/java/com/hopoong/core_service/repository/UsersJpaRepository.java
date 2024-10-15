package com.hopoong.core_service.repository;

import com.hopoong.core_service.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersJpaRepository extends JpaRepository<UsersEntity, Long> {

}
