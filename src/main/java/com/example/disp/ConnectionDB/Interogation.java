package com.example.disp.ConnectionDB;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * in this class, after i get the connection to the server, here i genereate and execute the querys
 */

public class Interogation {

    /**
        for customers;
     **/
    private static final String allCustomers = "SELECT * FROM Customers_ORDERS_MANAGEMENT";
    private static final String addCustomerQuery = "INSERT INTO Customers_ORDERS_MANAGEMENT (name, address, email) VALUES (?, ?, ?)";
    private static final String updateCustomer = "UPDATE Customers_ORDERS_MANAGEMENT SET name = ?, address = ?, email = ? WHERE id = ?";
    private static final String deleteCustomer = "DELETE FROM Customers_ORDERS_MANAGEMENT WHERE id = ?";

    /**
        for products;
    **/
    private static final String allProducts = "SELECT * FROM AssignmentTP_Products";
    private static final String addProductQuery = "INSERT INTO AssignmentTP_Products (id, nume, pret, cantitate) VALUES (?, ?, ?, ?)";
    private static final String updateProduct = "UPDATE AssignmentTP_Products SET nume = ?, pret = ?, cantitate = ? WHERE id = ?";
    private static final String deleteProduct = "DELETE FROM AssignmentTP_Products WHERE id = ?";



    /**
        for creating an order;
     **/

    private static final String customerNameQuery = "SELECT name FROM Customers_ORDERS_MANAGEMENT WHERE id = ?";
    private static final String getProductQuery = "SELECT * FROM AssignmentTP_Products WHERE id = ?";

    static String insertBillQuery = "INSERT INTO BILL_TP_ASSIGNMENT (BILL_ID, buyer_name, amount_to_pay) VALUES (?, ?, ?)";
    private static final DataBase database = new DataBase();
    // Method to retrieve all customers
    public static ResultSet getAllCustomers() {
        return database.exeQuery(allCustomers);
    }

    /**
     *
     * @param id: id of customer, this must be unic.
     * @param name : name of customer
     * @param address : adress of customer
     * @param email email
     */
    public static void updateCustomer(int id, String name, String address, String email) {
        try {
            DataBase.getConnection().setAutoCommit(false);
            DataBase.exeUpdate(updateCustomer, name, address, email, id);
            DataBase.getConnection().commit();
        } catch (SQLException e) {
            System.out.println("Error updating customer: " + e.getMessage());
            try {
                DataBase.getConnection().rollback();
            } catch (SQLException ex) {
                System.out.println("Error rolling back transaction: " + ex.getMessage());
            }
        }
    }

    /**
     * Method to delete a customer
     */

    public static void deleteCustomer(int id) {
        try {
            DataBase.getConnection().setAutoCommit(false);
            DataBase.exeUpdate(deleteCustomer, id);
            DataBase.getConnection().commit();
        } catch (SQLException e) {
            System.out.println("Error deleting customer: " + e.getMessage());
            try {
                DataBase.getConnection().rollback();
            } catch (SQLException ex) {
                System.out.println("Error rolling back transaction: " + ex.getMessage());
            }
        }
    }

    /**
     *  Method to add a new customer
     */
    public static void addCustomer(String name, String address, String email) {
        try {
            DataBase.getConnection().setAutoCommit(false);
            DataBase.exeUpdate(addCustomerQuery, name, address, email);
            DataBase.getConnection().commit();
        } catch (SQLException e) {
            System.out.println("Error adding customer: " + e.getMessage());
            try {
                DataBase.getConnection().rollback();
            } catch (SQLException ex) {
                System.out.println("Error rolling back transaction: " + ex.getMessage());
            }
        }
    }

    /**
     *  Method to retrieve all products
     */

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

    public static String getCustomerNameById(int customerId) {
        String customerName = null;
        try {
            ResultSet resultSet = database.exeQuery(customerNameQuery, customerId);
            if (resultSet.next()) {
                customerName = resultSet.getString("name");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving customer name: " + e.getMessage());
        }
        return customerName;
    }
    public static ResultSet getProductById(int id){
        ResultSet resultSet = null;
        resultSet = database.exeQuery(getProductQuery, id);
        return resultSet;
    }
    public static void addBill(int id,String buyerName, double price) {
        try {
            DataBase.getConnection().setAutoCommit(false);
            database.exeUpdate(insertBillQuery,id, buyerName, price);
            DataBase.getConnection().commit();
            System.out.println("Bill successfully added for buyer: " + buyerName);
        } catch (SQLException e) {
            System.out.println("Error adding bill: " + e.getMessage());
            try {
                database.getConnection().rollback();
            } catch (SQLException ex) {
                System.out.println("Error rolling back transaction: " + ex.getMessage());
            }
        }
    }


}
