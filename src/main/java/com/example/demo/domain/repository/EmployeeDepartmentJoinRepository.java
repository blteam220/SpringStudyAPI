package com.example.demo.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.EmployeeEntity;

// @Repository : EntityのDBアクセスを行うクラス
@Repository
public interface EmployeeDepartmentJoinRepository extends JpaRepository<EmployeeEntity, Integer>{
	
	// IDをもとにDBからデータを取得
	List<EmployeeEntity> findById(int id);
}