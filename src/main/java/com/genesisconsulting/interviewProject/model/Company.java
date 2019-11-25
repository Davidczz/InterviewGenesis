package com.genesisconsulting.interviewProject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Company {
    private @Id @GeneratedValue Long id;
    private String name;
    private String tvaNumber;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Address> addresses;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Contact> employees;

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

    public String getTvaNumber() {
        return tvaNumber;
    }

    public void setTvaNumber(String tvaNumber) {
        this.tvaNumber = tvaNumber;
    }

    public Collection<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Collection<Address> addresses) {
        this.addresses = addresses;
    }

    public Collection<Contact> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Contact> employees) {
        this.employees = employees;
    }
}

