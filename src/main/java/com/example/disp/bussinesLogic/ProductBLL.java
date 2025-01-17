package com.example.disp.bussinesLogic;

import com.example.disp.ConnectionDB.DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    private static final String allProducts = "SELECT * FROM AssignmentTP_Products";
    private static final String addProductQuery = "INSERT INTO AssignmentTP_Products (id, nume, pret, cantitate) VALUES (?, ?, ?, ?)";
    private static final String updateProduct = "UPDATE AssignmentTP_Products SET nume = ?, pret = ?, cantitate = ? WHERE id = ?";
    private static final String deleteProduct = "DELETE FROM AssignmentTP_Products WHERE id = ?";

    private static final String getProductQuery = "SELECT * FROM AssignmentTP_Products WHERE id = ?";
    private static final DataBase database = new DataBase();
    public static ResultSet getAllProducts() {
        return database.exeQuery(allProducts);
    }

    public static void updateProduct(int id, String name, double price, int stock) {
        try {
            DataBase.getConnection().setAutoCommit(false);
            DataBase.exeUpdate(updateProduct, name, price, stock, id);
            DataBase.getConnection().commit();
        } catch (SQLException e) {
            System.out.println("Error updating product: " + e.getMessage());
            try {
                DataBase.getConnection().rollback();
            } catch (SQLException ex) {
                System.out.println("Error rolling back transaction: " + ex.getMessage());
            }
        }
    }

    // Method to delete a product
    public static void deleteProduct(int id) {
        try {
            DataBase.getConnection().setAutoCommit(false);
            DataBase.exeUpdate(deleteProduct, id);
            DataBase.getConnection().commit();
        } catch (SQLException e) {
            System.out.println("Error deleting product: " + e.getMessage());
            try {
                DataBase.getConnection().rollback();
            } catch (SQLException ex) {
                System.out.println("Error rolling back transaction: " + ex.getMessage());
            }
        }
    }

    // Method to add a new product
    public static void addProduct(int id,String name, double price, int stock) {
        try {
            DataBase.getConnection().setAutoCommit(false);
            DataBase.exeUpdate(addProductQuery, id,name, price, stock);
            DataBase.getConnection().commit();
        } catch (SQLException e) {
            System.out.println("Error adding product: " + e.getMessage());
            try {
                DataBase.getConnection().rollback();
            } catch (SQLException ex) {
                System.out.println("Error rolling back transaction: " + ex.getMessage());
            }
        }
    }

    public static ResultSet getProductById(int id){
        ResultSet resultSet = null;
        resultSet = database.exeQuery(getProductQuery, id);
        return resultSet;
    }
}
