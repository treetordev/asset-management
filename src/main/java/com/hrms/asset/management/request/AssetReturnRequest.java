package com.hrms.asset.management.request;


import lombok.Data;

@Data
public class AssetReturnRequest {
    
    private String reason;
    private String status;

}
