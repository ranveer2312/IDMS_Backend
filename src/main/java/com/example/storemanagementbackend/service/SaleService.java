package com.example.storemanagementbackend.service;

import com.example.storemanagementbackend.model.Sale;
import java.util.List;

public interface SaleService {
    List<Sale> getAllSales();
    Sale getSaleById(Long id);
    Sale createSale(Sale sale);
    Sale updateSale(Long id, Sale saleDetails);
    void deleteSale(Long id);
} 