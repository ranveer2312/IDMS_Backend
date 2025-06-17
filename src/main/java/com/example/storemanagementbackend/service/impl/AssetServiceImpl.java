package com.example.storemanagementbackend.service.impl;
import com.example.storemanagementbackend.dto.AssetDTO;
import com.example.storemanagementbackend.model.Asset;
import com.example.storemanagementbackend.repository.AssetRepository;
import com.example.storemanagementbackend.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.Optional;
 
@Service
public class AssetServiceImpl implements AssetService {
 
    @Autowired
    private AssetRepository assetRepository;
 
    @Override
    public Asset saveAsset(Asset asset) {
        return assetRepository.save(asset);
    }
 
    @Override
    public Optional<Asset> getAssetById(Long id) {
        return assetRepository.findById(id);
    }
 
    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }
 
    @Override
    public List<Asset> getAssetsByCategory(String category) {
        return assetRepository.findByCategoryIgnoreCase(category);
    }
 
    @Override
    public List<Asset> getAssetsByStatus(String status) {
        return assetRepository.findByStatusIgnoreCase(status);
    }
 
    @Override
    public List<Asset> searchAssetsByAssignedTo(String assignedTo) {
        return assetRepository.findByAssignedToContainingIgnoreCase(assignedTo);
    }
 
    @Override
    public Asset updateAsset(Long id, Asset updatedAsset) {
        return assetRepository.findById(id).map(existingAsset -> {
            existingAsset.setAssetName(updatedAsset.getAssetName());
            existingAsset.setSerialNumber(updatedAsset.getSerialNumber());
            existingAsset.setCategory(updatedAsset.getCategory());
            existingAsset.setAssignedTo(updatedAsset.getAssignedTo());
            existingAsset.setStatus(updatedAsset.getStatus());
            existingAsset.setCondition(updatedAsset.getAssetcondition());
            return assetRepository.save(existingAsset);
        }).orElseThrow(() -> new RuntimeException("Asset not found with id " + id));
    }
 
    @Override
    public void deleteAsset(Long id) {
        assetRepository.deleteById(id);
    }
}
 
 
 