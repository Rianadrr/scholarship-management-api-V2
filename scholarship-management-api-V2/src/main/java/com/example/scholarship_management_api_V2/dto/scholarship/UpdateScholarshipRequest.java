package com.example.scholarship_management_api_V2.dto.scholarship;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UpdateScholarshipRequest {

    @Size(min = 3, message = "Name must be at least 3 characters")
    private String name;

    private String description;

    private LocalDate openDate;
    private LocalDate closeDate;

    @Min(value = 1, message = "Quota must be at least 1")
    private Integer quota;

}
