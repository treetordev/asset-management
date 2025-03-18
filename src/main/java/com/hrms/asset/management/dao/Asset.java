package com.hrms.asset.management.dao;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "assets")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, length = 50)
    private String type;
    @Column(unique = true, nullable = false,length = 100)
    private String serialNumber;
   // private String assetStatus;
    private boolean isAssigned=false;
    private Boolean isActive=true;
    // private String retirementDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "asset_allocation_id", nullable = true)
    private AssetAllocation assetAllocation;


}
