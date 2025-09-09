package com.example.scholarship_management_api_V2.controller;

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
    @PostMapping
    public ResponseEntity<ApplicantEntity> createApplicant(@Valid @RequestBody CreateApplicantRequest request) {

        // Cek apakah scholarship ada
        ScholarshipEntity scholarship = scholarshipRepository.findById(request.getScholarshipId())
                .orElseThrow(() -> new RuntimeException("Scholarship not found"));

        // Hitung jumlah pendaftar pada scholarship ini
        List<ApplicantEntity> existingApplicants = applicantRepository.findByScholarshipId(scholarship.getId());
        if (existingApplicants.size() >= scholarship.getQuota()) {
            throw new RuntimeException("Scholarship quota is full");
        }

        // Cek tanggal pendaftaran
        LocalDate today = LocalDate.now();
        if (today.isBefore(scholarship.getOpenDate()) || today.isAfter(scholarship.getCloseDate())) {
            throw new RuntimeException("Scholarship registration is closed");
        }

        // Simpan data applicant baru
        ApplicantEntity applicant = ApplicantEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .gpa(request.getGpa())
                .scholarship(scholarship)
                .build();

        ApplicantEntity savedApplicant = applicantRepository.save(applicant);

        // Mapping entity -> response DTO
        return ResponseEntity.ok((savedApplicant));
    }

    // Get List Pendaftar
    @GetMapping
    public List<ApplicantEntity> findAllApplicant() {
        return applicantRepository.findAll();
    }

    // Get Detail Pendaftar
    @GetMapping("/{id}")
    public ApplicantEntity findById(@PathVariable("id") Long applicantId) {
        return applicantRepository.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));
    }

    // Put Detail Pendaftar
    @PutMapping("/{id}")
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long applicantId) {
        ApplicantEntity applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));

        applicantRepository.delete(applicant);

        return ResponseEntity.ok("Applicant deleted successfully");
    }

}
