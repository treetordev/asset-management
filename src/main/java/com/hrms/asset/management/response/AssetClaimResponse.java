package com.hrms.asset.management.response;

import lombok.Data;

@Data
public class AssetClaimResponse {

    private Long id;
    private Long employeeId;
    private String assetType;
    private String comment;
    private String claimDate;
    private String reason;
    
}
