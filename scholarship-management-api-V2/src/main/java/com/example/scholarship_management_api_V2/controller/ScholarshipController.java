package com.example.scholarship_management_api_V2.controller;

import com.example.scholarship_management_api_V2.dto.scholarship.CreateScholarshipRequest;
import com.example.scholarship_management_api_V2.dto.scholarship.ScholarshipResponse;
import com.example.scholarship_management_api_V2.dto.scholarship.UpdateScholarshipRequest;
import com.example.scholarship_management_api_V2.entity.ApplicantEntity;
import com.example.scholarship_management_api_V2.entity.ScholarshipEntity;
import com.example.scholarship_management_api_V2.repository.ApplicantRepository;
import com.example.scholarship_management_api_V2.repository.ScholarshipRepository;
import jakarta.servlet.http.PushBuilder;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/scholarships")
public class ScholarshipController {
    public final ScholarshipRepository scholarshipRepository;
    public final ApplicantRepository applicantRepository;

    public ScholarshipController (ScholarshipRepository scholarshipRepository, ApplicantRepository applicantRepository){

        this.scholarshipRepository=scholarshipRepository;
        this.applicantRepository=applicantRepository;
    }

    @PostMapping
    public ResponseEntity<ScholarshipEntity> createScholarship(@Valid @RequestBody CreateScholarshipRequest request){

        if (request.getOpenDate().isAfter(request.getCloseDate())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }

        ScholarshipEntity scholarship = ScholarshipEntity
                .builder()
                .name(request.getName())
                .description(request.getDescription())
                .openDate(request.getOpenDate())
                .closeDate(request.getCloseDate())
                .quota(request.getQuota())
                .build();

        ScholarshipEntity saved = scholarshipRepository.save(scholarship);
        return ResponseEntity.status(HttpStatus.CREATED).body((saved));
    }

    @GetMapping
    public List<ScholarshipEntity> findAllScholarship(){

        return scholarshipRepository.findAll();
    }

    @GetMapping
    public List<ApplicantEntity> getApplicantFromScholarshipId(@PathVariable Long id){
        ScholarshipEntity scholarship = scholarshipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));

//         return applicantRepository.findById(id)
////                 .orElseThrow(() -> new RuntimeException("Applicant not found"));
    }

    @PutMapping({"/id"})
    public ResponseEntity<ScholarshipEntity> updateScholarship(@PathVariable Long id, @RequestBody UpdateScholarshipRequest request) {

        ScholarshipEntity checkId =scholarshipRepository.findById(id).orElse(null);
        if(checkId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

        ScholarshipEntity existing = checkId;

        if (request.getName() != null) existing.setName(request.getName());
        if (request.getDescription() != null) existing.setDescription(request.getDescription());
        if (request.getOpenDate() != null) existing.setOpenDate(request.getOpenDate());
        if (request.getCloseDate() != null) existing.setCloseDate(request.getCloseDate());
        if (request.getQuota() != null) existing.setQuota(request.getQuota());

        if (existing.getOpenDate().isAfter(existing.getCloseDate())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }

        ScholarshipEntity updated = scholarshipRepository.save(existing);
        return ResponseEntity.ok((updated));


    }
}
