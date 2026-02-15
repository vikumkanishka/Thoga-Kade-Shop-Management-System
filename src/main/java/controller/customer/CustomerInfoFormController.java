package controller.customer;

import controller.service.CustomerService;
import controller.service.CustomerServiceImpl;
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
import java.time.LocalDate;
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

    CustomerService service = new CustomerServiceImpl();

    public void btnAddOnAction(ActionEvent actionEvent) {
        String id = txtCusId.getText();
        String firstname = txtCusFirstName.getText();
        String lastname = txtCusLastName.getText();
        String email = txtCusEmail.getText();
        String city = txtCusCity.getText();
        String address = txtCusAddress.getText();
        Integer phone = Integer.parseInt(txtCusPhone.getText());
        LocalDate regdate = cusRegDate.getValue();

        service.addCustomer(id,firstname,lastname,email,city,address,phone,regdate);

        loadAllCustomers();
        clearfields();

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

        service.deleteCustomer(txtCusId.getText());

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
        LocalDate regdate = cusRegDate.getValue();

        service.updateCustomer(id,firstname,lastname,email,city,address,phone,regdate);
        clearfields();
        loadAllCustomers();
    }

    public void btnReloadOnAction(ActionEvent actionEvent) {
        service.getAllCustomers();
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

        tblCustomerInfo.setItems(service.getAllCustomers());

        tblCustomerInfo.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newVlaue) -> {
            if (newVlaue != null){
                setSelectedValue((CustomerDto) newVlaue);
            }
        });
    }

    private void setSelectedValue(CustomerDto customerDto) {
        txtCusId.setText(customerDto.getCustomerId());
        txtCusFirstName.setText(customerDto.getFirstName());
        txtCusLastName.setText(customerDto.getLastName());
        txtCusEmail.setText(customerDto.getEmail());
        txtCusCity.setText(customerDto.getCity());
        txtCusAddress.setText(customerDto.getAddress());
        txtCusPhone.setText(String.valueOf(customerDto.getPhone()));
        cusRegDate.setValue(customerDto.getRegisteredDate());
    }

    public void loadAllCustomers(){
        tblCustomerInfo.setItems(service.getAllCustomers());
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
