package com.hrms.asset.management.request;

import lombok.Data;

@Data
public class AssetClaimRequest {

    private Long id;
    private Long employeeId;
    private String assetType;
    private String comment;
    private String reason;


}
