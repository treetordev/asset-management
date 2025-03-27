package com.hrms.asset.management.service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.hrms.asset.management.dao.Asset;
import com.hrms.asset.management.dao.Employee;
import com.hrms.asset.management.dao.RequestedAsset;
import com.hrms.asset.management.repo.AssetRepository;
import com.hrms.asset.management.repo.EmployeeRepository;
import com.hrms.asset.management.repo.RequestedAssetRepository;
import com.hrms.asset.management.request.AssetAllocationRequest;
import com.hrms.asset.management.request.AssetReportRequest;
import com.hrms.asset.management.request.AssetRequest;
import com.hrms.asset.management.request.AssetRequestSubmission;
import com.hrms.asset.management.request.AssetReturnRequest;
import com.hrms.asset.management.response.AssetAllocationResponse;
import com.hrms.asset.management.response.AssetReportResponse;
import com.hrms.asset.management.response.AssetRequestResponse;
import com.hrms.asset.management.response.AssetResponse;
import com.hrms.asset.management.response.AssetReturnResponse;
import com.hrms.asset.management.utility.AssetMapper;
import com.hrms.asset.management.utility.AssetRequestMapper;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private RequestedAssetRepository assetClaimRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AssetMapper assetMapper;

    @Autowired
    private AssetRequestMapper assetClaimMapper;

    public AssetResponse addAsset(AssetRequest assetRequest) {

        try {
            Asset asset = assetMapper.convertToEntity(assetRequest);
            Asset savedAsset = assetRepository.save(asset);
            asset.setStatus("Unassigned");
            return assetMapper.convertToResponse(savedAsset);
        } catch (Exception e) {
            throw new RuntimeException("Error in saving asset: " + e.getMessage(), e);
        }
    }

    public AssetResponse updateAsset(Long id, AssetRequest assetRequest) {

        try {
            Asset existingAsset = assetRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Asset not found with ID: " + id));
            Asset asset = assetMapper.convertToEntity(assetRequest);

            copyNonNullProperties(asset, existingAsset);

            Asset updateAsset = assetRepository.save(existingAsset);
            return assetMapper.convertToResponse(updateAsset);
        } catch (Exception e) {
            throw new RuntimeException("Error in saving asset: " + e.getMessage(), e);
        }
    }

    public AssetAllocationResponse allocateAsset(Long id, AssetAllocationRequest assetRequest) {

        try {
            Asset existingAsset = assetRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Asset not found with ID: " + id));
            existingAsset.setAssignedEmployeeId(assetRequest.getEmployeeId());
            existingAsset.setStatus("Assigned");
            existingAsset.setAllocationDate(assetRequest.getAllocationDate());

            Asset updateAsset = assetRepository.save(existingAsset);
            return assetMapper.convertToAllocationResponse(updateAsset);
        } catch (Exception e) {
            throw new RuntimeException("Error in saving asset: " + e.getMessage(), e);
        }
    }

    public AssetReportResponse reportAsset(Long id, AssetReportRequest assetReportRequest) {

        try {
            Asset existingAsset = assetRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Asset not found with ID: " + id));

            existingAsset.setReportDate(assetReportRequest.getReportingDate());
            existingAsset.setReportDescription(assetReportRequest.getReportingDescription());
            existingAsset.setStatus(assetReportRequest.getStatus());

            Asset updateAsset = assetRepository.save(existingAsset);
            return assetMapper.convertToReportResponse(updateAsset);
        } catch (Exception e) {
            throw new RuntimeException("Error in saving asset: " + e.getMessage(), e);
        }
    }

    public AssetReturnResponse returnAsset(Long id, AssetReturnRequest assetRequest) {

        try {
            Asset existingAsset = assetRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Asset not found with ID: " + id));
            existingAsset.setAssignedEmployeeId(null);
            existingAsset.setStatus(assetRequest.getStatus());
            existingAsset.setAllocationDate(null);
            Asset updateAsset = assetRepository.save(existingAsset);
            return assetMapper.convertToReturnResponse(updateAsset);
        } catch (Exception e) {
            throw new RuntimeException("Error in saving asset: " + e.getMessage(), e);
        }
    }

    public List<AssetResponse> getAllAsset(String status, String assetType) {
        try {
            Specification<Asset> spec = Specification.where(null);
            if (status != null) {
                spec = spec.and((root, query, cb) -> cb.equal(root.get("status"), status));
            }
            // if (status.equals("unassigned")) {
            // spec = spec.and((root, query, cb) -> cb.equal(root.get("isAllocated"),
            // false));
            // }
            if (assetType != null) {
                spec = spec.and((root, query, cb) -> cb.equal(root.get("type"), assetType));
            }
            return assetRepository.findAll(spec).stream().map(assetMapper::convertToResponse)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Error in fetching all assigned assets: " + e.getMessage(), e);
        }

    }

    public AssetRequestResponse requestAsset(Long employeeId, AssetRequestSubmission assetClaimRequest) {
        try {
            RequestedAsset requestedAsset = assetClaimMapper.convertToEntity(assetClaimRequest);
            requestedAsset.setEmployeeId(employeeId);
            requestedAsset.setStatus("Pending");
            requestedAsset.setDate(LocalDate.now());
            assetClaimRepository.save(requestedAsset);
            return assetClaimMapper.convertToResponse(requestedAsset);
        } catch (Exception e) {
            throw new RuntimeException("Error in saving asset request: " + e.getMessage(), e);
        }
    }

    // Assigned and Unassigned field we have to add there to fetch data
    public List<AssetRequestResponse> getAllRequestedAsset(Long employeeId) {
        try {
            List<RequestedAsset> assetClaims = assetClaimRepository.findAllByEmployeeId(employeeId);
            return assetClaimMapper.convertToResponse(assetClaims);
        } catch (Exception e) {
            throw new RuntimeException("Error in fetching all requested assets: " + e.getMessage(), e);
        }
    }

    public void copyNonNullProperties(Object src, Object target) {
        Field[] fields = src.getClass().getDeclaredFields();
        Field[] var3 = fields;
        int var4 = fields.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            Field field = var3[var5];
            field.setAccessible(true);

            try {
                Object value = field.get(src);
                if (value != null) {
                    field.set(target, value);
                }
            } catch (IllegalAccessException var8) {
                System.out.println("exception while copying the object values");
            }
        }

    }

}
