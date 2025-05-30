package com.hrms.asset.management.utility;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.hrms.asset.management.dao.Asset;
import com.hrms.asset.management.request.AssetAllocationRequest;
import com.hrms.asset.management.request.AssetRequest;
import com.hrms.asset.management.response.AssetAllocationResponse;
import com.hrms.asset.management.response.AssetReportResponse;
import com.hrms.asset.management.response.AssetResponse;
import com.hrms.asset.management.response.AssetReturnResponse;
import com.hrms.asset.management.response.EmployeeDto;

@Component
public class AssetMapper {
    private final ModelMapper modelMapper;

    public AssetMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

    }

    public Asset convertToEntity(AssetRequest assetRequest) {

        return modelMapper.map(assetRequest, Asset.class);
    }

    public Asset convertToEntity(AssetAllocationRequest assetRequest) {

        return modelMapper.map(assetRequest, Asset.class);
    }
    public AssetResponse convertToResponse(Asset asset) {
        try {
            return modelMapper.map(asset, AssetResponse.class);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error in converting AssetRequest to Asset");
        }
    }
    public AssetResponse convertToResponse(Asset asset, EmployeeDto employeeDto) {
        try {
            AssetResponse assetResponse = modelMapper.map(asset, AssetResponse.class);
            assetResponse.setEmployee(employeeDto);
            return assetResponse;
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error in converting AssetRequest to Asset");
        }
    }

    public AssetAllocationResponse convertToAllocationResponse(Asset asset) {
        try {
            return modelMapper.map(asset, AssetAllocationResponse.class);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error in converting AssetRequest to Asset");
        }
    }
    public AssetAllocationResponse convertToAllocationResponse(Asset asset, EmployeeDto employeeDto) {
        try {
            AssetAllocationResponse assetAllocationResponse = modelMapper.map(asset, AssetAllocationResponse.class);
            assetAllocationResponse.setAssignedEmployee(employeeDto);
            return assetAllocationResponse;
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error in converting AssetRequest to Asset");
        }
    }
    public AssetReportResponse convertToReportResponse(Asset asset) {
        try {
            return modelMapper.map(asset, AssetReportResponse.class);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error in converting AssetRequest to Asset");
        }
    }
    public AssetReturnResponse convertToReturnResponse(Asset asset) {
        try {
            return modelMapper.map(asset, AssetReturnResponse.class);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error in converting AssetRequest to Asset");
        }
    }



}
