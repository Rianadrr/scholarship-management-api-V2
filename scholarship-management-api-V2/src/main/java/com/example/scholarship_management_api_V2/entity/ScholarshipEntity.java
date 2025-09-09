package com.example.scholarship_management_api_V2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "scholarships")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScholarshipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String name;

    private String description;

    @NotNull(message = "Open date is mandatory")
    private LocalDate openDate;

    @NotNull(message = "Close date is mandatory")
    private LocalDate closeDate;

    @Min(value = 1, message = "Quota must be at least 1")
    private Integer quota;

    @OneToMany(mappedBy = "scholarship", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<ApplicantEntity> applicants;
}
