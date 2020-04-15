package com.example.demo.domain.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.UserEntity;

//@Repository : EntityのDBアクセスを行うクラス
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	// emailをもとにDBからデータを取得
    Optional<UserEntity> findByEmail(String email);
    
    // nameをもとにDBからデータを取得
    Optional<UserEntity> findFirstByName(String name);
    
    // passwordをもとにDBからデータを取得
    Optional<UserEntity> findFirstByPassword(String password);
    
}