package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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




    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
