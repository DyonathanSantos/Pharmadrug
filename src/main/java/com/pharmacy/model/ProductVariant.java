package com.pharmacy.model;

import java.math.BigDecimal;

public class ProductVariant {

    private Integer id;
    private Integer productId;
    private Integer quantity;
    private Integer unitId;
    private BigDecimal price;
    private Integer stock;
    private String image;


 //Constructor
    public ProductVariant(){
    }

    public ProductVariant(Integer productId, Integer quantity, Integer unitId, BigDecimal price, Integer stock, String image){
        this.productId = productId;
        this.quantity = quantity;
        this.unitId = unitId;
        this.price = price;
        this.stock = stock;
        this.image = image;
    }


// Getters
    public Integer getId(){
        return id;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public String getImage() {
        return image;
    }

 //Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setQuantity (Integer quantity) {
        this.quantity = quantity;
    }

    public void setUnitId (Integer unitId) {
        this.unitId = unitId;
    }

    public void setPrice (BigDecimal price){
        this.price = price;
    }

    public void setStock (Integer stock) {
        this.stock = stock;
    }

    public void setImage (String image) {
        this.image = image;
    }
}
