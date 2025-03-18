package com.hrms.asset.management.dao;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "asset_claim")
public class AssetClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private String assetType;
    private String comment;
    private LocalDate claimDate;
    private String reason;
    private String status; // pending, approved, rejected

}
