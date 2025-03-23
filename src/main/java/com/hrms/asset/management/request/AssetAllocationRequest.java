package com.hrms.asset.management.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AssetAllocationRequest {
    
    private Long employeeId;
    private LocalDate allocationDate;
}
