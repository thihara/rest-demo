/**
 * Created by thihara on 2/13/16.
 */
package com.restdemo.domain;

import java.util.Set;

public class Company {

    private Long id;
    private String name;
    private String address;
    private String city;
    private String country;
    private String email;
    private String phoneNumber;
    private Set<String> owners;

    public Company(){

    }

    private Company(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.address = company.getAddress();
        this.city = company.getCity();
        this.country = company.getCountry();
        this.email = company.getEmail();
        this.phoneNumber = company.getPhoneNumber();
        this.owners = company.getOwners();
    }

    public static Company fromCompany(Company company){
        return company != null ? new Company(company) : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        return id == company.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<String> getOwners() {
        return owners;
    }

    public void setOwners(Set<String> owners) {
        this.owners = owners;
    }
}
