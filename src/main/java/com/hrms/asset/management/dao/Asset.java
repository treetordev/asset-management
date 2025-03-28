package com.hrms.asset.management.dao;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "assets")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String assetCode;
    private String name;
    private String type;
    private String status;
    private String serialNumber;
    private LocalDate retirementDate;
    private Boolean isActive;

    private LocalDate returnDate;
    private String returnDescription;

    private LocalDate allocationDate;
    private LocalDate reportDate;
    private String reportDescription;


    private UUID assignedEmployeeId;


    @Column(updatable = false)
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
    }
}
