package com.example.disp.bussinesLogic;

public class ClientBLL {
    private int id;
    private String nume;
    private String adresa;
    private String email;

    public ClientBLL(int id, String nume, String adresa, String email) {
        this.id = id;
        this.nume = nume;
        this.adresa = adresa;
        this.email = email;
    }

    public int getId() {
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

    public void setId(int id) {
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
