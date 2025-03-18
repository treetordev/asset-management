package com.hrms.asset.management.response;

import lombok.Data;

@Data
public class AssetAllocationResponse {

    private Long id;
    private Long employeeId;
    private String allocationDate;
}
