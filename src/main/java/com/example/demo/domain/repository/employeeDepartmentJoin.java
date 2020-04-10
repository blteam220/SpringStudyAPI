package com.example.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.departmentEntity;
import com.example.demo.domain.entity.employeeEntity;

/*
 * 
 */

@Repository
public interface employeeDepartmentJoin extends JpaRepository<employeeEntity, departmentEntity>{

}