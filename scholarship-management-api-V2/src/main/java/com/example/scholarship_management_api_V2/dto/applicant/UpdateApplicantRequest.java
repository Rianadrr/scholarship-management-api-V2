package com.example.scholarship_management_api_V2.dto.applicant;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateApplicantRequest {

    @Size(min = 3, message = "Name must be at least 3 characters")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @DecimalMin(value = "0.0", inclusive = true, message = "GPA must be >= 0.00")
    @DecimalMax(value = "4.0", inclusive = true, message = "GPA must be <= 4.00")
    private Double gpa;
}
