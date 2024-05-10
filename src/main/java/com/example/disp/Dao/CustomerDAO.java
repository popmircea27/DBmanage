package com.example.disp.Dao;

import com.example.disp.ConnectionDB.DataBase;
import com.example.disp.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO extends AbstractDAO<Customer>{
    private static final String allCustomers = "SELECT * FROM Customers_ORDERS_MANAGEMENT";
    private static final String addCustomerQuery = "INSERT INTO Customers_ORDERS_MANAGEMENT (name, address, email) VALUES (?, ?, ?)";
    private static final String updateCustomer = "UPDATE Customers_ORDERS_MANAGEMENT SET name = ?, address = ?, email = ? WHERE id = ?";
    private static final String deleteCustomer = "DELETE FROM Customers_ORDERS_MANAGEMENT WHERE id = ?";
    private static final String customerNameQuery = "SELECT name FROM Customers_ORDERS_MANAGEMENT WHERE id = ?";

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
}
