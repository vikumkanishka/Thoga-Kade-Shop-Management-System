package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import model.CustomerDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class CustomerInfoFormController {

    @FXML
    private TableColumn<?, ?> ColCusLastName;

    @FXML
    private TableColumn<?, ?> colCusAddress;

    @FXML
    private TableColumn<?, ?> colCusCity;

    @FXML
    private TableColumn<?, ?> colCusDate;

    @FXML
    private TableColumn<?, ?> colCusEmail;

    @FXML
    private TableColumn<?, ?> colCusFirstName;

    @FXML
    private TableColumn<?, ?> colCusId;

    @FXML
    private TableColumn<?, ?> colCusPhone;

    @FXML
    private DatePicker cusRegDate;

    @FXML
    private TextField txtCusAddress;

    @FXML
    private TextField txtCusCity;

    @FXML
    private TextField txtCusEmail;

    @FXML
    private TextField txtCusFirstName;

    @FXML
    private TextField txtCusId;

    @FXML
    private TextField txtCusLastName;

    @FXML
    private TextField txtCusPhone;

    public void btnAddOnAction(ActionEvent actionEvent) {
        String id = txtCusId.getText();
        String firstname = txtCusFirstName.getText();
        String lastname = txtCusLastName.getText();
        String email = txtCusEmail.getText();
        String city = txtCusCity.getText();
        String address = txtCusAddress.getText();
        Integer phone = Integer.parseInt(txtCusPhone.getText());
        String regdate = String.valueOf(cusRegDate.getValue());

        CustomerDto customerDto = new CustomerDto(id, firstname, lastname, email, phone, address, city, regdate);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade_Shop_Management_System", "root", "200004602360");

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customer VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setObject(1,id);
            preparedStatement.setObject(2,firstname);
            preparedStatement.setObject(3,lastname);
            preparedStatement.setObject(4,email);
            preparedStatement.setObject(5,phone);
            preparedStatement.setObject(6,address);
            preparedStatement.setObject(7,city);
            preparedStatement.setObject(8,regdate);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnReloadOnAction(ActionEvent actionEvent) {
    }
}
