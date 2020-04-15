package com.example.demo.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.entity.DeveloperEntity;

public interface DeveloperRepository extends JpaRepository<DeveloperEntity, Integer>{
	
	// IDをもとにDBからデータを取得
	List<DeveloperEntity> findById(int id);
}