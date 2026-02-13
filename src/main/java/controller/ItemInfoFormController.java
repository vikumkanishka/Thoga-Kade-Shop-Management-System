package controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ItemDto;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class ItemInfoFormController implements Initializable {

    public TextField txtId;
    public TextField txtName;
    public TextField txtPrice;
    public TextField txtPackSize;
    public TextField txtQTY;
    @FXML
    private TableColumn<?, ?> ColPackSize;

    @FXML
    private ComboBox<String> cmbCategory;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQTY;

    @FXML
    private TableView<ItemDto> tblItemInformations;

    @FXML
    private JFXTextArea txtDescription;

    ObservableList <ItemDto> itemDtos = FXCollections.observableArrayList();
    ObservableList <String> categoryList = FXCollections.observableArrayList("Food", "Beverage", "Other");

    public void initialize(){
        cmbCategory.setItems(categoryList);
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

        String id = txtId.getText();
        String name = txtName.getText();
        String price = txtPrice.getText();
        String packSize = txtPackSize.getText();
        String qty = txtQTY.getText();
        String category = String.valueOf(cmbCategory.getValue());
        String description = txtDescription.getText();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade_Shop_Management_System", "root", "200004602360");

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO items VALUES(?,?,?,?,?,?,?)");

            preparedStatement.setObject(1, id);
            preparedStatement.setObject(2, name);
            preparedStatement.setObject(3, price);
            preparedStatement.setObject(4, packSize);
            preparedStatement.setObject(5, qty);
            preparedStatement.setObject(6, category);
            preparedStatement.setObject(7, description);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFeilds();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade_Shop_Management_System", "root", "200004602360");

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM items WHERE id = ?");

            preparedStatement.setObject(1,txtId.getText());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

        itemDtos.clear();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade_Shop_Management_System", "root", "200004602360");

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM items WHERE itemId = ?");

            preparedStatement.setObject(1, txtId.getText());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                Double price = resultSet.getDouble(3);
                String description = resultSet.getString(4);
                String category = resultSet.getString(5);
                String packSize = resultSet.getString(6);
                Integer qty = resultSet.getInt(7);

                ItemDto itemDto = new ItemDto(id, name, price, description, category, packSize, qty);
                itemDtos.add(itemDto);
            }
            colId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
            colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
            colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
            ColPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
            colQTY.setCellValueFactory(new PropertyValueFactory<>("quantityOnHand"));


            tblItemInformations.setItems(itemDtos);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String price = txtPrice.getText();
        String packSize = txtPackSize.getText();
        String qty = txtQTY.getText();
        String category = String.valueOf(cmbCategory.getValue());
        String description = txtDescription.getText();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade_Shop_Management_System", "root", "200004602360");

            connection.prepareStatement("UPDATE items SET name = ?, price = ?, pack_size = ?, qty_on_hand = ?, category = ?, description = ? WHERE id = ?");

             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE items SET name = ?, price = ?, pack_size = ?, qty_on_hand = ?, category = ?, description = ? WHERE id = ?");

             preparedStatement.setObject(1, name);
             preparedStatement.setObject(2, price);
             preparedStatement.setObject(3, packSize);
             preparedStatement.setObject(4, qty);
             preparedStatement.setObject(5, category);
             preparedStatement.setObject(6, description);
             preparedStatement.setObject(7, id);

             preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearFeilds(){
        txtId.clear();
        txtName.clear();
        txtPrice.clear();
        txtPackSize.clear();
        txtQTY.clear();
        cmbCategory.setValue(null);
        txtDescription.clear();
    }

    public void btnReloadOnAction(ActionEvent actionEvent) {

        itemDtos.clear();

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
                itemDtos.add(itemDto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        ColPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("quantityOnHand"));

        tblItemInformations.setItems(itemDtos);

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
                itemDtos.add(itemDto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
