package com.example.scholarship_management_api_V2.dto.applicant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicantResponse {
    private Long id;
    private String name;
    private String email;
    private Double gpa;
    private String scholarshipName;
}
