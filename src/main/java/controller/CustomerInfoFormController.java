package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CustomerDto;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class CustomerInfoFormController implements Initializable {

    public TableView tblCustomerInfo;
    public TableColumn colCusLastName;


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

    CustomerController customerController = new CustomerController();
    ObservableList<CustomerDto> observableList = FXCollections.observableArrayList();

    public void btnAddOnAction(ActionEvent actionEvent) {
        String id = txtCusId.getText();
        String firstname = txtCusFirstName.getText();
        String lastname = txtCusLastName.getText();
        String email = txtCusEmail.getText();
        String city = txtCusCity.getText();
        String address = txtCusAddress.getText();
        Integer phone = Integer.parseInt(txtCusPhone.getText());
        String regdate = String.valueOf(cusRegDate.getValue());

        customerController.addCustomer(id,firstname,lastname,email,city,address,phone,regdate);

        loadAllCustomers();
        clearfields();

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

        customerController.deleteCustomer(txtCusId.getText());

        loadAllCustomers();
        clearfields();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        String id = txtCusId.getText();
        String firstname = txtCusFirstName.getText();
        String lastname = txtCusLastName.getText();
        String email = txtCusEmail.getText();
        String city = txtCusCity.getText();
        String address = txtCusAddress.getText();
        Integer phone = Integer.parseInt(txtCusPhone.getText());
        String regdate = String.valueOf(cusRegDate.getValue());

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade_Shop_Management_System", "root", "200004602360");

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE customers SET firstName=?,lastName=?,email=?,phone=?,address=?,city=?,registeredDate=? WHERE customerId=?");

            preparedStatement.setObject(1, firstname);
            preparedStatement.setObject(2, lastname);
            preparedStatement.setObject(3, email);
            preparedStatement.setObject(4, phone);
            preparedStatement.setObject(5, address);
            preparedStatement.setObject(6, city);
            preparedStatement.setObject(7, regdate);
            preparedStatement.setObject(8, id);

            preparedStatement.executeUpdate();

            loadAllCustomers();
            clearfields();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnReloadOnAction(ActionEvent actionEvent) {
        customerController.getAllCustomers();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colCusId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colCusFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colCusLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colCusEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCusCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colCusAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCusPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colCusDate.setCellValueFactory(new PropertyValueFactory<>("registeredDate"));

        tblCustomerInfo.setItems(customerController.getAllCustomers());
    }

    public void loadAllCustomers(){
        tblCustomerInfo.setItems(customerController.getAllCustomers());
    }

    public void clearfields(){
        txtCusId.clear();
        txtCusFirstName.clear();
        txtCusLastName.clear();
        txtCusEmail.clear();
        txtCusCity.clear();
        txtCusAddress.clear();
        txtCusPhone.clear();
        cusRegDate.setValue(null);
    }

}
