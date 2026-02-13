package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ItemDto;

import java.sql.*;

public class ItemController {

    ObservableList <ItemDto> observableList = FXCollections.observableArrayList();

    public ObservableList<ItemDto> getAllItems(){

        observableList.clear();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade_Shop_Management_System", "root", "200004602360");

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM items");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ItemDto itemDto = new ItemDto(
                        resultSet.getString(1),
                        resultSet.getString(1),
                        resultSet.getDouble(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7)
                );
                observableList.add(itemDto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return observableList;
    }

    public void addItem(String id, String name, Double price, String description, String category, String packSize, Integer qty){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade_Shop_Management_System", "root", "200004602360");

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO items VALUES(?,?,?,?,?,?,?)");

            preparedStatement.setObject(1, id);
            preparedStatement.setObject(2, name);
            preparedStatement.setObject(3, price);
            preparedStatement.setObject(4, description );
            preparedStatement.setObject(5, category);
            preparedStatement.setObject(6, packSize );
            preparedStatement.setObject(7, qty );

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteItem(String id){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade_Shop_Management_System", "root", "200004602360");

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM items WHERE ItemId = ?");

            preparedStatement.setObject(1,id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
