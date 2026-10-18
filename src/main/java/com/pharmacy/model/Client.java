package com.pharmacy.model;

import org.jetbrains.kotlin.it.unimi.dsi.fastutil.Hash;

public class Client {

    private Integer id;
    private String name;
    private String email;
    private Integer password;


    //construtor vazio
    public Client(){
    }

    //Construtro com valores
    public Client(String name, String email, Integer password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }


    //Getters
    public Integer getId() {return id;}

    public String getName() {return name;}

    public String getEmail() {return email;}

    public Integer getPassword() {return password;}


    //Setters
    public void  setId(Integer id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }
}
