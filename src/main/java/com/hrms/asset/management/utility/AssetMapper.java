package com.hrms.asset.management.utility;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.hrms.asset.management.dao.Asset;
import com.hrms.asset.management.request.AssetRequest;
import com.hrms.asset.management.response.AssetResponse;

@Component
public class AssetMapper {
    private final ModelMapper modelMapper;

    public AssetMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
      
    }
    public Asset convertToEntity(AssetRequest assetRequest) {
        try {
            return modelMapper.map(assetRequest, Asset.class);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error in converting AssetRequest to Asset");
        }
    }
    public AssetResponse convertToResponse(Asset asset) {
        try {
            return modelMapper.map(asset, AssetResponse.class);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error in converting AssetRequest to Asset");
        }
    }
    public List<AssetResponse> convertToResponse(List<Asset> assets) {
        try {
            return assets.stream().map(asset -> modelMapper.map(asset, AssetResponse.class)).collect(Collectors.toList());
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error in converting AssetRequest to Asset");
        }
    }
}
