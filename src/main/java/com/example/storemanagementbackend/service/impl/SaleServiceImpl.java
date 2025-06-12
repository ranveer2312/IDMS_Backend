package com.example.storemanagementbackend.service.impl;

import com.example.storemanagementbackend.model.Sale;
import com.example.storemanagementbackend.repository.SaleRepository;
import com.example.storemanagementbackend.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Override
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    @Override
    public Sale getSaleById(Long id) {
        Optional<Sale> sale = saleRepository.findById(id);
        return sale.orElse(null);
    }

    @Override
    public Sale createSale(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public Sale updateSale(Long id, Sale saleDetails) {
        Sale sale = saleRepository.findById(id).orElse(null);
        if (sale != null) {
            sale.setCustomer(saleDetails.getCustomer());
            sale.setAmount(saleDetails.getAmount());
            sale.setDate(saleDetails.getDate());
            sale.setStatus(saleDetails.getStatus());
            sale.setPaymentStatus(saleDetails.getPaymentStatus());
            sale.setPaymentMethod(saleDetails.getPaymentMethod());
            return saleRepository.save(sale);
        }
        return null;
    }

    @Override
    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }
} 