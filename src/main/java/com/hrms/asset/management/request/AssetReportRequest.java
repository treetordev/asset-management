package com.hrms.asset.management.request;

import java.time.LocalDate;
import lombok.Data;

@Data
public class AssetReportRequest {

    private String status;
    private LocalDate reportingDate;
    private String reportingDescription;
}
