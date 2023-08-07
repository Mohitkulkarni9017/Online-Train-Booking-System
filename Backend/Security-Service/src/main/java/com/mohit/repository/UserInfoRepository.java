package com.mohit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mohit.entity.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByName(String username);
    
//    UserInfo findAllByName(String username);

}
