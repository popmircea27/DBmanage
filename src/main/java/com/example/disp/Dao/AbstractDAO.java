package com.example.disp.Dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.disp.ConnectionDB.DataBase;

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;


    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private String createSelectQuery(String field) {
        return "SELECT * FROM " + type.getSimpleName() + " WHERE " + field + " = ?";
    }

    public List<T> findAll() {
        List<T> resultList = new ArrayList<>();
        String query = "SELECT * FROM " + type.getSimpleName();

        try (Connection connection = DataBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            resultList = createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while retrieving all objects", e);
        }
        return resultList;
    }


    public T findById(int id) {
        String query = createSelectQuery("id");
        try (Connection connection = DataBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<T> objects = createObjects(resultSet);
                return objects.isEmpty() ? null : objects.get(0);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage(), e);
        }
        return null;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<>();
        try {
            Constructor<T> ctor = type.getDeclaredConstructor();
            while (resultSet.next()) {
                T instance = ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException | SQLException | IntrospectionException e) {
            LOGGER.log(Level.SEVERE, "Error while creating objects", e);
        }
        return list;
    }

    public T insert(T t) throws IntrospectionException {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName()).append(" (");

        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            sb.append(field.getName()).append(", ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.append(") VALUES (");
        for (Field field : fields) {
            sb.append("?, ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");

        String sql = sb.toString();

        try (Connection connection = DataBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int index = 1;
            for (Field field : fields) {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                Method method = propertyDescriptor.getReadMethod();
                Object value = method.invoke(t);
                statement.setObject(index++, value);
            }
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.log(Level.SEVERE, "Error while inserting object", e);
        }
        return t;
    }

    public T update(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ").append(type.getSimpleName()).append(" SET ");

        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            sb.append(field.getName()).append(" = ?, ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" WHERE id = ?");

        String sql = sb.toString();

        try (Connection connection = DataBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int index = 1;
            for (Field field : fields) {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                Method method = propertyDescriptor.getReadMethod();
                Object value = method.invoke(t);
                statement.setObject(index++, value);
            }
            // Set the id parameter
            PropertyDescriptor idPropertyDescriptor = new PropertyDescriptor("id", type);
            Method idMethod = idPropertyDescriptor.getReadMethod();
            statement.setObject(index, idMethod.invoke(t));

            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException | InvocationTargetException | IntrospectionException e) {
            LOGGER.log(Level.SEVERE, "Error while updating object", e);
        }
        return t;
    }
}
