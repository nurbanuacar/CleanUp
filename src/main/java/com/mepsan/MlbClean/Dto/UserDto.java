/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mepsan.MlbClean.Dto;

/**
 *
 * @author nurbanu.acar
 */
public class UserDto {

    private int id;
    private String name;
    private String surname;
    private String username;
    private boolean isAdmin;

    public UserDto() {
    }

    public UserDto(int id, String name, String surname, String username, boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.isAdmin = isAdmin;
    }

    public UserDto(String name, String surname, String username, boolean isAdmin) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.isAdmin = isAdmin;
    }

    public UserDto(String name, String surname, String username) {
        this.name = name;
        this.surname = surname;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

}
