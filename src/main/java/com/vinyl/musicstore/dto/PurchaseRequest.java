package com.vinyl.musicstore.dto;

public class PurchaseRequest {
    private Long customerId;
    private Long vinylId;
    private int quantity;

    public PurchaseRequest() {}

    public PurchaseRequest(Long customerId, Long vinylId, int quantity) {
        this.customerId = customerId;
        this.vinylId = vinylId;
        this.quantity = quantity;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getVinylId() {
        return vinylId;
    }

    public void setVinylId(Long vinylId) {
        this.vinylId = vinylId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

