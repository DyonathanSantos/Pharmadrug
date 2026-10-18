package com.pharmacy.model;

import java.math.BigDecimal;

public class Product {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private Integer stock;
    private String image;

// Constructor empty
    public Product(){
    }

//Constructor
    public Product(String name, String description, BigDecimal price, String category, Integer stock, String image){
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.stock = stock;
        this.image = image;
    }

//Getters
    public Integer getId () {return  id;}

    public String getName(){
        return  name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public String getCategory() {return category;}

    public Integer getStock() {return stock;}

    public String getImage() {return image;}

 //Setters
    public void setId (Integer id) {
        this.id = id;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public void setPrice (BigDecimal price) {
        this.price = price;
    }

    public void setCategory (String category) {
        this.category = category;
    }

    public void setStock (Integer stock) {
        this.stock = stock;
    }

    public void setImage (String image) {
        this.image = image;
    }
}
