package com.example.scholarship_management_api_V2.dto.scholarship;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ScholarshipResponse {

    private Long id;
    private String name;
    private String description;
    private LocalDate openDate;
    private LocalDate closeDate;
    private Integer quota;

}
