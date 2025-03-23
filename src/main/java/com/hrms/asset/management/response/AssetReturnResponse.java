package com.hrms.asset.management.response;

import com.hrms.asset.management.dao.Asset;

import lombok.Data;

@Data
public class AssetReturnResponse {

    private Asset asset;
    private String reason;
    private String returnStatus;
}
