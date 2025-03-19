package com.hrms.asset.management.controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import com.hrms.asset.management.request.AssetClaimRequest;
import com.hrms.asset.management.request.AssetRequest;
import com.hrms.asset.management.response.AssetClaimResponse;
import com.hrms.asset.management.response.AssetResponse;
import com.hrms.asset.management.service.AssetService;

@RestController
@RequestMapping("/asset")
public class AssetController {

    @Autowired
    private AssetService assetService;

    /* Asset */
    @PostMapping
    public ResponseEntity<AssetResponse> addAsset(@Valid @RequestBody AssetRequest assetRequest) {
        AssetResponse asset = assetService.addAsset(assetRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(asset);
    }

    @PutMapping
    public ResponseEntity<AssetResponse> updateAsset(@Valid @RequestBody AssetRequest assetRequest) {

        AssetResponse updatedAsset = assetService.updateAsset(assetRequest);

        return ResponseEntity.status(HttpStatus.OK).body(updatedAsset);

    }

    @GetMapping("/assigned")
    public ResponseEntity<List<AssetResponse>> getAllAssignedAsset() {

        List<AssetResponse> assetResponse = assetService.getAllAssignedAsset();
        if (assetResponse != null) {
            return ResponseEntity.ok(assetResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/unassigned")
    public ResponseEntity<List<AssetResponse>> getAllUnassignedAsset() {

        List<AssetResponse> assetResponse = assetService.getAllUnassignedAsset();
        if (assetResponse != null) {
            return ResponseEntity.ok(assetResponse);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    /* Asset Allocation */

    @PostMapping("/allocation")
    public ResponseEntity<?> allocateAsset(@Valid @RequestParam Long assetId,
            @Valid @RequestParam Long employeeId) {

        assetService.allocateAsset(assetId, employeeId);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    /* Retire damaged Asset */

    @PatchMapping("/retire/{id}")
    public ResponseEntity<AssetResponse> retireAsset(@PathVariable Long assetId, @RequestParam Boolean isAactive) {

        AssetResponse assetResponse = assetService.retireAsset(assetId, isAactive);
        return ResponseEntity.status(HttpStatus.OK).body(assetResponse);

    }

    /* Asset Claim */

    @PostMapping("/claim")
    public ResponseEntity<AssetClaimResponse> claimAsset(@Valid @RequestBody AssetClaimRequest assetClaimRequest) {

        AssetClaimResponse assetRespone = assetService.claimAsset(assetClaimRequest);
        return ResponseEntity.status(HttpStatus.OK).body(assetRespone);

    }

    @GetMapping("/claim")
    public ResponseEntity<List<AssetClaimResponse>> getAllClaimedAsset(@RequestParam Long employeeId) {

        List<AssetClaimResponse> assetResponse = assetService.getAllClaimedAsset(employeeId);
        if (assetResponse != null) {
            return ResponseEntity.ok(assetResponse);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
