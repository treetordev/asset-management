package com.hrms.asset.management.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.asset.management.dao.Asset;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

    List<Asset> findByIsAssignedAndActiveTrue(boolean isAssigned);

}
