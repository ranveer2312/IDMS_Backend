package com.example.storemanagementbackend.controller;
import com.example.storemanagementbackend.model.Asset;
import com.example.storemanagementbackend.service.AssetService;
import com.example.storemanagementbackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/assets")
public class AssetController {
 
    @Autowired
    private AssetService assetService;
 
    @Autowired
    private EmployeeService employeeService;
 
    //  Get all assets
    @GetMapping
    public List<Asset> getAllAssets() {
        return assetService.getAllAssets();
    }
 
    //  Get assets by category (case-insensitive)
    @GetMapping("/category/{category}")
    public List<Asset> getAssetsByCategory(@PathVariable String category) {
        return assetService.getAssetsByCategory(category);
    }
 
    //  Get assets by status (case-insensitive)
    @GetMapping("/status/{status}")
    public List<Asset> getAssetsByStatus(@PathVariable String status) {
        return assetService.getAssetsByStatus(status);
    }
 
    //  Search assets by assignedTo (e.g. employee name or ID)
    @GetMapping("/search")
    public List<Asset> searchAssetsByAssignedTo(@RequestParam String keyword) {
        return assetService.searchAssetsByAssignedTo(keyword);
    }
 
    // Get all assets assigned to an employee by their numeric (internal) ID
    @GetMapping("/employee/{id}")
    public List<Asset> getAssetsByEmployeeDbId(@PathVariable Long id) {
        var employee = employeeService.getEmployeeById(id);
        return assetService.searchAssetsByAssignedTo(employee.getEmployeeId());
    }
 
    // Add a new asset
    @PostMapping
    public Asset addAsset(@RequestBody Asset asset) {
        return assetService.saveAsset(asset);
    }
 
    // Update existing asset
    @PutMapping("/{id}")
    public Asset updateAsset(@PathVariable Long id, @RequestBody Asset asset) {
        return assetService.updateAsset(id, asset);
    }
 
    //  Delete asset
    @DeleteMapping("/{id}")
    public void deleteAsset(@PathVariable Long id) {
        assetService.deleteAsset(id);
    }
}
 
 
 