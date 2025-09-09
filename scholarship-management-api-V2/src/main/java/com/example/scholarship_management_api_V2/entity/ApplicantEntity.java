package com.example.scholarship_management_api_V2.entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Double gpa;

    @Column(nullable = false)
    private ScholarshipEntity scholarship;
}
