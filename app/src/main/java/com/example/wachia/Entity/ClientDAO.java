package com.example.wachia.Entity;

public class ClientDAO {
    private String IdNumber;
    private String PhoneNumber;
    private String CounterNumber;

    private String Email;
    private String Description;


    public ClientDAO() {
    }

    public String getCounterNumber() {
        return CounterNumber;
    }

    public void setCounterNumber(String counterNumber) {
        CounterNumber = counterNumber;
    }



    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
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
