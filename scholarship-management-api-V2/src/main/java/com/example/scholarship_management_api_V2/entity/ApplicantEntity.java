package com.example.scholarship_management_api_V2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "applicants")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY);
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "GPA is mandatory")
    @DecimalMin(value = "0.00", inclusive = true, message = "GPA must be >= 0.00")
    @DecimalMax(value = "4.00", inclusive = true, message = "GPA must be <= 4.00")
    private Double gpa;

//    @NotNull(message = "Scholarship is mandatory")
//    private Scholarship scholarship;
}
