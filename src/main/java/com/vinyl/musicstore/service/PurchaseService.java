package com.vinyl.musicstore.service;

import com.vinyl.musicstore.model.Customer;
import com.vinyl.musicstore.model.Purchase;
import com.vinyl.musicstore.model.Vinyl;
import com.vinyl.musicstore.repository.CustomerRepository;
import com.vinyl.musicstore.repository.PurchaseRepository;
import com.vinyl.musicstore.repository.VinylRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final CustomerRepository customerRepository;
    private final VinylRepository vinylRepository;

    public PurchaseService(PurchaseRepository purchaseRepository, CustomerRepository customerRepository, VinylRepository vinylRepository) {
        this.purchaseRepository = purchaseRepository;
        this.customerRepository = customerRepository;
        this.vinylRepository = vinylRepository;
    }

    public Purchase createPurchase(Long customerId, Long vinylId, int quantity) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

        Vinyl vinyl = vinylRepository.findById(vinylId)
                .orElseThrow(() -> new RuntimeException("Vinyl not found with ID: " + vinylId));

        if (vinyl.getStock() < quantity) {
            throw new RuntimeException("Not enough stock available for vinyl: " + vinyl.getTitle());
        }

        vinyl.setStock(vinyl.getStock() - quantity);
        vinylRepository.save(vinyl);

        Purchase purchase = new Purchase(customer, vinyl, quantity, LocalDateTime.now());
        return purchaseRepository.save(purchase);
    }

    public List<Purchase> getPurchasesByCustomer(Long customerId) {
        return purchaseRepository.findByCustomerId(customerId);
    }

    public List<Purchase> getPurchasesByVinyl(Long vinylId) {
        return purchaseRepository.findByVinylId(vinylId);
    }
}
