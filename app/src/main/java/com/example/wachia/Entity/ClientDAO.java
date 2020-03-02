package com.example.wachia.Entity;

public class ClientDAO {
    private int IdNumber;
    private int PhoneNumber;
    private String Email;
    private String Description;


    public ClientDAO() {
    }

    public int getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(int idNumber) {
        IdNumber = idNumber;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
