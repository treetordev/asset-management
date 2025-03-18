package com.hrms.asset.management.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.asset.management.dao.AssetClaim;

public interface AssetClaimRepository extends JpaRepository<AssetClaim, Long> {

    List<AssetClaim> findAllByEmployeeId(Long employeeId);
 
}
