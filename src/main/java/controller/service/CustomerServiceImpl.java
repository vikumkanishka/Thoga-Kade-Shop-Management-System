package controller.service;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CustomerDto;

import java.sql.*;
import java.time.LocalDate;

public class CustomerServiceImpl implements CustomerService {

    ObservableList<CustomerDto> observableList = FXCollections.observableArrayList();

    @Override
    public ObservableList<CustomerDto> getAllCustomers() {

        observableList.clear();

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            ResultSet resultSet = connection.prepareStatement("SELECT * FROM customers").executeQuery();


            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String email = resultSet.getString(4);
                int phone = resultSet.getInt(5);
                String address = resultSet.getString(6);
                String city = resultSet.getString(7);
                LocalDate regDate = resultSet.getDate(8).toLocalDate();

                CustomerDto customerDto = new CustomerDto(id, firstName, lastName, email, phone, address, city, regDate);

                observableList.add(customerDto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return observableList;
    }

    @Override
    public void addCustomer(String id, String firstName, String lastName, String email,String city, String address, int phone, LocalDate regDate) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customers VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setObject(1, id);
            preparedStatement.setObject(2, firstName);
            preparedStatement.setObject(3, lastName);
            preparedStatement.setObject(4, email);
            preparedStatement.setObject(5, phone);
            preparedStatement.setObject(6, address);
            preparedStatement.setObject(7, city);
            preparedStatement.setObject(8, regDate);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCustomer(String id) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customers WHERE customerId = ?");

            preparedStatement.setObject(1,id);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCustomer(String id, String firstName, String lastName, String email,String city, String address, Integer phone, LocalDate regDate) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE customers SET firstName=?,lastName=?,email=?,phone=?,address=?,city=?,registeredDate=? WHERE customerId=?");

            preparedStatement.setObject(1, firstName);
            preparedStatement.setObject(2, lastName);
            preparedStatement.setObject(3, email);
            preparedStatement.setObject(4, phone);
            preparedStatement.setObject(5, address);
            preparedStatement.setObject(6, city);
            preparedStatement.setObject(7, regDate);
            preparedStatement.setObject(8, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
