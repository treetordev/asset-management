package com.hrms.asset.management.response;

import lombok.Data;

@Data
public class AssetResponse {

    private Long id;
    private String name;
    private String type;
    private String serialNumber;
    private Boolean isActive;
    private Boolean assigned;
    private AssetAllocationResponse assetAllocation;
}
