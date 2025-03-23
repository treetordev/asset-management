package com.hrms.asset.management.utility;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.hrms.asset.management.dao.RequestedAsset;
import com.hrms.asset.management.request.AssetRequestSubmission;
import com.hrms.asset.management.response.AssetRequestResponse;

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
    public List<AssetRequestResponse> convertToResponse(List<RequestedAsset> assetClaims) {
        try {
            return assetClaims.stream().map(assetClaim -> modelMapper.map(assetClaim, AssetRequestResponse.class)).collect(Collectors.toList());
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error in converting AssetRequest to Asset");
        }
    }
}
