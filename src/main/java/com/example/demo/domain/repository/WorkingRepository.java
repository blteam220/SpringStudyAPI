package com.example.demo.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.WorkingEntity;

// @Repository : EntityのDBアクセスを行うクラス
@Repository
public interface WorkingRepository extends JpaRepository<WorkingEntity, Integer>{
	
	// IDをもとにDBからデータを取得
	List<WorkingEntity> findById(int id);
}