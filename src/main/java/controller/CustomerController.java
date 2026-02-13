package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CustomerDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerController {

    ObservableList<CustomerDto> observableList = FXCollections.observableArrayList();

    public ObservableList<CustomerDto> getAllCustomers() {

        observableList.clear();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade_Shop_Management_System", "root", "200004602360");

            ResultSet resultSet = connection.prepareStatement("SELECT * FROM customers").executeQuery();


            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String email = resultSet.getString(4);
                int phone = resultSet.getInt(5);
                String address = resultSet.getString(6);
                String city = resultSet.getString(7);
                String regDate = resultSet.getString(8);

                CustomerDto customerDto = new CustomerDto(id, firstName, lastName, email, phone, address, city, regDate);

                observableList.add(customerDto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return observableList;
    }
}
