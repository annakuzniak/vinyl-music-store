package com.vinyl.musicstore.controller;

import com.vinyl.musicstore.dto.PurchaseRequest;
import com.vinyl.musicstore.model.Purchase;
import com.vinyl.musicstore.service.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public ResponseEntity<Purchase> createPurchase(@RequestBody PurchaseRequest request) {
        Purchase purchase = purchaseService.createPurchase(request.getCustomerId(), request.getVinylId(), request.getQuantity());
        return ResponseEntity.ok(purchase);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Purchase>> getPurchasesByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(purchaseService.getPurchasesByCustomer(customerId));
    }

    @GetMapping("/vinyl/{vinylId}")
    public ResponseEntity<List<Purchase>> getPurchasesByVinyl(@PathVariable Long vinylId) {
        return ResponseEntity.ok(purchaseService.getPurchasesByVinyl(vinylId));
    }
}
