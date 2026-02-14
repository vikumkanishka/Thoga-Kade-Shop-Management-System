package controller.item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ItemDto;

import java.net.URL;
import java.util.ResourceBundle;


public class ItemInfoFormController implements Initializable {

    public TextField txtId;
    public TextField txtName;
    public TextField txtPrice;
    public TextField txtPackSize;
    public TextField txtQTY;
    public TextArea txtDescription;
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


    ObservableList <ItemDto> itemDtos = FXCollections.observableArrayList();
    ObservableList <String> categoryList = FXCollections.observableArrayList("Food", "Beverage", "Other");
    ItemController itemController = new ItemController();


    @FXML
    void btnAddOnAction(ActionEvent event) {

        String id = txtId.getText();
        String name = txtName.getText();
        Double price = Double.valueOf(txtPrice.getText());
        String packSize = txtPackSize.getText();
        Integer qty = Integer.valueOf(txtQTY.getText());
        String category = String.valueOf(cmbCategory.getValue());
        String description = txtDescription.getText();

        itemController.addItem(id, name, price, description, category, packSize, qty);
        loadAllItems();
        clearFeilds();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFeilds();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        itemController.deleteItem(txtId.getText());
        loadAllItems();
        clearFeilds();

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

        ObservableList<ItemDto> resultSet = itemController.searchItem(txtId.getText());
        tblItemInformations.setItems(resultSet);

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        Double price = Double.valueOf(txtPrice.getText());
        String packSize = txtPackSize.getText();
        Integer qty = Integer.valueOf(txtQTY.getText());
        String category = String.valueOf(cmbCategory.getValue());
        String description = txtDescription.getText();

        itemController.updateItem(id, name, price, description, category, packSize, qty);

        loadAllItems();
        clearFeilds();
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

        loadAllItems();

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
        cmbCategory.setItems(categoryList);

        tblItemInformations.setItems(itemController.getAllItems());
    }

    public void loadAllItems(){
        tblItemInformations.setItems(itemController.getAllItems());
    }
}
