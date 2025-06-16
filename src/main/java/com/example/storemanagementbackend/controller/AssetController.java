package com.example.storemanagementbackend.controller;
 
import com.example.storemanagementbackend.model.Asset;
import com.example.storemanagementbackend.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@RestController
@CrossOrigin(origins = "http://localhost:3000") // React frontend port
@RequestMapping("/api/assets")
public class AssetController {
 
    @Autowired
    private AssetService assetService;
 
    @GetMapping
    public List<Asset> getAllAssets() {
        return assetService.getAllAssets();
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable Long id) {
        return assetService.getAssetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
 
    @PostMapping
    public Asset saveAsset(@RequestBody Asset asset) {
        return assetService.saveAsset(asset);
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable Long id, @RequestBody Asset asset) {
        try {
            Asset updatedAsset = assetService.updateAsset(id, asset);
            return ResponseEntity.ok(updatedAsset);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsset(@PathVariable Long id) {
        assetService.deleteAsset(id);
        return ResponseEntity.ok().build();
    }
}
 
 