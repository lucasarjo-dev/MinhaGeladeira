package org.example.model;

public class Stock {
    private int id;
    private int productId;
    private double quantity;

    public void setId(int id) {
        this.id = id;
    }

    public void setProductId(int productId){
        this.productId = productId;
    }

    public void setQuantity(double quantity){
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getProductId() {
        return productId;
    }

    public double getQuantity() {
        return quantity;
    }
}