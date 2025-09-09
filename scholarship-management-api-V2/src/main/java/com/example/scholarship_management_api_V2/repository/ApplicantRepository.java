package com.example.scholarship_management_api_V2.repository;

import com.example.scholarship_management_api_V2.entity.ApplicantEntity;
import com.example.scholarship_management_api_V2.entity.ScholarshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicantRepository extends JpaRepository<ApplicantEntity, Long> {
    List<ApplicantEntity> findByScholarshipId(Long scholarshipId);
    List<ApplicantEntity> findByScholarship(ScholarshipEntity scholarship);

}
