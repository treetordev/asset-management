package com.hrms.asset.management.request;

import lombok.Data;

@Data
public class AssetRequest {

    private String name;
    private String type;
    private String serialNumber;

}
