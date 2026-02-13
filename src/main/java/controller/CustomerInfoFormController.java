package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CustomerDto;

import java.sql.*;



public class CustomerInfoFormController {

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


        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade_Shop_Management_System", "root", "200004602360");

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customers VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setObject(1, id);
            preparedStatement.setObject(2, firstname);
            preparedStatement.setObject(3, lastname);
            preparedStatement.setObject(4, email);
            preparedStatement.setObject(5, phone);
            preparedStatement.setObject(6, address);
            preparedStatement.setObject(7, city);
            preparedStatement.setObject(8, regdate);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade_Shop_Management_System", "root", "200004602360");

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customers WHERE customerId = ?");

            preparedStatement.setObject(1,txtCusId.getText());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnReloadOnAction(ActionEvent actionEvent) {

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

            colCusId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            colCusFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            colCusLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            colCusEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colCusCity.setCellValueFactory(new PropertyValueFactory<>("city"));
            colCusAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colCusPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            colCusDate.setCellValueFactory(new PropertyValueFactory<>("registeredDate"));

            tblCustomerInfo.setItems(observableList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
