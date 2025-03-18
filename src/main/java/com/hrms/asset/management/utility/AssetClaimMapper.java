package com.hrms.asset.management.utility;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.hrms.asset.management.dao.AssetClaim;
import com.hrms.asset.management.request.AssetClaimRequest;
import com.hrms.asset.management.response.AssetClaimResponse;

@Component
public class AssetClaimMapper {

    private final ModelMapper modelMapper;

    public AssetClaimMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

    }

    public AssetClaim convertToEntity(AssetClaimRequest assetClaimRequest) {
        try {
            return modelMapper.map(assetClaimRequest, AssetClaim.class);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error in converting AssetRequest to Asset");
        }
    }

    public AssetClaimResponse convertToResponse(AssetClaim assetClaim) {
        try {
            return modelMapper.map(assetClaim, AssetClaimResponse.class);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error in converting AssetRequest to Asset");
        }
    }
    public List<AssetClaimResponse> convertToResponse(List<AssetClaim> assetClaims) {
        try {
            return assetClaims.stream().map(assetClaim -> modelMapper.map(assetClaim, AssetClaimResponse.class)).collect(Collectors.toList());
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error in converting AssetRequest to Asset");
        }
    }
}
