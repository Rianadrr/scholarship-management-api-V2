package com.example.scholarship_management_api_V2.controller;

import com.example.scholarship_management_api_V2.dto.applicant.ApplicantResponse;
import com.example.scholarship_management_api_V2.dto.applicant.CreateApplicantRequest;
import com.example.scholarship_management_api_V2.dto.applicant.UpdateApplicantRequest;
import com.example.scholarship_management_api_V2.entity.ApplicantEntity;
import com.example.scholarship_management_api_V2.entity.ScholarshipEntity;
import com.example.scholarship_management_api_V2.repository.ApplicantRepository;
import com.example.scholarship_management_api_V2.repository.ScholarshipRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/applicants")
public class ApplicantController {

    public final ApplicantRepository applicantRepository;
    public final ScholarshipRepository scholarshipRepository;

    public ApplicantController(ApplicantRepository applicantRepository, ScholarshipRepository scholarshipRepository){
        this.applicantRepository = applicantRepository;
        this.scholarshipRepository = scholarshipRepository;
    }
    // Daftar beasiswa
//    @PostMapping
//    public ApplicantResponse createApplicant(@Valid @RequestBody CreateApplicantRequest request) {
//
//        // Cek apakah scholarship ada
//        ScholarshipEntity scholarship = scholarshipRepository.findById(request.getScholarshipId())
//                .orElseThrow(() -> new RuntimeException("Scholarship not found"));
//
//        // Hitung jumlah pendaftar pada scholarship ini
//        List<ApplicantEntity> existingApplicants = applicantRepository.findByScholarshipId(scholarship.getId());
//        if (existingApplicants.size() >= scholarship.getQuota()) {
//            throw new RuntimeException("Scholarship quota is full");
//        }
//
//        // Cek tanggal pendaftaran
//        LocalDate today = LocalDate.now();
//        if (today.isBefore(scholarship.getOpenDate()) || today.isAfter(scholarship.getCloseDate())) {
//            throw new RuntimeException("Scholarship registration is closed");
//        }
//
//        // Simpan data applicant baru
//        ApplicantEntity applicant = ApplicantEntity.builder()
//                .name(request.getName())
//                .email(request.getEmail())
//                .gpa(request.getGpa())
//                .scholarship(scholarship)
//                .build();
//
//        ApplicantEntity savedApplicant = applicantRepository.save(applicant);
//
//        // Mapping entity -> response DTO
//        return mapToResponse(savedApplicant);
//    }
//
//    // Helper untuk mapping Applicant -> ApplicantResponse
//    private ApplicantResponse mapToResponse(Applicant applicant) {
//        return ApplicantResponse.builder()
//                .id(applicant.getId())
//                .name(applicant.getName())
//                .email(applicant.getEmail())
//                .gpa(applicant.getGpa())
//                .scholarshipId(applicant.getScholarship().getId())
//                .build();
//    }

    // Get List Pendaftar
    @GetMapping
    public List<ApplicantEntity> findAllApplicant() {
        return applicantRepository.findAll();
    }

    // Get Detail Pendaftar
    @GetMapping("/{id}")
    public ApplicantEntity findById(@PathVariable("id") Long feedbackId) {
        return applicantRepository.findById(feedbackId)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
    }

    // Put Detail Pendaftar
    @PutMapping
    public ResponseEntity<ApplicantEntity> updateApplicant(@PathVariable Long id, @RequestBody UpdateApplicantRequest request){

        ApplicantEntity checkId = applicantRepository.findById(id).orElse(null);
        if (checkId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        ApplicantEntity existing = checkId;

        if (request.getName() != null) existing.setName(request.getName());
        if (request.getEmail() != null) existing.setEmail(request.getEmail());
        if (request.getGpa() != null) existing.setGpa(request.getGpa());

        ApplicantEntity updated = applicantRepository.save(existing);

        return ResponseEntity.ok((updated));
    }

}
