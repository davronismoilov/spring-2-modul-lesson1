package com.davron.company.dto;

public class AddressDto  {
    private Long id;
    private String street;
    private String homeNumber;

    public AddressDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return this.street;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getHomeNumber() {
        return this.homeNumber;
    }
}