package com.example.scholarship_management_api_V2.dto.applicant;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateApplicantRequest {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "GPA is mandatory")
    @DecimalMin(value = "0.0", inclusive = true, message = "GPA must be >= 0.00")
    @DecimalMax(value = "4.0", inclusive = true, message = "GPA must be <= 4.00")
    private Double gpa;

    @NotNull(message = "Scholarship ID is mandatory")
    private Long scholarshipId;
}
