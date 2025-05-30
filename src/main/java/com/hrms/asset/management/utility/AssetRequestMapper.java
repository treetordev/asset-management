package com.hrms.asset.management.utility;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.hrms.asset.management.dao.RequestedAsset;
import com.hrms.asset.management.request.AssetRequestSubmission;
import com.hrms.asset.management.response.AssetRequestResponse;
import com.hrms.asset.management.response.EmployeeDto;

@Component
public class AssetRequestMapper {

    private final ModelMapper modelMapper;

    public AssetRequestMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

    }

    public RequestedAsset convertToEntity(AssetRequestSubmission assetClaimRequest) {
        try {
            return modelMapper.map(assetClaimRequest, RequestedAsset.class);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error in converting AssetRequest to Asset");
        }
    }

    public AssetRequestResponse convertToResponse(RequestedAsset assetClaim) {
        try {
            return modelMapper.map(assetClaim, AssetRequestResponse.class);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error in converting AssetRequest to Asset");
        }
    }
    public AssetRequestResponse convertToResponse(RequestedAsset assetClaim, EmployeeDto employeeDto) {
        try {
            AssetRequestResponse response = modelMapper.map(assetClaim, AssetRequestResponse.class);
            response.setEmployeeDto(employeeDto);
            return response;
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error in converting AssetRequest to Asset");
        }
    }
    public List<AssetRequestResponse> convertToResponse(List<RequestedAsset> assetClaims) {
        try {
            return assetClaims.stream().map(assetClaim -> modelMapper.map(assetClaim, AssetRequestResponse.class)).collect(Collectors.toList());
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error in converting AssetRequest to Asset");
        }
    }
    public List<AssetRequestResponse> convertToResponseWithEmployee(List<RequestedAsset> assetClaims,EmployeeDto employeeDto) {
        try {
            return assetClaims.stream().map(assetClaim -> {
                AssetRequestResponse response = modelMapper.map(assetClaim, AssetRequestResponse.class);
                response.setEmployeeDto(employeeDto);
                return response;
            }).collect(Collectors.toList());
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error in converting AssetRequest to Asset");
        }
    }
}
