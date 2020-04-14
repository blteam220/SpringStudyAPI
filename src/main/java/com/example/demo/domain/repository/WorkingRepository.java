package com.example.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.EmployeeEntity;

//@Repository : EntityのDBアクセスを行うクラス
@Repository
public interface WorkingRepository extends JpaRepository<EmployeeEntity, Long>{
}