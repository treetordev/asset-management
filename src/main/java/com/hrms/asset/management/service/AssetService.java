package com.hrms.asset.management.service;

import java.time.LocalDate;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.asset.management.dao.Asset;
import com.hrms.asset.management.dao.AssetAllocation;
import com.hrms.asset.management.dao.AssetClaim;
import com.hrms.asset.management.repo.AssetAllocationRepository;
import com.hrms.asset.management.repo.AssetClaimRepository;
import com.hrms.asset.management.repo.AssetRepository;
import com.hrms.asset.management.request.AssetClaimRequest;
import com.hrms.asset.management.request.AssetRequest;
import com.hrms.asset.management.response.ApiResponse;
import com.hrms.asset.management.response.AssetClaimResponse;
import com.hrms.asset.management.response.AssetResponse;
import com.hrms.asset.management.utility.AssetClaimMapper;
import com.hrms.asset.management.utility.AssetMapper;

import jakarta.transaction.Transactional;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private AssetAllocationRepository assetAllocationRepository;

    @Autowired
    private AssetMapper assetMapper;

    @Autowired
    private AssetClaimRepository assetClaimRepository;

    @Autowired
    private AssetClaimMapper assetClaimMapper;

    public AssetResponse addAsset(AssetRequest assetRequest) {

        try {
            Asset asset = assetMapper.convertToEntity(assetRequest);
            assetRepository.save(asset);
            return assetMapper.convertToResponse(asset);
        } catch (Exception e) {
            throw new RuntimeErrorException(null, "Error in saving asset");
        }
    }

    @Transactional
    public ApiResponse allocateAsset(Long assetId, Long employeeId) {

        try {
            Asset asset = assetRepository.findById(assetId).get();
            AssetAllocation assetAllocation = new AssetAllocation();
            assetAllocation.setEmployeeId(employeeId);
            assetAllocation.setAllocationDate(LocalDate.now());
            assetAllocationRepository.save(assetAllocation);

            asset.setAssigned(true);
            asset.setAssetAllocation(assetAllocation);
            assetRepository.save(asset);
            return new ApiResponse("Asset allocated successfully", true);
        } catch (Exception e) {
            throw new RuntimeErrorException(null, "Error in allocating asset");
        }

    }

    public AssetResponse updateAsset(AssetRequest assetRequest) {

        try {
            Asset asset = assetMapper.convertToEntity(assetRequest);
            Asset assetData = assetRepository.findById(asset.getId()).get();
            assetData.setName(asset.getName());
            assetData.setType(asset.getType());
            assetData.setSerialNumber(asset.getSerialNumber());
            assetRepository.save(assetData);
            return assetMapper.convertToResponse(assetData);
        } catch (Exception e) {
            throw new RuntimeException("Error in saving asset",e);
        }
    }

    public List<AssetResponse> getAllAssignedAsset() {
        try {
            List<Asset> assets = assetRepository.findByIsAssigned(true);
            return assetMapper.convertToResponse(assets);
        } catch (Exception e) {
            throw new RuntimeException( "Error in fetching all assigned assets",e);
        }

    }

    public List<AssetResponse> getAllUnassignedAsset() {

        try {
            List<Asset> assets = assetRepository.findByIsAssigned(false);
            return assetMapper.convertToResponse(assets);
        } catch (Exception e) {
            throw new RuntimeException( "Error in fetching all unassigned assets",e);
        }

    }

    public AssetResponse updateAssetStatus(AssetRequest assetRequest) {

        try {
            Asset asset = assetMapper.convertToEntity(assetRequest);
            Asset assetData = assetRepository.findById(asset.getId()).get();
            assetData.setIsActive(asset.getIsActive());
            assetRepository.save(assetData);
            return assetMapper.convertToResponse(assetData);
        } catch (Exception e) {
            throw new RuntimeException("Error in saving asset",e);
        }
    }

   
    public AssetClaimResponse claimAsset(AssetClaimRequest assetClaimRequest) {
        try {
            AssetClaim assetClaim = assetClaimMapper.convertToEntity(assetClaimRequest);

            assetClaim.setStatus("Pending");
            assetClaim.setClaimDate(LocalDate.now());
            assetClaimRepository.save(assetClaim);
            return assetClaimMapper.convertToResponse(assetClaim);
        } catch (Exception e) {
          throw new RuntimeException("Error in saving asset claim: " + e.getMessage(), e);
        }
    }

    public List<AssetClaimResponse> getAllClaimedAsset(Long employeeId) {
        try {
            List<AssetClaim> assetClaims = assetClaimRepository.findAllByEmployeeId(employeeId);
            return assetClaimMapper.convertToResponse(assetClaims);
        } catch (Exception e) {
            throw new RuntimeException("Error in fetching all claimed assets",e);
        }
    }

}
