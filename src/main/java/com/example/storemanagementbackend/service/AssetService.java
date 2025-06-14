package com.example.storemanagementbackend.service;


import com.example.storemanagementbackend.dto.AssetDTO;
import com.example.storemanagementbackend.model.Asset;

import java.util.List;
import java.util.Optional;

public interface AssetService {

    Asset saveAsset(Asset asset);

    Optional<Asset> getAssetById(Long id);

    List<Asset> getAllAssets();

    List<Asset> getAssetsByCategory(String category);

    List<Asset> getAssetsByStatus(String status);

    List<Asset> searchAssetsByAssignedTo(String assignedTo);

    Asset updateAsset(Long id, Asset assetDetails);

    void deleteAsset(Long id);
}

