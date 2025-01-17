package com.example.disp.model;

/**
 * a  clas for creating an Object customer.
 * this class is also use to generate a line in table to display them
 */
public class Customer {
    private String id;
    private String nume;
    private String adresa;
    private String email;

    public Customer(String id, String nume, String adresa, String email) {
        this.id = id;
        this.nume = nume;
        this.adresa = adresa;
        this.email = email;
    }

    public Customer() {
    }

    public String getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getEmail() {
        return email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
