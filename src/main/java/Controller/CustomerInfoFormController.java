package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

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

}
