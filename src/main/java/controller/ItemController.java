package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ItemDto;

import java.sql.*;

public class ItemController {

    ObservableList <ItemDto> observableList = FXCollections.observableArrayList();

    public ObservableList<ItemDto> getAllItems(){

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
}
