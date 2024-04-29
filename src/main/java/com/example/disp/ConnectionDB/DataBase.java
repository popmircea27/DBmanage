package com.example.disp.ConnectionDB;

import java.sql.SQLException;

import java.sql.* ;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * in this class:- i generate the connection with the database.
 * teh db is hosted on my computer on a local host.
 */
public class DataBase {
    private static Connection connection = null;
    private static final Logger LOGGER = Logger.getLogger(DataBase.class.getName());
    static{

        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "system";
        String password = "LKLTLPHT2udfu";

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected.");
        } catch (SQLException e) {
            System.out.println("Connection failed." + e.getMessage());
        }

    }

    public static Connection getConnection() {
        return connection;
    }
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
            }
        }
    }

    public static int exeUpdate(String query, Object... params) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);

            // Set parameters based on the provided arguments
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error executing update: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println("Error closing prepared statement: " + e.getMessage());
                }
            }
        }
    }

    public ResultSet exeQuery(String query){
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet exeQuery(String query, int param) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Create a prepared statement with parameter
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, param); // Set the integer parameter

            // Execute the query and retrieve the result set
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
