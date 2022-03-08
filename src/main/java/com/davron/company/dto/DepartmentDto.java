package com.davron.company.dto;

import com.davron.company.entity.Company;

public class DepartmentDto {
    private Long id;
    private String name;
    private Company company;

    public DepartmentDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Company getCompany() {
        return this.company;
    }
}