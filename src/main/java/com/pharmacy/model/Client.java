/*
 * ORIGEM / CONTRIBUIÇÃO: BASE PHARMACY — modelo de cliente do projeto principal; mantido como Client para compatibilidade com os serviços e repositórios existentes.
 * Observação: este comentário registra a procedência do código integrado; não identifica autoria individual.
 */
package com.pharmacy.model;

import java.io.Serializable;

public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String email;
    private String passwordHash;
    private Role cargo = Role.CLIENTE;


    //construtor vazio
    public Client(){
    }

    //Construtro com valores
    public Client(String name, String email, String passwordHash) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.cargo = Role.CLIENTE;
    }


    //Getters
    public Integer getId() {return id;}

    public String getName() {return name;}

    public String getEmail() {return email;}

    public String getPasswordHash() {return passwordHash;}

    public Role getCargo() {return cargo;}


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

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setCargo(Role cargo) {
        this.cargo = cargo;
    }
}
