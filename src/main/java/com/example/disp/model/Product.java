package com.example.disp.model;
/**
 * a  clas for creating an Object product.
 * this class is also use to generate a line in table to display them
 */
public class Product {
    private String id;
    private String nume;
    private String price;
    private String stock;

    public Product(String id, String nume, String price, String stock) {
        this.id = id;
        this.nume = nume;
        this.price = price;
        this.stock = stock;
    }

    public Product() {
    }

    public String getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getPrice() {
        return price;
    }

    public String getStock() {
        return stock;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", nume='" + nume + '\'' +
                ", price='" + price + '\'' +
                ", stock='" + stock + '\'' +
                '}';
    }
}
