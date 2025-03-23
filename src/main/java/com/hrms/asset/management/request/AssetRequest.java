package com.hrms.asset.management.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AssetRequest {
    private Long id;
    private String assetCode;
    private String name;
    private String type;
    private String status;
    private String serialNumber;
    private LocalDate retirementDate;
    private Boolean isActive;
}
