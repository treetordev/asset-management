package com.hrms.asset.management.response;

import java.time.LocalDate;

import com.hrms.asset.management.dao.Asset;

import lombok.Data;

@Data
public class AssetRequestResponse {

    private Long id;
    private Long employeeId;
    private Asset asset;
    private String comment;
    private LocalDate date;
    private String reason;
    
}
