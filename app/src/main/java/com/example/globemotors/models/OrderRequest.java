package com.example.globemotors.models;

import java.io.Serializable;

public class OrderRequest implements Serializable {
    private int quantity;
    private double total;
    private int userId;
    private int productId;

    public OrderRequest(int quantity, double total, int userId, int productId) {
        this.quantity = quantity;
        this.total = total;
        this.userId = userId;
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return total;
    }

    public int getUserId() {
        return userId;
    }

    public int getProductId() {
        return productId;
    }
}
