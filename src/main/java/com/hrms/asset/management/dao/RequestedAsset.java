package com.hrms.asset.management.dao;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "requested_asset")
public class RequestedAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employeeId;
    @ManyToOne
    @JoinColumn(name = "asset_id",nullable = false)
    private Asset asset;
    private String comment;
    private LocalDate date;
    private String reason;
    private String status; // pending, approved, rejected
    private Boolean isApproved;
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
