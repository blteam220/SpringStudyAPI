package com.example.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.model.entity.SpringStudyEntity;

@Repository
public interface SpringStudyRepository extends JpaRepository<SpringStudyEntity, Long> {

}