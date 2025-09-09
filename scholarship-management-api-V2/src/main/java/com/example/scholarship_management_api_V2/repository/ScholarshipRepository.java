package com.example.scholarship_management_api_V2.repository;

import com.example.scholarship_management_api_V2.entity.ScholarshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScholarshipRepository extends JpaRepository<ScholarshipEntity, Long> {
}
