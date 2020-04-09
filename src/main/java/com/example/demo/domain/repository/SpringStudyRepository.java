package com.example.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.SpringStudyEntity;

//import java.util.ArrayList;
import java.util.List;

@Repository
public interface SpringStudyRepository extends JpaRepository<SpringStudyEntity, Integer> {
	
	List<SpringStudyEntity> findById(int id);

}