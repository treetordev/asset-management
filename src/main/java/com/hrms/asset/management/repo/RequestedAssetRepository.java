package com.hrms.asset.management.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.asset.management.dao.RequestedAsset;

@Repository
public interface RequestedAssetRepository extends JpaRepository<RequestedAsset, Long> {

    List<RequestedAsset> findAllByEmployeeId(Long employeeId);
 
}
