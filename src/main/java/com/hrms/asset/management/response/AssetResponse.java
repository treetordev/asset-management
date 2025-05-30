package com.hrms.asset.management.response;

import java.time.LocalDate;


import lombok.Data;

@Data
public class AssetResponse {

    private Long id;
    private String assetCode;
    private String name;
    private String type;
    private String status;
    private String serialNumber;
    private EmployeeDto employee;
    private LocalDate retirementDate;
    private Boolean isActive;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
