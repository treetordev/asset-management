package com.hrms.asset.management.request;

import java.time.LocalDate;


import lombok.Data;

@Data
public class AssetRequestSubmission {

    private String assetType;
    private String comment;
    private LocalDate requestedDate;
    private String reason;

}
