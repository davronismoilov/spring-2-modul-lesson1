package com.davron.company.dto;

import com.davron.company.entity.Address;

public class CompanyDto  {
    private Long id;
    private String companyName;
    private String directorName;
    private Address address;

    public CompanyDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getDirectorName() {
        return this.directorName;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return this.address;
    }
}