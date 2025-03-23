package com.hrms.asset.management.response;

import java.time.LocalDate;

import com.hrms.asset.management.dao.Employee;

import lombok.Data;

@Data
public class AssetAllocationResponse {

    private Long id;
    private String assetCode;
    private String name;
    private String type;
    private String status;
    private String serialNumber;
    private LocalDate retirementDate;
    private LocalDate allocationDate;
    private Employee assignedEmployeeId;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
