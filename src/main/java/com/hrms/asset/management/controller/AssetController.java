package com.hrms.asset.management.controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hrms.asset.management.request.AssetClaimRequest;
import com.hrms.asset.management.request.AssetRequest;
import com.hrms.asset.management.response.ApiResponse;
import com.hrms.asset.management.response.AssetClaimResponse;
import com.hrms.asset.management.response.AssetResponse;
import com.hrms.asset.management.service.AssetService;

@RestController
@RequestMapping("/api/asset")
public class AssetController {

    @Autowired
    private AssetService assetService;

    /* Asset  */
    @PostMapping("/")
    public ResponseEntity<AssetResponse> addAsset(@Valid @RequestBody AssetRequest assetRequest) {

        try {
            AssetResponse assetResponse = assetService.addAsset(assetRequest);
            if (assetResponse != null) {
                return ResponseEntity.ok(assetResponse);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/")
    public ResponseEntity<AssetResponse> updateAsset(@Valid @RequestBody AssetRequest assetRequest) {

        AssetResponse assetResponse = assetService.updateAsset(assetRequest);
        if (assetResponse != null) {
            return ResponseEntity.ok(assetResponse);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/assigned")
    public ResponseEntity<List<AssetResponse>> getAllAssignedAsset() {

        try {
            List<AssetResponse> assetResponse = assetService.getAllAssignedAsset();
            if (assetResponse != null) {
                return ResponseEntity.ok(assetResponse);
            }

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/unassigned")
    public ResponseEntity<List<AssetResponse>> getAllUnassignedAsset() {

        try {
            List<AssetResponse> assetResponse = assetService.getAllUnassignedAsset();
            if (assetResponse != null) {
                return ResponseEntity.ok(assetResponse);
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.badRequest().build();
    }

    /* Asset Allocation */

    @PostMapping("/allocation")
    public ResponseEntity<ApiResponse> allocateAsset(@Valid @RequestParam Long assetId,
            @Valid @RequestParam Long employeeId) {

        try {
            ApiResponse apiResponse = assetService.allocateAsset(assetId, employeeId);
            if (apiResponse.isStatus()) {
                return ResponseEntity.ok(apiResponse);
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.badRequest().build();
    }


    /* Retire damaged Asset */

    @PatchMapping("/retire")
    public ResponseEntity<AssetResponse> updateAssetStatus(@Valid @RequestBody AssetRequest assetRequest) {

        AssetResponse assetResponse = assetService.updateAssetStatus(assetRequest);
        if (assetResponse != null) {
            return ResponseEntity.ok(assetResponse);
        }
        return ResponseEntity.badRequest().build();
    }

   
    /* Asset Claim  */

    @PostMapping("/claim")
    public ResponseEntity<AssetClaimResponse> claimAsset(@Valid @RequestBody AssetClaimRequest assetClaimRequest) {

        try {
            AssetClaimResponse assetRespone = assetService.claimAsset(assetClaimRequest);
            if (assetRespone != null) {
                return ResponseEntity.ok(assetRespone);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/claim")
    public ResponseEntity<List<AssetClaimResponse>> getAllClaimedAsset(@RequestParam Long employeeId) {

        try {
            List<AssetClaimResponse> assetResponse = assetService.getAllClaimedAsset(employeeId);
            if (assetResponse != null) {
                return ResponseEntity.ok(assetResponse);
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
