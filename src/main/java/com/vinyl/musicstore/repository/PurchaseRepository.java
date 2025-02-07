package com.vinyl.musicstore.repository;

import com.vinyl.musicstore.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByCustomerId(Long customerId);
    List<Purchase> findByVinylId(Long vinylId);
}
