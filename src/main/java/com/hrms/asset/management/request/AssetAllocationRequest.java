package com.hrms.asset.management.request;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;

@Data
public class AssetAllocationRequest {
    
    private UUID employeeId;
    private LocalDate allocationDate;
}
