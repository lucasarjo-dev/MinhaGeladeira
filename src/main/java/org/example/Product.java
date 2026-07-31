package org.example;

public class Product {

    private int id;
    private String name;
    private String unit;
    private int categoryId;
    private double quantity;


    public void setName(String name){
        this.name = name;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setCategoryId(int categoryId){
        this.categoryId = categoryId;
    }
    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }
    public int getCategoryId(){
        return categoryId;
    }

    public void setQuantity(double quantity){
        this.quantity = quantity;
    }

    public double getQuantity(){
        return quantity;
    }
}
