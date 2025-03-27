package com.hrms.asset.management.response;

import java.time.LocalDate;
import java.util.UUID;

import com.hrms.asset.management.dao.Asset;

import lombok.Data;

@Data
public class AssetRequestResponse {

    private Long id;
    private UUID employeeId;
    private Asset asset;
    private String comment;
    private LocalDate date;
    private String reason;
    
}
