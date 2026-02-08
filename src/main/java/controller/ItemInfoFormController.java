package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemInfoFormController {

    @FXML
    private TableColumn<?, ?> ColPackSize;

    @FXML
    private JFXComboBox<?> cmbCategory;

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
    private TableView<?> tblItemInformations;

    @FXML
    private JFXTextArea txtDescription;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPackSize;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtQTY;

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

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

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
}
