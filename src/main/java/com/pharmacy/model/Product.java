package com.pharmacy.model;

public class Product {

    private Integer id;
    private String name;
    private String description;
    private Integer categoryId;

    public Product(){
    }
//Constructor
    public Product(String name, String description, Integer categoryId){
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
    }

//Getters
    public String getName(){
        return  name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCategoryId (){
        return categoryId;
    }

    public Integer getId (){
        return  id;
    }

 //Setters
    public void setName (String name) {
        this.name = name;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public void setCategoryId (Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setId (Integer id) {
        this.id = id;
    }
}
