package com.spd.uthservice.repository;

import com.spd.uthservice.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUserName(String username);

    @Query("select u from UserEntity u where u.email=:email")
    Optional<UserEntity> findByEmail(@Param("email") String email);

//    Page<UserEntity> findUserEntitiesBy
}
