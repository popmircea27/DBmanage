package com.example.disp.bussinesLogic;

public class ProductBLL {
    private int id;
    private String nume;
    private float price;
    private int stock;

    public ProductBLL(int id, String nume, float price, int stock) {
        this.id = id;
        this.nume = nume;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public float getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
