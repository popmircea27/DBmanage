package com.example.disp.ConnectionDB;

import java.sql.SQLException;

public class InterogationAddRecipe {

    static String insertBillQuery = "INSERT INTO BILL_TP_ASSIGNMENT (BILL_ID, buyer_name, amount_to_pay) VALUES (?, ?, ?)";
    private static final DataBase database = new DataBase();
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
